package com.harambe.waysidecontroller;
import com.harambe.trackmodel.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tak72_000
 */
public interface PLC {
    //boolean changeSwitch(Switch sb);
    boolean checkMaintenance(Block maintenance);
    boolean checkLight(Switch switchBlock);
    boolean checkCrossing(Block crossingBlock, Block next, Block prev);
    boolean checkAuthority(Block nxtBlock, Block destination, int trainID);
    boolean checkSwitch(Block curBlock, Block nxtBlock, Block destination);
}
