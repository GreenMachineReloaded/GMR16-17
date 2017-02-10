package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

/**
 * Created by Payton on 1/18/2017
 */
@Autonomous(name="Two Beacon Red", group="Beacon Programs")
public class TwoBeaconRed extends OpMode {

    private Robot robot;
    private GMRColorSensor colorSensor;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private boolean isStraight = false;

    private int launches = 0;

    private double startingOrientation;

    private Continue sleep = new Continue();

    private int launchNumber = 0;

    private ElapsedTime beaconTime = new ElapsedTime();
    private double beaconServoTime;

    @Override
    public void init() {
        beaconTime.reset();
        try {
            robot = new Robot(hardwareMap, telemetry);
        } catch(NullPointerException e) {
            telemetry.addData("Robot Failed", "");
        }
        try {
            colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        } catch(NullPointerException e) {
            telemetry.addData("ColorSensor Failed", "");
        }
        try {
            startingOrientation = robot.driveTrain.getYaw();
        } catch(NullPointerException e) {
            telemetry.addData("Robot Failed Again", "");
        }
        telemetry.addData("Gyro Done Calibrating", robot.driveTrain.checkGyro());
    }

    @Override
    public void start() {
        telemetry.addData("Starting Servos", "");
        telemetry.update();
        beaconTime.reset();
    }

    @Override
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
                state = CurrentStates.STRAFELEFT;
                isFinished = false;
            } else if (!isFinished) {
                isFinished = robot.launch.launchControl(true);
            } else {
                launches += 1;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 0.5, 13);
            } else {
                state = CurrentStates.COLORFORWARD;
                isFinished = false;
                isStraight = false;
            }
        } else if (state == CurrentStates.COLORFORWARD) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.1, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.STRAFELEFT2;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT2) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 4);
            } else {
                state = CurrentStates.PUSHBEACON;
                isFinished = false;
                isStraight = false;
                beaconServoTime = (beaconTime.seconds() + 2);
            }
        } else if(state == CurrentStates.PUSHBEACON) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushRed();
            } else {
                state = CurrentStates.STRAFERIGHT;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFERIGHT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, 0.2, 4);
            } else {
                state = CurrentStates.STRAIGHTEN;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAIGHTEN) {
            if (!isFinished) {
                isFinished = robot.driveTrain.straighten(startingOrientation);
            } else {
                state = CurrentStates.FORWARD;
                beaconServoTime = (beaconTime.seconds() + 1);
                isFinished = false;
            }
        } else if (state == CurrentStates.FORWARD) {
            if (beaconServoTime > beaconTime.seconds()) {
                robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, 0.1);
            } else {
                state = CurrentStates.COLORFORWARD2;
            }
        } else if (state == CurrentStates.COLORFORWARD2) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.1, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.STRAFELEFT3;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT3) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 1);
            } else {
                state = CurrentStates.PUSHBEACON2;
                isFinished = false;
                beaconServoTime = (beaconTime.seconds() + 2);
            }
        } else if (state == CurrentStates.PUSHBEACON2) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushRed();
            } else {
                state = CurrentStates.PROGRAMEND;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.stopRobot();
        }
        telemetry.addData("Current State", state);
    }
}
