/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.waysidecontrol;

import java.util.ArrayList;

/**
 *
 * @author tak72_000
 */
public class TestUIInterface {
    WaysideController wc;
    ArrayList<Block> blocks;
    PLC plc;
    
    public TestUIInterface(LineTypes line){
        plc = new TwoWayPLC();
        blocks = new ArrayList<Block>();
        System.out.println("So far so good");
        if(line.equals(LineTypes.Red)){
            Block block22 = new Block(LineTypes.Red, 22, ArrowDirection.Head);
            Block block23 = new Block(LineTypes.Red, 23, ArrowDirection.Head);
            Block block24 = new Block(LineTypes.Red, 24, ArrowDirection.Head);
            Block block25 = new Block(LineTypes.Red, 25, ArrowDirection.Head);
            Block block26 = new Block(LineTypes.Red, 26, ArrowDirection.Head);
            Block block28 = new Block(LineTypes.Red, 28, ArrowDirection.Head);
            Block block29 = new Block(LineTypes.Red, 29, ArrowDirection.Head);
            Block block30 = new Block(LineTypes.Red, 30, ArrowDirection.Head);
            Block block31 = new Block(LineTypes.Red, 31, ArrowDirection.Head);
            Block block32 = new Block(LineTypes.Red, 32, ArrowDirection.Head);
            Block block34 = new Block(LineTypes.Red, 34, ArrowDirection.Head);
            Block block76 = new Block(LineTypes.Red, 76, ArrowDirection.Head);
            Block block75 = new Block(LineTypes.Red, 75, ArrowDirection.Head);
            Block block74 = new Block(LineTypes.Red, 74, ArrowDirection.Head);
            Block block73 = new Block(LineTypes.Red, 73, ArrowDirection.Head);
            Block block72 = new Block(LineTypes.Red, 72, ArrowDirection.Head);
            
            //SwitchBlocks require different constructor
            ArrayList<Block> block27Options = new ArrayList<Block>();
            block27Options.add(block28);
            block27Options.add(block76);
            SwitchBlock block27 = new SwitchBlock(block27Options, 7, LineTypes.Red, 27, ArrowDirection.Head);
            
            ArrayList<Block> block33Options = new ArrayList<Block>();
            block33Options.add(block32);
            block33Options.add(block72);
            SwitchBlock block33 = new SwitchBlock(block33Options, 8, LineTypes.Red, 33, ArrowDirection.Head);
            
            block22.adj1 = null;
            block22.adj2 = block23;
            block23.adj1 = block22;
            block23.adj2 = block24;
            block24.adj1 = block23;
            block24.adj2 = block25;
            block25.adj1 = block24;
            block25.adj2 = block26;
            block26.adj1 = block25;
            block26.adj2 = block27;
            block27.adj1 = block26;
            block27.adj2 = null;
            block28.adj1 = block27;
            block28.adj2 = block29;
            block29.adj1 = block28;
            block29.adj2 = block30;
            block30.adj1 = block29;
            block30.adj2 = block31;
            block31.adj1 = block30;
            block31.adj2 = block32;
            block32.adj1 = block31;
            block32.adj2 = block33;
            block33.adj1 = null;
            block33.adj2 = block34;
            block34.adj1 = block33;
            block34.adj2 = null;
            block76.adj1 = block75;
            block76.adj2 = block27;
            block75.adj1 = block74;
            block75.adj2 = block76;
            block74.adj1 = block73;
            block74.adj2 = block75;
            block73.adj1 = block72;
            block73.adj2 = block74;
            block72.adj1 = block33;
            block72.adj2 = block73;
            
            blocks.add(block22);
            blocks.add(block23);
            blocks.add(block24);
            blocks.add(block25);
            blocks.add(block26);
            blocks.add(block27);
            blocks.add(block28);
            blocks.add(block29);
            blocks.add(block30);
            blocks.add(block31);
            blocks.add(block32);
            blocks.add(block33);
            blocks.add(block34);
            blocks.add(block72);
            blocks.add(block73);
            blocks.add(block74);
            blocks.add(block75);
            blocks.add(block76);
            
            wc = new WaysideController();
            
            wc.switches.add(block27);
            wc.switches.add(block33);
            wc.blocks.addAll(blocks);
            
            printLine(block22);
        }
        else {
            
        }
    }
    
    private void printLine(Block start){
        while(!start.equals(null)){
            System.out.println(start.toString());
            
            if(start instanceof SwitchBlock){
                break;
            }
            
            start = start.adj2;
        }
    }
    
    public PLC getPLC(){
        return this.plc;
    }
    
    public void setPLC(PLC newPLC){
        plc = newPLC;
    }
    
}
