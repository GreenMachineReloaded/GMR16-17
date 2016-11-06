package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.SensorObjects.LightSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 10/9/2016
 */
public class MoveMotors {

    Telemetry telemetry;

    Hardwaresetup robot = new Hardwaresetup();

    ColorSensors colorSensorsBeacon;
    ColorSensors colorSensorsGroundLeft;
    ColorSensors colorSensorsGroundRight;
    LightSensors lightSensors;
    ProxSensors proxSensors;

    Continue c = new Continue();

    ColorSensors.whichColor whichColor;

    public void init(HardwareMap hwMap){
        robot.init(hwMap);

        colorSensorsBeacon = new ColorSensors(robot.colorSensorBeacon);
        colorSensorsGroundLeft = new ColorSensors(robot.colorSensorGroundLeft);
        colorSensorsGroundRight = new ColorSensors(robot.colorSensorGroundRight);
        lightSensors = new LightSensors(robot.lightSensor);
        proxSensors = new ProxSensors(robot.proxSensor);
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
        double LRpower = Range.clip(-(y-x+z),-1,1);
        double RRpower = Range.clip((y + x - z), -1, 1);

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

    public void Drive(Directions direction, double power){

        double leftInput = -power;
        double rightInput = power;

        switch (direction) {
            case Forward:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(rightInput);
                break;
            case Backward:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(-rightInput);
                break;
            case StrafeLeft:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(-rightInput);
                break;
            case StrafeRight:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(rightInput);
                break;
            case DLeftUp:
                robot.leftFront.setPower(0);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(0);
                break;
            case DLeftDown:
                robot.leftFront.setPower(0);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(0);
                break;
            case DRightUp:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightRear.setPower(rightInput);
                break;
            case DRightDown:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightRear.setPower(-rightInput);
                break;
            case TurnLeft:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(rightInput);
                break;
            case TurnRight:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(-rightInput);
                break;
        }

    }

    public void gyroTurn(Directions direction, double power, float degrees){

        double leftInput = -power;
        double rightInput = power;

        switch(direction) {
            case TurnLeft:
                if(-robot.ahrs.getYaw() < degrees){
                    robot.leftFront.setPower(-leftInput);
                    robot.rightFront.setPower(rightInput);
                    robot.leftRear.setPower(-leftInput);
                    robot.rightRear.setPower(rightInput);
                } else {
                    Stop();
                }
                break;
            case TurnRight:
                if(robot.ahrs.getYaw() < degrees) {
                    robot.leftFront.setPower(leftInput);
                    robot.rightFront.setPower(-rightInput);
                    robot.leftRear.setPower(leftInput);
                    robot.rightRear.setPower(-rightInput);
                } else {
                    Stop();
                }
                break;
        }

    }

    public void encoderDriveC(Directions direction, double power) {

        double leftInput = -power;
        double rightInput = power;

        switch(direction) {

            case Forward:
                break;
            case Backward:
                break;
            case StrafeLeft:
                break;
            case StrafeRight:
                break;
            case DRightUp:
                break;
            case DRightDown:
                break;
            case DLeftUp:
                break;
            case DLeftDown:
                break;
            case TurnLeft:
                break;
            case TurnRight:
                break;
        }
    }

    public void startEncoders(){
        robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int getLeftEncoder() {
        return robot.leftFront.getCurrentPosition();
    }

    public int getRightEncoder() {
        return robot.rightFront.getCurrentPosition();
    }

    public float getYaw(){
        return robot.ahrs.getYaw();
    }

    public void Stop(){
        robot.leftFront.setPower(0);
        robot.leftRear.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightRear.setPower(0);
    }



    public void lightDrive(Directions direction, double power) {
        Drive(direction, power);
        while(lightSensors.WhichColor() != ColorSensors.whichColor.WHITE) {
            c.Sleep(10);
        }
        Stop();
    }





    public void colorWhiteDrive(Directions direction, double power, ColorSensors.whichColorSensor which) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            while(colorSensorsBeacon.isWhite() != ColorSensors.whichColor.WHITE) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT){
            while(colorSensorsGroundLeft.isWhite() != ColorSensors.whichColor.WHITE) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            while(colorSensorsGroundRight.isWhite() != ColorSensors.whichColor.WHITE) {
                c.Sleep(10);
            }
        }
        else {
            telemetry.addData("ERROR NO ENUM WHICH COLOR SENSOR", null);
            telemetry.update();
        }
        Stop();
    }

    public void colorDriveRedBlue(Directions direction, double power, ColorSensors.whichColorSensor which, ColorSensors.whichColor whichColor) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            while (colorSensorsBeacon.greaterColor() == whichColor) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT) {
            while (colorSensorsGroundLeft.greaterColor() == whichColor) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            while (colorSensorsGroundRight.greaterColor() == whichColor) {
                c.Sleep(10);
            }
        }
        else {
            telemetry.addData("ERROR NO ENUM WHICH COLOR SENSOR", null);
            telemetry.update();
        }
        Stop();
    }





    public void ProxDrive(Directions direction, double power) {
        Drive(direction, power);
        while(proxSensors.getDistance() < .5) {
            c.Sleep(10);
        }
        Stop();
    }
    public void ProxDrive(Directions direction, double power, double prox) {
        Drive(direction, power);
        while(proxSensors.getDistance() < prox) {
            c.Sleep(10);
        }
        Stop();
    }
}
