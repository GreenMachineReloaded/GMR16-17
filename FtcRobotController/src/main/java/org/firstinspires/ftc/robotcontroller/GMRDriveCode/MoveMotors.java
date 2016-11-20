package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.LightSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
public I2cDevice CSBeacon;
    public I2cDevice CSGroundLeft;
    public I2cDevice CSGroundRight;

    public I2cDeviceSynch CRBeacon;
    public I2cDeviceSynch CRGroundLeft;
    public I2cDeviceSynch CRGroundRight;

    CSBeacon = hwMap.i2cDevice.get("CSBeacon");
        CRBeacon = new I2cDeviceSynchImpl(CSBeacon, I2cAddr.create8bit(0x3c), false);
        CRBeacon.engage();

        CSGroundLeft = hwMap.i2cDevice.get("CSGroundLeft");
        CRGroundLeft = new I2cDeviceSynchImpl(CSGroundLeft, I2cAddr.create8bit(0x3c), false);
        CRGroundLeft.engage();

        CSGroundRight = hwMap.i2cDevice.get("CSGroundRight");
        CRGroundRight = new I2cDeviceSynchImpl(CSGroundRight, I2cAddr.create8bit(0x3c), false);
        CRGroundRight.engage();
 */

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

    float startingOrientation = -1;

    Telemetry telemetry;

    static final double     countsPerMotorRev    = 1440 ;
    static final double     driveGearReduction    = 1.5 ;
    static final double     wheelDiameterInches   = 4.0 ;
    static final double     countsPerInch         = (countsPerMotorRev * driveGearReduction) / (wheelDiameterInches * Math.PI);

    Continue sleep = new Continue();

    ColorSensors.whichColor whichColor;

    float goalDegrees = -1;

    boolean canLaunch = true;

    int goalPosition = -1;

    public void init(HardwareMap hwMap, Telemetry Telemetry){
        robot.init(hwMap);

        telemetry = Telemetry;

        colorSensorsBeacon = new ColorSensors(robot.CSBeacon_);
        colorSensorsGroundLeft = new ColorSensors(robot.CSGroundLeft_);
        colorSensorsGroundRight = new ColorSensors(robot.CSGroundRight_);
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
        double RRpower = Range.clip((y+x-z), -1, 1);

        robot.leftFront.setPower(LFpower);
        robot.rightFront.setPower(RFpower);
        robot.leftRear.setPower(LRpower);
        robot.rightRear.setPower(RRpower);

    }

    public void launchControl(boolean leftTrigger) {
        if (leftTrigger) {
            if (canLaunch){
                goalPosition = (getLaunchEncoder() + 1550);
                canLaunch = false;
                telemetry.addData("","");
            }
        }
        if (getLaunchEncoder() < goalPosition) {
            robot.launchMotor.setPower(1);
            telemetry.addData("Goal Position", goalPosition);
        } else {
            robot.launchMotor.setPower(0);
            canLaunch = true;
        }
    }

    public void sweeperControl(boolean rightBumper, double rightTrigger) {
        if(rightBumper) {
            robot.sweeperMotor.setPower(1);
        } else if (rightTrigger > 0) {
            robot.sweeperMotor.setPower(-1);
        } else {
            robot.sweeperMotor.setPower(0);
        }
    }

    public void linearSlideControl(boolean gamepad2A, boolean gamepad2B) {
        if (gamepad2A && !gamepad2B) {
            robot.ballLiftMotor.setPower(0.8);
        } else if (!gamepad2A && gamepad2B) {
            robot.ballLiftMotor.setPower(-0.8);
        } else {
            robot.ballLiftMotor.setPower(0);
        }
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

    public boolean gyroTurn(Directions direction, double power, float degrees){

        switch(direction) {
            case TurnLeft:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() - degrees);
                    if (goalDegrees < 0) {
                        goalDegrees = (goalDegrees + 360);
                    }
                }
                if (!(getYaw() > goalDegrees - 1 && getYaw() < goalDegrees + 1)) {
                    Drive(direction, power);
                    telemetry.addData("Goal degrees", goalDegrees);
                    return true;
                } else {
                    Stop();
                    goalDegrees = -1;
                    return false;
                }
            case TurnRight:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() + degrees);
                    if (goalDegrees > 360) {
                        goalDegrees = (goalDegrees - 360);
                    }
                }
                if (!(getYaw() > (goalDegrees - 3) && getYaw() < (goalDegrees + 3))) {
                    Drive(direction, power);
                    telemetry.addData("Goal degrees", goalDegrees);
                    telemetry.addData("Goal degrees + 3", goalDegrees + 3);
                    telemetry.addData("Goal degrees - 3", goalDegrees - 3);
                    return true;
                } else {
                    Stop();
                    goalDegrees = -1;
                    return false;
                }
        }
        return false;
    }

    public boolean encoderDrive(Directions direction, double power, double inches) {

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
        return false;
    }

    public void startEncoders(){
        robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int getLeftEncoder() { return -robot.leftFront.getCurrentPosition(); }

    public int getRightEncoder() {
        return robot.rightFront.getCurrentPosition();
    }

    public int getLaunchEncoder() { return robot.launchMotor.getCurrentPosition(); }

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

    public double encoderInchesRight() {
        return (getRightEncoder() / countsPerInch);
    }

    public double encoderInchesLeft() {
        return (getLeftEncoder() / countsPerInch);
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
        else {
            telemetry.addData("ERROR NO ENUM WHICH COLOR SENSOR", null);
            telemetry.update();
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
