package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 12/15/2016
 */
@SuppressWarnings("FieldCanBeLocal")

public class Launch {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MOTOR V
    private DcMotor sweeperMotor;
    private DcMotor ballLiftMotor;
    private DcMotor launchMotor;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SERVO V
    private Servo hopperDoorServo;
    private Servo ballLiftServo;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MISS V
    private boolean canLaunch;
    //boolean to test if the launcher can launch again.
    private int goalPosition;
    // ???
    private double timeOfCompletion;
    //???
    private ElapsedTime launchTime;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //calls the second constructor of Launch and passes a reference to the hardware map, telemetry and the 3 string names of the motors and servos in the order sweeper, launcher and door hopper.
    public Launch(HardwareMap hardwareMap, Telemetry Telemetry){new Launch(hardwareMap,Telemetry,"sweepermotor","balllift","launchmotor","ballliftservo","hopperdoorservo");}
    //sets up all the references to the launching motors and servos.
    public Launch(HardwareMap hardwareMap, Telemetry Telemetry, String sweeperMotorStringArg, String ballLiftMotorStringArg, String launchMotorStringArg, String ballLiftServoStringArg,String hopperDoorServoStringArg) {
        //setup for the sweeper
        this.sweeperMotor = hardwareMap.dcMotor.get(sweeperMotorStringArg);
        //setup for all the launching stuff
            //setup for the launching motor
        this.launchMotor = hardwareMap.dcMotor.get(launchMotorStringArg);
            //setup for the ballLiftMotor
        this.ballLiftMotor = hardwareMap.dcMotor.get(ballLiftMotorStringArg);
        //setup for all the servos
            //setup for the ball lifter servo
        this.ballLiftServo = hardwareMap.servo.get(ballLiftServoStringArg);
            //setup for the door servo.
        this.hopperDoorServo = hardwareMap.servo.get(hopperDoorServoStringArg);

        //is this required?
        this.sweeperMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //setups the incoder of the lanuching motor.
        this.launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        canLaunch = true;
        goalPosition = -1;
        timeOfCompletion = 0;
        launchTime = new ElapsedTime();

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LAUNCHING
    //could we just call the other method and give it an arbitrary value?
    public void launchControl(boolean leftBumper) {
        if (leftBumper) {
            if (canLaunch){
                goalPosition = (getLaunchEncoder() + 1680);
                canLaunch = false;
                this.launchMotor.setPower(1);
                this.launchMotor.setTargetPosition(goalPosition);
            }
        }
        if (this.launchMotor.getCurrentPosition() < goalPosition - 20) {
            launcherServoControl(false);
            timeOfCompletion = (launchTime.seconds() + 0.7);
        } else {
            if (launchTime.seconds() < timeOfCompletion) {launcherServoControl(true);}
            else {launcherServoControl(false);}
            canLaunch = true;
        }
    }
    public void launchControl(boolean leftBumper, boolean x) {
        if (leftBumper) {
            if (this.canLaunch){
                this.goalPosition = (getLaunchEncoder() + 1680);
                this.canLaunch = false;
                this.launchMotor.setPower(1);
                this.launchMotor.setTargetPosition(goalPosition);
            }
        }
        if (this.launchMotor.getCurrentPosition() < goalPosition - 20) {
            this.launcherServoControl(false);
            this.timeOfCompletion = (this.launchTime.seconds() + 0.7);
        }else {
            if (this.launchTime.seconds() < this.timeOfCompletion) {launcherServoControl(true);}
            else {
                if(x){this.launcherServoControl(true); }
                else {this.launcherServoControl(false);}
            }
            this.canLaunch = true;
        }
    }
    //could this be private?
    public void launcherServoControl(boolean x) {
        if (x) {this.hopperDoorServo.setPosition(0.43);}
        else {this.hopperDoorServo.setPosition(0.95);}
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LINEAR SLIDE
    public void linearSlideControl(boolean gamepad2A, boolean gamepad2B) {
        if (gamepad2A && !gamepad2B) {this.ballLiftMotor.setPower(0.8);}
        else if (!gamepad2A && gamepad2B) {this.ballLiftMotor.setPower(-0.8);}
        else {this.ballLiftMotor.setPower(0);}
    }
    public void liftControl(boolean dPadUp, boolean dPadDown) {
        if (dPadUp) {this.ballLiftServo.setPosition(0.07);}
        else if (dPadDown) {this.ballLiftServo.setPosition(0.63);}
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SWEEPER
    public void sweeperControl(boolean rightBumper, double rightTrigger) {
        if(rightBumper) {this.sweeperMotor.setPower(1);}
        else if (rightTrigger > 0) {this.sweeperMotor.setPower(-1);}
        else {this.sweeperMotor.setPower(0);}
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENCODER
    public int getLaunchEncoder() {return this.launchMotor.getCurrentPosition();}
}