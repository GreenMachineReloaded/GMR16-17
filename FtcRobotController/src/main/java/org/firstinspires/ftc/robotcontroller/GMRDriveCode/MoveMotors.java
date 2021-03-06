package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
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
    double goalEncoderPosition = -1;

    double servoTestPosition = 0.5;

    ElapsedTime launchTime = new ElapsedTime();

    double timeOfCompletion;

    boolean encoderDrive = true;

    int gyroRange = 4;

    public void init(HardwareMap hwMap, Telemetry Telemetry){
        robot.init(hwMap);

        telemetry = Telemetry;

        colorSensorsBeacon = new ColorSensors(robot.colorSensorBeacon);
        colorSensorsGroundLeft = new ColorSensors(robot.colorSensorGroundLeft);
        colorSensorsGroundRight = new ColorSensors(robot.colorSensorGroundRight);
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

    public void launchControl(boolean leftBumper, boolean x) {
        if (leftBumper) {
            if (canLaunch){
                goalPosition = (getLaunchEncoder() + 1680);
                canLaunch = false;
                robot.launchMotor.setPower(1);
                robot.launchMotor.setTargetPosition(goalPosition);
            }
        }

        if (robot.launchMotor.getCurrentPosition() < goalPosition - 20) {
            launcherServoControl(false);
            timeOfCompletion = (launchTime.seconds() + 0.7);
        } else {
            if (launchTime.seconds() < timeOfCompletion) {
                launcherServoControl(true);
            } else {
                if (x) {
                    launcherServoControl(true);
                } else {
                    launcherServoControl(false);
                }
            }
            canLaunch = true;
        }
    }

    public void launchControl(boolean leftBumper) {
        if (leftBumper) {
            if (canLaunch){
                goalPosition = (getLaunchEncoder() + 1680);
                canLaunch = false;
                robot.launchMotor.setPower(1);
                robot.launchMotor.setTargetPosition(goalPosition);
            }
        }

        if (robot.launchMotor.getCurrentPosition() < goalPosition - 20) {
            launcherServoControl(false);
            timeOfCompletion = (launchTime.seconds() + 0.7);
        } else {
            if (launchTime.seconds() < timeOfCompletion) {
                launcherServoControl(true);
            } else {
                    launcherServoControl(false);
            }
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

    public void launcherServoControl(boolean x) {
        if (x) {
            robot.hopperDoorServo.setPosition(0.43);
        } else {
            robot.hopperDoorServo.setPosition(0.95);
        }
    }

    public void liftControl(boolean dPadUp, boolean dPadDown) {
        if (dPadUp) {
            robot.ballLiftServo.setPosition(0.07);
        } else if (dPadDown) {
            robot.ballLiftServo.setPosition(0.63);
        }
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
                if (!(getYaw() > goalDegrees - gyroRange && getYaw() < goalDegrees + gyroRange)) {
                    Drive(direction, power);
                    telemetry.addData("Current Gyro", getYaw());
                    return false;
                } else {
                    telemetry.addData("Current Gyro", getYaw());
                    Stop();
                    goalDegrees = -1;
                    return true;
                }
            case TurnRight:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() + degrees);
                    if (goalDegrees > 360) {
                        goalDegrees = (goalDegrees - 360);
                    }
                }
                if (!(getYaw() > (goalDegrees - gyroRange) && getYaw() < (goalDegrees + gyroRange))) {
                    Drive(direction, power);
                    telemetry.addData("Current Gyro", getYaw());
                    return false;
                } else {
                    telemetry.addData("Current Gyro", getYaw());
                    Stop();
                    goalDegrees = -1;
                    return true;
                }
        }
        return false;
    }

    public boolean encoderDrive(Directions direction, double power, double inches) {

        double leftInput = -power;
        double rightInput = power;

        int combinedEnValue = ((getLeftEncoder() + getRightEncoder()) / 2);

        if (encoderDrive){
            goalEncoderPosition = (combinedEnValue + (inches * countsPerInch));
            encoderDrive = false;
            return encoderDrive;
        } else {

            switch (direction) {

                case Forward:
                    if ((combinedEnValue) < goalEncoderPosition) {
                        Drive(direction, power);
                        telemetry.addData("Current Combined Value", combinedEnValue);
                    } else {
                        encoderDrive = true;
                        Stop();
                        return encoderDrive;
                    }
                    break;
                case Backward:
                    if ((combinedEnValue) > goalEncoderPosition) {
                        Drive(direction, power);
                        telemetry.addData("Current Combined Value", combinedEnValue);
                    } else {
                        encoderDrive = true;
                        Stop();
                        return encoderDrive;
                    }
                    break;
                case StrafeLeft:
                    return encoderDrive;
                case StrafeRight:
                    return encoderDrive;
                case DRightUp:
                    return encoderDrive;
                case DRightDown:
                    return encoderDrive;
                case DLeftUp:
                    return encoderDrive;
                case DLeftDown:
                    return encoderDrive;
                case TurnLeft:
                    return encoderDrive;
                case TurnRight:
                    return encoderDrive;
            }
            return encoderDrive;
        }
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





    public boolean colorWhiteDrive(Directions direction, double power, ColorSensors.whichColorSensor which) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            telemetry.addData("Current Red", colorSensorsBeacon.getRed());
            telemetry.addData("Current Red", colorSensorsBeacon.getRed());
            if(colorSensorsBeacon.isWhite() != ColorSensors.whichColor.WHITE) {
                Stop();
                return true;
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT){
            telemetry.addData("Current Red", colorSensorsGroundLeft.getRed());
            telemetry.addData("Current Red", colorSensorsGroundLeft.getRed());
            if(colorSensorsGroundLeft.isWhite() == ColorSensors.whichColor.WHITE) {
                Stop();
                return true;
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            telemetry.addData("Current Red", colorSensorsGroundRight.getRed());
            telemetry.addData("Current Red", colorSensorsGroundRight.getRed());
            if(colorSensorsGroundRight.isWhite() == ColorSensors.whichColor.WHITE) {
                Stop();
                return true;
            }
        }
        return false;
    }

    public boolean colorDriveRedBlue(Directions direction, double power, ColorSensors.whichColorSensor which, ColorSensors.whichColor whichColor) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            if (colorSensorsBeacon.greaterColor() == whichColor) {
                return true;
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT) {
            if (colorSensorsGroundLeft.greaterColor() == whichColor) {
                return true;
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            if (colorSensorsGroundRight.greaterColor() == whichColor) {

                return true;
            }
        }
        sleep.Sleep(10);
        return false;
    }





    public boolean ProxDrive(Directions direction, double power) {
        Drive(direction, power);
        if(proxSensors.getDistance() > .4) {
            Stop();
            return true;
        }
        return false;
    }
    public boolean ProxDrive(Directions direction, double power, double prox) {
        Drive(direction, power);
        if(proxSensors.getDistance() > prox) {
            Stop();
            return true;
        }
        return false;
    }
}
