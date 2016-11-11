package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.LightSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 10/9/2016
 */
public class MoveMotors {

    Hardwaresetup robot = new Hardwaresetup();

    ColorSensors colorSensorsBeacon;
    ColorSensors colorSensorsGroundLeft;
    ColorSensors colorSensorsGroundRight;
    LightSensors lightSensors;
    ProxSensors proxSensors;

    Continue sleep = new Continue();

    ColorSensors.whichColor whichColor;

    float goalDegrees = -1;

    float startingOrientation = -1;

    Telemetry telemetry;

    static final double     countsPerMotorRev    = 1440 ;
    static final double     driveGearReduction    = 1.5 ;
    static final double     wheelDiameterInches   = 4.0 ;
    static final double     countsPerInch         = (countsPerMotorRev * driveGearReduction) / (wheelDiameterInches * Math.PI);

    public void init(HardwareMap hwMap, Telemetry Telemetry){
        robot.init(hwMap);

        telemetry = Telemetry;

        colorSensorsBeacon = new ColorSensors(robot.colorSensorBeacon);
        colorSensorsGroundLeft = new ColorSensors(robot.colorSensorGroundLeft);
        colorSensorsGroundRight = new ColorSensors(robot.colorSensorGroundRight);
        proxSensors = new ProxSensors(robot.proxSensor);
        startEncoders();
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

        float upperBound;
        float lowerBound;

        if (startingOrientation == -1) {
            startingOrientation = getYaw();
        }

        if ((startingOrientation - 1) < 0){
            lowerBound = (360 + (0 - (1 - startingOrientation)));
        } else {
            lowerBound = (startingOrientation - 1);
        }

        if ((startingOrientation + 1) > 360) {
            upperBound = (0 + (360 - (1 + startingOrientation)));
        } else {
            upperBound = (startingOrientation + 1);
        }

        switch (direction) {
            case Forward:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(rightInput);
                if (getYaw() != startingOrientation) {
                    if (getYaw() < 0 ) {
                        int O = 0;
                    }
                }
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

    public boolean gyroTurn(Directions direction, double power, float degrees){

        switch(direction) {
            case TurnLeft:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() - degrees);
                    if (goalDegrees < 0) {
                        goalDegrees = (goalDegrees + 360);
                    }
                }
                if (!(getYaw() > (goalDegrees - 1) && getYaw() < (goalDegrees + 1))) {
                    Drive(direction, power);
                    telemetry.addData("Goal degrees: ", goalDegrees);
                    telemetry.addData("Goal degrees + 1: ", goalDegrees + 1);
                    telemetry.addData("Goal degrees - 1: ", goalDegrees - 1);
                } else {
                    Stop();
                    goalDegrees = -1;
                    return false;
                }
                break;
            case TurnRight:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() + degrees);
                    if (goalDegrees > 360) {
                        goalDegrees = (goalDegrees - 360);
                    }
                }
                if (!(getYaw() > (goalDegrees - 3) && getYaw() < (goalDegrees + 3))) {
                    Drive(direction, power);
                    telemetry.addData("Goal degrees: ", goalDegrees);
                    telemetry.addData("Goal degrees + 3: ", goalDegrees + 3);
                    telemetry.addData("Goal degrees - 3: ", goalDegrees - 3);
                } else {
                    Stop();
                    goalDegrees = -1;
                    return false;
                }
                break;
        }
        return false;

    }

    public void encoderDrive(Directions direction, double power, float inches) {

        double leftInput = -power;
        double rightInput = power;

        switch(direction) {

            case Forward:
                if (getRightInches() < inches && getLeftInches() < inches) {
                    break;
                }
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
        return -robot.leftFront.getCurrentPosition();
    }

    public int getRightEncoder() {
        return robot.rightFront.getCurrentPosition();
    }

    public double getRightInches() {
        return (robot.rightFront.getCurrentPosition() / countsPerInch);
    }

    public double getLeftInches() {
        return (robot.leftFront.getCurrentPosition() / countsPerInch);
    }

    public float getYaw(){
        if(robot.ahrs.getYaw() < 0) {
            return (360 + robot.ahrs.getYaw());
        } else {
            return robot.ahrs.getYaw();
        }
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
            sleep.Sleep(10);
        }
        Stop();
    }





    public void colorWhiteDrive(Directions direction, double power, ColorSensors.whichColorSensor which) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            while(colorSensorsBeacon.isWhite() != ColorSensors.whichColor.WHITE) {
                sleep.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT){
            while(colorSensorsGroundLeft.isWhite() != ColorSensors.whichColor.WHITE) {
                sleep.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            while(colorSensorsGroundRight.isWhite() != ColorSensors.whichColor.WHITE) {
                sleep.Sleep(10);
            }
        }
        Stop();
    }

    public void colorDriveRedBlue(Directions direction, double power, ColorSensors.whichColorSensor which, ColorSensors.whichColor whichColor) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            while (colorSensorsBeacon.greaterColor() == whichColor) {
                sleep.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT) {
            while (colorSensorsGroundLeft.greaterColor() == whichColor) {
                sleep.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            while (colorSensorsGroundRight.greaterColor() == whichColor) {
                sleep.Sleep(10);
            }
        }
        Stop();
    }





    public void ProxDrive(Directions direction, double power) {
        Drive(direction, power);
        while(proxSensors.getDistance() < .5) {
            sleep.Sleep(10);
        }
        Stop();
    }
    public void ProxDrive(Directions direction, double power, double prox) {
        Drive(direction, power);
        while(proxSensors.getDistance() < prox) {
            sleep.Sleep(10);
        }
        Stop();
    }
}
