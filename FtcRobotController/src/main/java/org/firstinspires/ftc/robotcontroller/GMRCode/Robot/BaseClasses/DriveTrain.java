package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class DriveTrain {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MOVE V
    //objects for all the drive train motors
        //objects for controlling the front two motors
    private DcMotor leftFront;
            //object for controlling the left front motor
    private DcMotor rightFront;
            //object for controlling the right front motor

        //objects for controlling the rear two motors
    private DcMotor leftRear;
            //object for controlling the left rear motor
    private DcMotor rightRear;
            //object for controlling the right rear motor

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// GYRO V
    private AHRS gyro;
    //gyro sensor

    //variables for gyro sensor
    private float goalDegrees;
        // ???
    private int goalPosition;
        // ???
    private int gyroRange;
        // ???
    private static final double     countsPerMotorRev    = 1440 ;
    private static final double     driveGearReduction    = 1.5 ;
    private static final double     wheelDiameterInches   = 4.0 ;
    private static final double     countsPerInch         = (countsPerMotorRev * driveGearReduction) / (wheelDiameterInches * Math.PI);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SETUP V
    private String LeftFront = "leftfront";
    private String RightFront = "rightfront";
    private String LeftRear = "leftrear";
    private String RightRear = "rightrear";
    private final int gyroPort = 0;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MISS V
    private Telemetry telemetry;        //do we need this?
    //object for reference (telemetry)
    private boolean encoderDrive;
    private double goalEncoderPosition;
    private double goalLeftPosition;
    private double goalRightPosition;

    private double currentGyro;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //calls the second constructor of DriveTrain and passes a reference to the hardware map, telemetry, the 4 string names of the motors in the order left front, right front, left back, right back and the port reference to the gyro.
    public DriveTrain(HardwareMap hardwareMap, Telemetry telemetry) {
        telemetry.addData("DriveTrain Startup", "Beginning");
        telemetry.update();
        //setup for all the motors.
            //setup for all the front motors.
        this.leftFront = hardwareMap.dcMotor.get(LeftFront);
                //setup for the left front motor.
        this.rightFront = hardwareMap.dcMotor.get(RightFront);
                //setup for the right front motor.
            //setup for all the back motors.
        this.leftRear = hardwareMap.dcMotor.get(LeftRear);
                //setup for the left back motor.
        this.rightRear = hardwareMap.dcMotor.get(RightRear);
                //setup for the right back motor.
        //sets all the motors power to zero.
        //do we need this?
        this.leftFront.setPower(0);
        this.rightFront.setPower(0);
        this.leftRear.setPower(0);
        this.rightRear.setPower(0);
        //gyro sensor setup.
        this.gyro = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"), gyroPort, AHRS.DeviceDataType.kProcessedData);
            //sets up the gyro sensor.
        this.gyro.zeroYaw();
            //sets the gyro sensors position to zero.
        //miss setup

        this.goalDegrees = -1;
        // ???
        this.goalPosition = -1;
        // ???
        this.gyroRange = 4;

        this.telemetry = telemetry;
        //do we need this?
        this.encoderDrive = true;
        this.goalEncoderPosition = -1;
        telemetry.addData("DriveTrain Startup", "End");
        telemetry.update();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//     MOVE
    public void setMotorPower(double x, double y, double z){
        /*
        Guide to motor Powers:
        Left Front: - (y + x + Z)
        Right Front: y - x - z
        Left Rear: y + x - z
        Right Rear: - (y - x + z)
         */
//        double LFpower = Range.clip(-(y+x+z),-1,1);
//        double RFpower = Range.clip((y-x-z),-1,1);
//        double LRpower = Range.clip(-(y-x+z),-1,1);
//        double RRpower = Range.clip((y+x-z), -1, 1);
        this.leftFront.setPower(Range.clip(-(y+x+z),-1,1));
        this.rightFront.setPower(Range.clip((y-x-z),-1,1));
        this.leftRear.setPower(Range.clip(-(y-x+z),-1,1));
        this.rightRear.setPower(Range.clip((y+x-z), -1, 1));
    }
    public void Drive(Direction direction, double power){
        switch (direction) {
            case FORWARD:
                this.leftFront.setPower(-power);
                this.rightFront.setPower(power);
                this.leftRear.setPower(-power);
                this.rightRear.setPower(power);
                break;
            case BACKWARD:
                this.leftFront.setPower(power);
                this.rightFront.setPower(-power);
                this.leftRear.setPower(power);
                this.rightRear.setPower(-power);
                break;
            case STRAFELEFT:
                this.leftFront.setPower(power);
                this.rightFront.setPower(power);
                this.leftRear.setPower(-power);
                this.rightRear.setPower(-power);
                break;
            case STRAFERIGHT:
                this.leftFront.setPower(-power);
                this.rightFront.setPower(-power);
                this.leftRear.setPower(power);
                this.rightRear.setPower(power);
                break;
            case DLEFTUP:
                this.leftFront.setPower(0);
                this.rightFront.setPower(power);
                this.leftRear.setPower(-power);
                this.rightRear.setPower(0);
                break;
            case DLEFTDOWN:
                this.leftFront.setPower(0);
                this.rightFront.setPower(-power);
                this.leftRear.setPower(power);
                this.rightRear.setPower(0);
                break;
            case DRIGHTUP:
                this.leftFront.setPower(-power);
                this.rightFront.setPower(0);
                this.leftRear.setPower(0);
                this.rightRear.setPower(power);
                break;
            case DRIGHTDOWN:
                this.leftFront.setPower(power);
                this.rightFront.setPower(0);
                this.leftRear.setPower(0);
                this.rightRear.setPower(-power);
                break;
            case TURNLEFT:
                this.leftFront.setPower(power);
                this.rightFront.setPower(power);
                this.leftRear.setPower(power);
                this.rightRear.setPower(power);
                break;
            case TURNRIGHT:
                this.leftFront.setPower(-power);
                this.rightFront.setPower(-power);
                this.leftRear.setPower(-power);
                this.rightRear.setPower(-power);
                break;
        }
    }
    public void stop(){
        this.leftFront.setPower(0);
        this.leftRear.setPower(0);
        this.rightFront.setPower(0);
        this.rightRear.setPower(0);
    }
    public boolean encoderDrive(Direction direction, double power, double inches) {

        double leftInput = -power;
        double rightInput = power;

        int combinedEnValue = ((getLeftEncoder() + getRightEncoder()) / 2);

        if (encoderDrive){
            currentGyro = getYaw();
            goalEncoderPosition = (combinedEnValue + (inches * countsPerInch));
            goalLeftPosition = (getLeftEncoder() + (inches * countsPerInch));
            goalRightPosition = (getRightEncoder() + (inches * countsPerInch));
            encoderDrive = false;
            return encoderDrive;
        } else {
            switch (direction) {

                case FORWARD:
                    if ((combinedEnValue) < goalEncoderPosition) {
                        Drive(direction, power);
                        telemetry.addData("Current Combined Value", combinedEnValue);
                    } else {
                        encoderDrive = true;
                        goalEncoderPosition = -1;
                        stop();
                        return encoderDrive;
                    }
                    break;
                case BACKWARD:
                    if ((combinedEnValue) > goalEncoderPosition) {
                        Drive(direction, power);
                        telemetry.addData("Current Combined Value", combinedEnValue);
                    } else {
                        encoderDrive = true;
                        goalEncoderPosition = -1;
                        stop();
                        return encoderDrive;
                    }
                    break;
                case STRAFELEFT:
                    return encoderDrive;
                case STRAFERIGHT:
                    return encoderDrive;
                case DRIGHTUP:
                    return encoderDrive;
                case DRIGHTDOWN:
                    return encoderDrive;
                case DLEFTUP:
                    if (getRightEncoder() < goalRightPosition) {
                        Drive(direction, power);
                        if (getYaw() < currentGyro) {
                            leftFront.setPower(-power);
                        } else {
                            leftFront.setPower(0);
                        }
                        telemetry.addData("Current Right Encoder Value", getRightEncoder());
                        telemetry.addData("Current Yaw", getYaw());
                    } else {
                        encoderDrive = true;
                        goalEncoderPosition = -1;
                        stop();
                        return encoderDrive;
                    }
                    return encoderDrive;
                case DLEFTDOWN:
                    return encoderDrive;
                case TURNLEFT:
                    return encoderDrive;
                case TURNRIGHT:
                    return encoderDrive;
            }
            return encoderDrive;
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//   GYRO
    public boolean gyroTurn(Direction direction, double power, float degrees){
        switch(direction) {
            case TURNLEFT:
                if (goalDegrees == -1) {
                    goalDegrees = (this.getYaw() - degrees);
                    if (goalDegrees < 0) {
                        goalDegrees = (goalDegrees + 360);
                    }
                }
                if (!(this.getYaw() > (goalDegrees - gyroRange) && this.getYaw() < (goalDegrees + gyroRange))) {
                    Drive(direction, power);
                    return false;
                } else {
                    this.stop();
                    goalDegrees = -1;
                    return true;
                }
            case TURNRIGHT:
                if (goalDegrees == -1) {
                    goalDegrees = (this.getYaw() + degrees);
                    if (goalDegrees > 360) {
                        goalDegrees = (goalDegrees - 360);
                    }
                }
                if (!(this.getYaw() > (goalDegrees - gyroRange) && this.getYaw() < (goalDegrees + gyroRange))) {
                    Drive(direction, power);
                    return false;
                } else {
                    this.stop();
                    goalDegrees = -1;
                    return true;
                }
        }
        return false;
    }
    public float getYaw(){
        if(this.gyro.getYaw() < 0) {return (360 + this.gyro.getYaw());}
        else {return this.gyro.getYaw();}
    }

    public void experimentalDrive(double x, double y, double z){
        /*
        Guide to motor Powers:
        Left Front: - (y + x + Z)
        Right Front: y - x - z
        Left Rear: y + x - z
        Right Rear: - (y - x + z)
         */
//        double LFpower = Range.clip(-(y+x+z),-1,1);
//        double RFpower = Range.clip((y-x-z),-1,1);
//        double LRpower = Range.clip(-(y-x+z),-1,1);
//        double RRpower = Range.clip((y+x-z), -1, 1);

        double forwrd = x; /* Invert stick X axis */
        double strafe = y;

        double pi = Math.PI;

/* Adjust Joystick X/Y inputs by navX MXP yaw angle */

        double gyro_radians = getYaw() * pi/180;
        double temp = forwrd * cos(gyro_radians) +
                strafe * sin(gyro_radians);
        strafe = -forwrd * sin(gyro_radians) +
                strafe * cos(gyro_radians);
        forwrd = temp;

/* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
/* rotated by the gyro angle, and can be sent to drive system */

        this.leftFront.setPower(Range.clip(-(forwrd+strafe+z),-1,1));
        this.rightFront.setPower(Range.clip((forwrd-strafe-z),-1,1));
        this.leftRear.setPower(Range.clip(-(forwrd-strafe+z),-1,1));
        this.rightRear.setPower(Range.clip((forwrd+strafe-z), -1, 1));
    }

    public double currentDegrees(double x,double y) {
        //double magnitude = (Math.sqrt((x*x)+(-y*-y)));
        //return (Math.asin(x/magnitude)/0.0175);

        //double currentRadians = (Math.atan2(y, x));
        //double degrees = (currentRadians * (180/Math.PI));
        //if (degrees<0) {degrees = 360 + degrees;}
        //return degrees;
        if (((Math.atan2(y, x)) * (180/Math.PI))<0) {return (360 + ((Math.atan2(y, x)) * (180/Math.PI)));}
        else {return ((Math.atan2(y, x)) * (180/Math.PI));}
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  ENCODER
    public int getLeftEncoder() {return -this.leftFront.getCurrentPosition();}
    public int getRightEncoder() {return this.rightFront.getCurrentPosition();}
//    countsPerMotorRev    = 1440 ;
//    driveGearReduction    = 1.5 ;
//    wheelDiameterInches   = 4.0 ;
    public double encoderInchesRight() {return (this.getRightEncoder() / (1440 * 1.5) / (4 * Math.PI));}
    public double encoderInchesLeft() {return (this.getLeftEncoder() / (1440 * 1.5) / (4 * Math.PI));}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  ENUMS
    public enum Direction{FORWARD,BACKWARD,STRAFELEFT,STRAFERIGHT,DRIGHTUP,DRIGHTDOWN,DLEFTUP,DLEFTDOWN,TURNLEFT,TURNRIGHT}
}