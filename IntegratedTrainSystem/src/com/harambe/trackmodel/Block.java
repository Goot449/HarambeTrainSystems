/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.trackmodel;

import java.util.*;

/**
 *
 * @author Owner
 */
public class Block implements BlockInterface{
    
    //Initial variable values:
    //Authority: -1
    //Commanded speed: Speed limit
    //For yard: Authority = First 2 blocks
    //-Commanded speed = 10
    
    //Block static attributes
    private String line;
    private String section;
    private int blockNumber;
    private double blockLength;
    private int speedLimit;
    private double grade;
    private String switchBlock;
    private String station;
    private String underground;
    private double cumElevation;
    private double elevation;
    private String switchNumber;
    private String arrow;
    private String crossing = null;
    private String stationSide;
    private String switchType;
    private int direction;
    private int stationPeople = 0;
    private boolean fromYard = false;
    private boolean toYard = false;
    private double friction = 0.001;
    private int seen = 0;
    private String stationName;
    private Switch switcher = null;
    private Block previous;
    private Block next;
   
    //Blcok configurable attributes
    private boolean blockOccupied = false;
    private int trainID = 0;
    private Crossing railroadCrossing = null;
    private boolean closedBlock = false;
    private boolean signalWorking = true;
    private boolean brokenBlock = false;
    private boolean brokenCircuit = false;
    private boolean lightsGreenTrueRedFalse = true;
    private boolean trackHeater = false;
    private boolean beaconCommanded = false;
    private double commandedSpeed = 0;
    private double commandedAuthority = -1;
    private double distanceTraveled = 0;
    
    public Block(Block b){
        this.line = b.line;
        this.section = b.section;
        blockNumber = b.blockNumber;
        blockLength = b.blockLength;
	grade = b.grade;
	speedLimit = b.speedLimit;
	station = b.station;
	switchBlock = b.switchBlock;
	underground = b.underground;
	elevation = b.elevation;
	cumElevation = b.cumElevation;
	switchNumber = b.switchNumber;
	arrow=b.arrow; 
	direction = b.direction;
	crossing = b.crossing;
	switchType = b.switchType;
	commandedAuthority = -1;
	commandedSpeed = speedLimit;
        
        railroadCrossing = b.getCrossing();
    }
    
    public Block(String[] splitStrings, Block lastCreated){
        
        line = splitStrings[0];
        section = splitStrings[1];
        blockNumber = Integer.parseInt(splitStrings[2]);
        blockLength = Double.parseDouble(splitStrings[3]);
	grade = Double.parseDouble(splitStrings[4]);
	speedLimit = Integer.parseInt(splitStrings[5]);
	station = splitStrings[6];
	switchBlock = splitStrings[7];
	underground = splitStrings[8];
	elevation = Double.parseDouble(splitStrings[9]);
	cumElevation = Double.parseDouble(splitStrings[10]);
	switchNumber = splitStrings[11];
	arrow=splitStrings[12]; 
	direction = Integer.parseInt(splitStrings[13]);
	crossing = splitStrings[14];
	switchType = splitStrings[15];
	commandedAuthority = -1;
	commandedSpeed = speedLimit;
        
        if(station.equals("TO YARD") || station.equals("TO YARD/FROM YARD")){
            
		toYard = true;
		//station = "";
	}
	if(station.equals("FROM YARD") || station.equals("TO YARD/FROM YARD")){
            
		fromYard = true;
		commandedAuthority = 100;
		commandedSpeed = 10;
		//station = "";
	}
	if(toYard == false && fromYard == false && station.length()>0){
            
		String[] stationStuff = station.split("-");
		station = stationStuff[0];
		//stationSide = stationStuff[1];
	}
        if(lastCreated == null){

	} else if(direction == 1 || direction == 2){
                //Set the path 
		next = lastCreated;

		if(next.getDirection() == -1){
                    next.setNext(this);
                }
		next.setPrevious(this);
	} else if(direction == -1) {
            
		previous = lastCreated;

		if(previous.getDirection() == 2){
			previous.setPrevious(this);
                } else{
                    previous.setNext(this);
                }
	}
	if(isStation()){
            Random pls = new Random();
            stationPeople = pls.nextInt(50);
	}

	commandedSpeed = speedLimit;
    }
    
    //----------------TRAIN----------------
    public int getBlockSpeedLimit(){
        return speedLimit;
    }

    //Returns the train friction coefficient
    public double getFrictionCoefficient(){
        return friction;
    }

    //Returns train grade 
    public double getGrade(){
        return grade;
    }
    
