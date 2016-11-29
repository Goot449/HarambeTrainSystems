package com.harambe.traincontroller;

import com.harambe.trainmodel.*;

public class vitalCalculator {
    
    private static int kp = 500000;
    private static int ki = 1000;
    private static double maxEnginePower = 120000;
    private static double sampleRate = 0.001;
    
    public double[] calculatePower(double setPoint, double currentSpeed, double ek_prev, double uk_prev, double speedLimit, double authority, double emergencyBrakeStatus){
        if (setPoint>speedLimit){
            setPoint = speedLimit;
        }
        double power1[] = calculatePower1(setPoint, currentSpeed, ek_prev, uk_prev);
        double power2[] = calculatePower2(setPoint, currentSpeed, ek_prev, uk_prev);
        double power3[] = calculatePower3(setPoint, currentSpeed, ek_prev, uk_prev);
       
        double finalPower;
        double ek;
        double uk;
        if (power1[0] == power2[0]){
            finalPower = power1[0];
            ek = power1[1];
            uk = power1[2];
        }
        else if(power2[0] == power3[0]){
            finalPower = power2[0];
            ek = power2[1];
            uk = power2[2];
        }
        else if (power1[0] == power3[0]){
            finalPower = power1[0];
            ek = power1[1];
            uk = power1[2];
        }
        else{
            finalPower = 0;
            ek = ek_prev;
            uk = uk_prev;
        }
        if (finalPower>=maxEnginePower){
            finalPower = maxEnginePower;
            uk = uk_prev;
        }
        else if (finalPower < -1*maxEnginePower){
            finalPower = -1*maxEnginePower;
            ek = uk_prev;
        }
        if (emergencyBrakeStatus != 0.0){
            finalPower = 0;
        }
        double[] calcOut = new double[3];
        calcOut[0] = finalPower;
        calcOut[1] = ek;
        calcOut[2] = uk;
        return calcOut;
    }
    
    private double[] calculatePower1(double setPoint, double currentSpeed, double ek_prev, double uk_prev){
        double[] returnInfo = new double[3];
        double ek = setPoint - currentSpeed;
        double uk = uk_prev + (sampleRate/2.0)*(ek - ek_prev);
        returnInfo[0] = kp*ek+ki*uk;
        returnInfo[1] = ek;
        returnInfo[2] = uk;
        return returnInfo;
    }
    
    private double[] calculatePower2(double setPoint, double currentSpeed, double ek_prev, double uk_prev){
        double[] returnInfo = new double[3];
        double ek = -1.0*currentSpeed + setPoint;
        double uk = uk_prev + (sampleRate/2.0)*(ek) - (sampleRate/2.0)*(ek_prev);
        returnInfo[0] = kp*ek+ki*uk;
        returnInfo[1] = ek;
        returnInfo[2] = uk;
        return returnInfo;
    }
    
    private double[] calculatePower3(double setPoint, double currentSpeed, double ek_prev, double uk_prev){
        double[] returnInfo = new double[3];
        double ek = setPoint - currentSpeed;
        double uk = uk_prev + (sampleRate/2.0)*(ek - ek_prev);
        returnInfo[0] = kp*ek+ki*uk;
        returnInfo[1] = ek;
        returnInfo[2] = uk;
        return returnInfo;
    }
    
}
