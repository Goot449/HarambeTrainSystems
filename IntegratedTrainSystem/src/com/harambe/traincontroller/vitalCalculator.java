/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harambe.traincontroller;

import com.harambe.trainmodel.*;

/**
 *
 * @author Alex
 */
public class vitalCalculator {
    
    private static int kp = 500000;
    private static int ki = 1000;
    private static double maxEnginePower = 120000;
    private static double sampleRate = 0.001;
    
    public double[] calculatePower(double setPoint, double currentSpeed, double ek_prev, double uk_prev, double speedLimit, double authority){
        double ek = setPoint - currentSpeed;
        double uk = uk_prev + (sampleRate/2.0)*(ek - ek_prev);
//        double power1 = calculatePower1(setPoint, currentSpeed);
//        double power2 = calculatePower2(setPoint, currentSpeed);
//        double power3 = calculatePower3(setPoint, currentSpeed);
        
        double finalPower = kp*ek+ki*uk;
        if (finalPower>=maxEnginePower){
            finalPower = maxEnginePower;
            uk = uk_prev;
        }
//        if (power1 == power2){
//            finalPower = power1;
//        }
//        else if(power2 == power3){
//            finalPower = power2;
//        }
//        else if (power1==power3){
//            finalPower = power1;
//        }
//        else{
//            finalPower = 0;
//        }
        double[] calcOut = new double[3];
        calcOut[0] = finalPower;
        calcOut[1] = ek;
        calcOut[2] = uk;
        return calcOut;
    }
    
    private double calculatePower1(double setPoint, double currentSpeed){
        double power1 = 100;
        return power1;
    }
    
    private double calculatePower2(double setPoint, double currentSpeed){
        double power2 = 200;
        return power2;
    }
    
    private double calculatePower3(double setPoint, double currentSpeed){
        double power3 = 300;
        return power3;
    }
    
}
