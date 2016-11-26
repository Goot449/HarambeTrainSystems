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
public class Section {
    SectionTypes sectionType;
    ArrayList<Block> blockList;
    
    public boolean checkOccupancy(){
        for(Block b : blockList)
        {
            if(b.isOccupied()){
                return true;
            }
        }
        return false;
    }
}
