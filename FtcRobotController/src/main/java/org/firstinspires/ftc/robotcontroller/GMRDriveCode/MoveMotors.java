package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Payton on 10/9/2016
 */
public class MoveMotors {

    Hardwaresetup robot = new Hardwaresetup();

    public void init(HardwareMap hwMap){
        robot.init(hwMap);
    }

    public void setMotorPower(double x, double y, double z){

        /*
        Guide to motor Powers:
        Left Front: - (y + x + Z)
        Right Front: y - x - z
        Left Rear: y + x - z
        Right Rear: - (y - x + z)
         */

        double LFpower = Range.clip(-(y+x+z),-1,1);
        double RFpower = Range.clip((y-x-z),-1,1);
        double LRpower = Range.clip((y+x-z),-1,1);
        double RRpower = Range.clip(-(y-x+z),-1,2);

        robot.leftFront.setPower(LFpower);
        robot.rightFront.setPower(RFpower);
        robot.leftRear.setPower(LRpower);
        robot.rightRear.setPower(RRpower);

    }

    public double currentDegrees(double x,double y) {

        //double magnitude = (Math.sqrt((x*x)+(-y*-y)));
        //return (Math.asin(x/magnitude)/0.0175);

        double currentRadians = Math.atan2(y, x);
        double degrees = currentRadians * (180/Math.PI);
        if (degrees<0) {
            degrees = 360 + degrees;
        }
        return degrees;

    }

    public void driveForward(){

    }
}