    //Returns the current commanded authority from the Wayside Controller 
    public double getTrainAuthority(){
        double temp = commandedAuthority;
        commandedAuthority = -1;
                
        return temp;
    }

    //Returns the current commanded speed from the Wayside Controller
    public double getTrainCommandedSpeed(){
        return (commandedSpeed*.2778);
    }

    //If a station block and indicated to stop by the Wayside, beacon will send string of information to train like "station name", "L/R" side, 
    //dwell time and passengers at stop.
    public String getBeacon(){

        if(station.length()>0 && beaconCommanded && (!toYard||!fromYard)){
            beaconCommanded = false;
            return station + "," + stationSide + "," + "90" + "," + stationPeople;
	} else{
            return "";
	}
    }
    
    //----------------WAYSIDE----------------
    //Returns T occupied; F not occupied
    public boolean isBlockOccupied(){
        return blockOccupied;
    }

    public void toggleOccupied(){
        blockOccupied = (!blockOccupied);
    }
    
    public void toggleBroken(){
        brokenBlock = (!brokenBlock);
    }

    //Returns a string of this block including: section, block number, occupied (boolean), broken (boolean), closed (boolean)
    public String toString(){
	return (this.getSection() + "   " + this.getBlockNumber() + "   " + this.isBlockOccupied() + "  " + this.isBroken() + " " +this.isClosed());
    }

    //Returns T broken; F not broken
    public boolean isBroken(){
        return brokenBlock;
    }

    //Returns true if closed, false if not closed
    public boolean isClosed(){
        return closedBlock;
    }
    
    //Sets the commanded speed of the block	
    public void setCommandedSpeed(double commandedSpeed2){		
        commandedSpeed = commandedSpeed2;
    }

    public void toggleRedGreen(boolean trueGreen){
        lightsGreenTrueRedFalse	= trueGreen;
    }
	
    public boolean getRedFalseGreenTrue(){
        return lightsGreenTrueRedFalse;
    }

    public boolean isSignalWorking(){
        return signalWorking;
    }

    //Closes a block for maintenance
    public void closeBlock(){
        closedBlock = true;
    }
    
    public void breakCircuit(){
        brokenCircuit = true;
        blockOccupied = true;
    }

    //Opens a block for maintenance
    public void openBlock(){
        closedBlock = false;
    }

    public void setBeaconOn(){
        beaconCommanded = true;	
    }

    //Chooses next block for track traversal based on where the train came from
    public Block traverse(){
        
        Block returnBlock = null;
        seen = 1;
        
        boolean zeroPrevious = false;
        boolean zeroNext = false;
        

        if(direction == 1 || direction == -1){

            if(this.getNext() == null){
                returnBlock = this;
            } else{
                returnBlock = this.getNext();
            }

            if(this.getPrevious()!= null){
                this.getPrevious().setSeen(0);
            }
	} else{
            if(this.station.equals("TO YARD/FROM YARD")){
                
                returnBlock = this.getNext();
		if(returnBlock == null){
                    returnBlock = this;
		}
            } else if(this.getNext() == null){
                returnBlock = this;
            } else if(this.getPrevious() == null){
                returnBlock = this;
            } else if(this.getNext().getSeen() == 1){
                returnBlock = this.getPrevious();
		zeroNext = true;
            } else if(this.getPrevious().getSeen() == 1){
                returnBlock = this.getNext();
		zeroPrevious = true;
            }

            //Going wrong way on 1-WAY case
            if(returnBlock != null && this.getArrow().equals("Head") && returnBlock.getArrow().equals("Head") && (returnBlock.getDirection() == 1 || returnBlock.getDirection() == -1)){
                returnBlock = this;
		zeroPrevious = false;
		zeroNext = false;
            }
	}

	if(zeroPrevious){
            this.getPrevious().setSeen(0);
        }

	if(zeroNext){
            this.getNext().setSeen(0);
	}

	return returnBlock;

	}

	public boolean isCrossing(){
		
		if(crossing.equals("-")){
                    return false;
		} else{
                    return true;
		} 
	}

