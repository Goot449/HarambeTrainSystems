/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.ctcoffice;

import com.harambe.waysidecontroller.Block;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jake
 */
public class CTCOfficeProto {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            new officeWindow().setVisible(true);
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CTCOfficeProto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CTCOfficeProto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CTCOfficeProto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CTCOfficeProto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
    

