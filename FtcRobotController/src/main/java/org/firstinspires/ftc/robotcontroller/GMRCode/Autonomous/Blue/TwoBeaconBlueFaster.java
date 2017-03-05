package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Blue;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

@Autonomous(name="Two Beacon Blue", group="Beacon Programs")

public class TwoBeaconBlueFaster extends OpMode {

    private Robot robot;
    private GMRColorSensor colorSensor;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private boolean isStraight = false;
    private int launches = 0;
    private double startingOrientation;
<<<<<<< HEAD
    private int launchNumber = 0;
=======

    private boolean hasStrafed = false;

    private final double beaconPushTime = 1.5;
>>>>>>> refs/remotes/origin/m-Automaintenence

    private ElapsedTime beaconTime = new ElapsedTime();
    private double beaconServoTime;

    private int iterations = 0;

    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
        startingOrientation = robot.driveTrain.getYaw();
        beaconTime.reset();
//        telemetry.addData("Gyro Done Calibrating", robot.driveTrain.checkGyro());
//        while (robot.driveTrain.checkGyro()) {
//            count += 1;
//            telemetry.addData("Gyro Done Calibrating", robot.driveTrain.checkGyro());
//            telemetry.addData("Count", count);
//            telemetry.update();
//        }
        robot.beaconNav.teleOpBeaconPush(false);
    }

    public void start() {
        telemetry.addData("Starting Servos", "");
        telemetry.update();
        beaconTime.reset();
        robot.beaconNav.teleOpBeaconPush(false);
    }

    public void loop() {
        if (state == CurrentStates.ENCODERFORWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.3, 13);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.LAUNCH;
                isFinished = false;
            }
        } else if (state == CurrentStates.LAUNCH) {
            if ((launches >= 1) && isFinished) {
                robot.launch.launchControl(false);
                state = CurrentStates.GYROTURNRIGHT;
                isFinished = false;
            } else if (!isFinished) {
                isFinished = robot.launch.launchControl(true);
            } else {
                launches += 1;
                isFinished = false;
            }
        } else if (state == CurrentStates.GYROTURNRIGHT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.3, 179);
            } else {
                state = CurrentStates.STRAFELEFT;
                isFinished = false;
                robot.driveTrain.resetEncoders();
            }
        } else if (state == CurrentStates.STRAFELEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 0.5, 20);
            } else {
                state = CurrentStates.COLORFORWARD;
                isFinished = false;
                isStraight = false;
            }
        } else if (state == CurrentStates.COLORFORWARD) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.BACKWARD, 0.2, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.STRAFELEFT2;
                robot.driveTrain.resetEncoders();
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT2) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 5);
            } else {
                state = CurrentStates.PUSHBEACON;
                iterations += 1;
                isFinished = false;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
            }
        } else if(state == CurrentStates.PUSHBEACON) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushBlue();
            } else {
                state = CurrentStates.STRAFERIGHT;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                robot.driveTrain.resetEncoders();
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFERIGHT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, 0.2, 3);
            } else {
                state = CurrentStates.BACKWARD;
                beaconServoTime = (beaconTime.seconds() + 1);
                hasStrafed = true;
                isFinished = false;
            }
        }  else if (state == CurrentStates.BACKWARD) {
            if (beaconServoTime > beaconTime.seconds()) {
                robot.driveTrain.Drive(DriveTrain.Direction.BACKWARD, 0.2);
            } else {
                state = CurrentStates.COLORFORWARD2;
            }
        } else if (state == CurrentStates.COLORFORWARD2) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.BACKWARD, 0.2, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.STRAFELEFT3;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT3) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 5);
            } else {
                state = CurrentStates.PUSHBEACON2;
                isFinished = false;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
            }
        } else if (state == CurrentStates.PUSHBEACON2) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushBlue();
            } else {
                state = CurrentStates.STRAFERIGHT2;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFERIGHT2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, 0.2, 3);
            } else {
                state = CurrentStates.GYROTURNRIGHT2;
                isFinished = false;
            }
        } else if (state == CurrentStates.GYROTURNRIGHT2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.2, 45);
            } else {
                state = CurrentStates.FORWARD;
                isFinished = false;
                robot.driveTrain.resetEncoders();
            }
        } else if(state == CurrentStates.FORWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.9, 22);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.PROGRAMEND;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                robot.launch.sweeperControl(false, 1);
            } else {
                robot.stopRobot();
            }
            robot.stopRobot();
            telemetry.addData("Program End", "");
            robot.beaconNav.teleOpBeaconPush(false);
        }
        telemetry.addData("Current State", state);
        telemetry.addData("Has Strafed", hasStrafed);
    }
}