	//Same as traverse but doens't mark seen
	public Block peek(){
            
            Block returnBlock = null;
            boolean zeroPrevious = false;
            boolean zeroNext = false;
		
            if(direction == 1 || direction == -1){

		if(this.getNext() == null){
                    returnBlock = this;
		} else{
                    returnBlock = this.getNext();
		}

		if(this.getPrevious()!= null){
                    //this.getPrevious().setSeen(0);
		}
            } else{
                if(this.station.equals("TO YARD/FROM YARD")){
                    returnBlock = this.getNext();
                    if(returnBlock == null){
                        returnBlock = this;
                    }
		} else if(this.getNext() == null){
                    returnBlock = this;
                } else if(this.getPrevious() == null){
                    returnBlock = this;
		} else if(this.getNext().getSeen() == 1){
                    returnBlock = this.getPrevious();
                    zeroNext = true;
		} else if(this.getPrevious().getSeen() == 1){
                    returnBlock = this.getNext();
                    zeroPrevious = true;
                }

                //Going wrong way on 1-WAY case
                if(returnBlock != null && this.getArrow().equals("Head") && returnBlock.getArrow().equals("Head") && (returnBlock.getDirection() == 1 || returnBlock.getDirection() == -1)){
				returnBlock = this;
				zeroPrevious = false;
				zeroNext = false;
			}
		}

		if(zeroPrevious)
		{
                    //this.getPrevious().setSeen(0);
		}

		if(zeroNext)
		{
                    //this.getNext().setSeen(0);
		}

		return returnBlock;
	}

	public boolean isSwitch(){
		
		if(switchNumber.length()>0){
			return true;
                } else{
			return false;
                }
	}
	
	//Sets the current authority;
	public void setAuthority(double authority){
            commandedAuthority = authority;
	}

	public Crossing getCrossing() {
            return railroadCrossing;
	}

	public void addCrossing(Crossing newCrossing){
            railroadCrossing = newCrossing;
	}
        
        public void setFriction(double frictionIn)
	{
		friction = frictionIn;
		if(friction == 0.0002)
		{
			trackHeater = true;
		}
		friction = 0.001;
	}
      
	//Toggles switch and all its connections if attached to a switch block 
	public void toggleSwitch()
	{
		if(switcher!=null){
                    switcher.toggleSwitch();
                }
	}
        
	/*public String gui1()
	{
		int directional = direction;
		if(direction == -1)
			direction = 1;

		String firstGui = "";
		firstGui = firstGui + "Grade: " + grade + "\nElevation (cum): " + cumElevation + "\nLength: " + blockLength + "\nSpeed Limit: " + speedLimit + "\nDirection: " + direction;
		
		String stationString = "";
		if(isStation())
		{
			stationString = "\tBeacon: " + station + "\tStation side:" + stationSide + "\nDwell time: " + "90" + "\tPeople: " + stationPeople;

		}
		
		firstGui= firstGui + "\n\nIs station: " + isStation() +stationString;


		String crossingString = "";
		if(isCrossing())
		{
			crossingString = "\t" + railroadCrossing.toString();
		}

		firstGui= firstGui + "\n\nIs crossing: " + isCrossing() +crossingString;


		return firstGui;
	}

	public String gui2()
	{

		String secondGui = "";

		
		secondGui = secondGui + "Signal state: ";

		if(getRedFalseGreenTrue())
		{
			secondGui = secondGui = "Green";
		}
		else
		{
			secondGui = secondGui + "Red";
		}

		secondGui = secondGui + "\n\nCommanded Authority: " + commandedAuthority + "\n\nCommanded Speed: " + commandedSpeed + "\n\nBlock occupied: " +blockOccupied;

		return secondGui;

	}*/

	
    //----------------BLOCK----------------
    public boolean isStation(){
        
        if(station.length()>0 && (!toYard||!fromYard)){
            return true;
        } else{
            return false;
	}
    }

    //Returns 1 if this block has already been traversed; 0 if not
    private int getSeen(){
        return seen;
    }
				
    public double getBlockLength(){
        return blockLength;
    }
    
    public int getBlockNumber(){
        return blockNumber;
    }

    //Returns the ID of the train currently in the block; 0 not there 
    public int getTrainID(){
        return trainID;
    }

    public int getSpeedLimit(){
        return speedLimit;
    }
    
    //For use in traversing the track
    public Block getPrevious() {
        return previous;
    }
    public Block getNext(){
        return next;
    }
    
    public String getSection(){
        return section;	
    }
    
    public String getStation(){
        return station;
    }
    
    public String getStationSide(){
        return stationSide;
    }
    
    public String getSwitchNumber(){
        return switchNumber; /*e.g. "Switch 6" */
    }
    
    public Switch getSwitch(){
        return switcher;
    }
    
    public String getSwitchBlock(){
        return switchBlock; /* e.g. SWITCH or "" */ 
    }
    
