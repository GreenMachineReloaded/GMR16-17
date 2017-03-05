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
    public DcMotor launchMotor;
    private String sweeperMotorStringArg = "sweepermotor";
    private String launchMotorStringArg = "launchmotor";
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SERVO V
    public Servo hopperDoorServo;
    private String hopperDoorServoStringArg = "hopperdoorservo";
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MISC V
    private boolean canLaunch = true;
    //boolean to test if the launcher can launch again.
    private int goalPosition;
    // ???
    private double timeOfCompletion;
    //???
    private ElapsedTime launchTime = new ElapsedTime();

    Telemetry telemetry;

    private String mostRecentCommand;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //calls the second constructor of Launch and passes a reference to the hardware map, telemetry and the 3 string names of the motors and servos in the order sweeper, launcher and door hopper.
    public Launch(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        //setup for the sweeper
        this.sweeperMotor = hardwareMap.dcMotor.get(sweeperMotorStringArg);
        //setup for all the launching stuff
            //setup for the launching motor
        this.launchMotor = hardwareMap.dcMotor.get(launchMotorStringArg);
        //setup for all the servos
            //setup for the door servo.
        this.hopperDoorServo = hardwareMap.servo.get(hopperDoorServoStringArg);

        hopperDoorServo.setPosition(0.75);

        //is this required?
        this.sweeperMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //setups the encoder of the launching motor.
        this.launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        canLaunch = true;
        goalPosition = -1;
        timeOfCompletion = 0;
        launchTime = new ElapsedTime();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// LAUNCHING
    //could we just call the other method and give it an arbitrary value?
    public boolean launchControl(boolean leftBumper) {
        mostRecentCommand = "[launchControl] leftBumper:"+leftBumper;
        if (leftBumper) {
            if (canLaunch){
                goalPosition = (getLaunchEncoder() + 1680);
                canLaunch = false;
                this.launchMotor.setPower(1);
                this.launchMotor.setTargetPosition(goalPosition);
                return false;
            }
        }
        if (this.launchMotor.getCurrentPosition() < goalPosition - 20) {
            launcherServoControl(false);
            timeOfCompletion = (launchTime.seconds() + 0.5);
            return false;
        } else {
            if (launchTime.seconds() < timeOfCompletion) {
                launcherServoControl(true);
                canLaunch = false;
                return false;
            } else {
                launcherServoControl(false);
                canLaunch = true;
                return true;
            }
        }
    }
    public boolean launchControl(boolean leftBumper, boolean x) {
        mostRecentCommand = "[launchControl] leftBumper:"+leftBumper+" x:"+x;
        if (leftBumper) {
            if (this.canLaunch){
                this.goalPosition = (getLaunchEncoder() + 1680);
                this.canLaunch = false;
                this.launchMotor.setPower(1);
                this.launchMotor.setTargetPosition(goalPosition);
                return false;
            }
        }
        if (this.launchMotor.getCurrentPosition() < goalPosition - 20) {
            this.launcherServoControl(false);
            this.timeOfCompletion = (this.launchTime.seconds() + 0.7);
            return false;
        }else {
            if (this.launchTime.seconds() < this.timeOfCompletion) {
                launcherServoControl(true);
                return false;
            } else {
                if(x){
                    this.launcherServoControl(true);
                }
                else {
                    this.launcherServoControl(false);
                }
            }
            this.canLaunch = true;
            return true;
        }
    }
    public void launcherServoControl(boolean x) {

        mostRecentCommand = "[launchServoControl] x:"+x;
        if (x) {this.hopperDoorServo.setPosition(0);}
        else {this.hopperDoorServo.setPosition(0.5);}
        if (x) {
            this.hopperDoorServo.setPosition(0.25);
        }
        else {
            this.hopperDoorServo.setPosition(0.75);
        }

    }

    public void stopLaunch(){
        launchMotor.setPower(0);
        sweeperMotor.setPower(0);
        launcherServoControl(false);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// SWEEPER
    public void sweeperControl(boolean rightBumper, double rightTrigger) {
<<<<<<< HEAD
        mostRecentCommand = "[sweeperControl] rightBumper:"+rightBumper+" rightTrigger:"+rightTrigger;
        if(rightBumper) {this.sweeperMotor.setPower(1);}
        else if (rightTrigger > 0) {this.sweeperMotor.setPower(-1);}
        else {this.sweeperMotor.setPower(0);}
=======
        if (rightBumper) {
            this.sweeperMotor.setPower(1);
        } else if (rightTrigger > 0) {
            this.sweeperMotor.setPower(-1);
        } else {
            this.sweeperMotor.setPower(0);
        }
>>>>>>> refs/remotes/origin/m-Automaintenence
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENCODER
    public int getLaunchEncoder() {mostRecentCommand = "[getLaunchEncoder]";return this.launchMotor.getCurrentPosition();}
    public void fixLauncher(float gamepad1LeftStick) {
        mostRecentCommand = "[fixLauncher] gamepad1LeftStick"+gamepad1LeftStick;
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setPower(gamepad1LeftStick/50);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEBUG
    public String getDebugCommand() {
        return mostRecentCommand;
    }
}