    //This method is used only internally for setting up switches.  switchType contains a string "AFTER", "BEFORE", or "-", signifying whether the switch is part of a middle section
    //that comes before or after the fork, as well as - to signify a normal 3-junction switch
    public String getSwitchType(){
        return switchType;
    }	

    //This method is used only internally for two-directional traversal, since trains can go in two ways on some blocks and need to know where they've come from	
    public void setSeen(int i){
        seen = i; /*for traversal use */
    }
    
    //Puts a train in a block
    private void setTrainID(int ID){
        trainID = ID;
    }

    //This method returns the type of arrow "Head" or "Tail" of the current block.	
    public String getArrow(){
        return arrow;
    }

    //This method physically places a train in a block and changes the appropriate parameters. 	
    public Block placeTrain(int train, double distanceMoved){
        
        System.out.println(train+" train moved to: " + this.section + " " + this.blockNumber);
	trainID = train;
        blockOccupied = true;
	
        return this.moveTrain(distanceMoved);	
    }

    //This method simulates train movement.  Distance updates and is stored.  If it surpasses length of the block, train proceeds.  	
    public Block moveTrain(double moved){
		
		double newDist = moved + distanceTraveled;
		//System.out.println("In " + this.section + " " + this.blockNumber + " moved: " + newDist + "Length:"+ this.blockLength);
		Block currentBlock = this;
                
		if(newDist>blockLength){
                    
                    Block temp = currentBlock;
                    blockOccupied = false;
                    distanceTraveled = 0;
                    newDist = newDist - blockLength;
                    //((Block) this.getNext()).placeTrain(trainID, newDist); <----------- traversal move 
                    currentBlock = this.traverseTrain(trainID); 
                    
                    if(temp == currentBlock){
                        System.out.println("CRASH!");
                    } else{
                        currentBlock = currentBlock.placeTrain(trainID,newDist);
                    }
			
                    //trainID = 0;
		} else{
                    distanceTraveled = newDist;
		}
		
		//System.out.print("Train is currently in block: " + currentBlock.getSection() + " " + currentBlock.getBlockNumber());
		return currentBlock;
	}
    
    //This method simulates train movement.  Equivalent to traverse() but is used solely for trains	
    public Block traverseTrain(int train){
            
        Block returnBlock = null;
        //System.out.println(this.getSection() + this.getBlockNumber());
        //System.out.println(this.getNext());
        //System.out.println(this.getPrevious());
        boolean zeroNext = false;
        boolean zeroPrevious = false;

        if(direction == 1 || direction == -1){

            if(this.getNext() == null){
                returnBlock = this;
            } else{
                returnBlock = this.getNext();
            }

            if(this.getPrevious()!= null){
                zeroPrevious = true;
            }
        } else{
                
            if(this.station.equals("TO YARD/FROM YARD")){
				
                returnBlock = this.getNext();
                if(returnBlock == null){
                    returnBlock = this;
                }
                
                System.out.println("got here");
                System.out.println(returnBlock);
                    
            } else if(this.getNext() == null){
                returnBlock = this;
            } else if(this.getPrevious() == null){
                returnBlock = this;
            } else if(this.getNext().getTrainID() == train){
                returnBlock = this.getPrevious();
                zeroNext = true;
            } else if(this.getPrevious().getTrainID() == train){
                returnBlock = this.getNext();
                zeroPrevious = true;
            }

            //Going wrong way on 1-WAY case
            if(returnBlock != null && this.getArrow().equals("Head") && returnBlock.getArrow().equals("Head") && (returnBlock.getDirection() == 1 || returnBlock.getDirection() == -1)){
                returnBlock = this;
                zeroPrevious = false;
                zeroNext = false;
            }
        }

        if(zeroPrevious){
            this.getPrevious().setTrainID(0);
        }

        if(zeroNext){
            this.getNext().setTrainID(0);
        }
            
        //System.out.println(this.getSection() + this.getBlockNumber());
        return returnBlock;
    }

    //Returns 1 or -1 for a one-way block (-1 means constructed from tail to head), 2 for two-way		
    public int getDirection(){
        return direction;
    }

    public void setNext(Block nextBlock){
        next = nextBlock; 
    }
    
    public String getLine(){
        return line;
    }
    
    public void setPrevious(Block previousBlock){
        previous = previousBlock;
    }

    public void setSwitch(Switch aSwitch){
        switcher = aSwitch;
    }

    public int getStationPeople(){
        return stationPeople;
    }

    //Returns 1 if this block has already been traversed, 0 if not. ??? REDUNDANT... remove. 
    public int getBlockDirection(){
        return direction;
    }
}
