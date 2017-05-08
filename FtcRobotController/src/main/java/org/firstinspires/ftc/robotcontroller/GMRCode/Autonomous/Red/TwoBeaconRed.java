package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Red;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

@Autonomous(name="Two Beacon Red", group="Beacon Programs Red")
public class TwoBeaconRed extends OpMode {
    private Robot robot;
    private GMRColorSensor colorSensor;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private int launches = 0;
    private double startingOrientation;
    private ElapsedTime beaconTime = new ElapsedTime();
    private double beaconServoTime;
    private boolean isStraight = false;

    private final double beaconPushTime = 1.5;

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
        robot.beaconNav.teleOpBeaconPush(false);
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
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 0.5, 20);
            } else {
                state = CurrentStates.COLORFORWARD;
                isFinished = false;
            }
        } else if (state == CurrentStates.COLORFORWARD) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.2, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.COLORBACKWARD;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAIGHTEN) {
            if (!isStraight) {
                isStraight = robot.driveTrain.straighten(startingOrientation);
            } else {
                isStraight = false;
                state = CurrentStates.COLORBACKWARD;
            }
        } else if (state == CurrentStates.COLORBACKWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.1, 0.5);
            } else {
                state = CurrentStates.STRAFELEFT2;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT2) {
            if (!isFinished) {
                //isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 8);
                isFinished = robot.UltrasonicDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 14);
            } else {
                state = CurrentStates.PUSHBEACON;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
                isFinished = false;
            }
        } else if(state == CurrentStates.PUSHBEACON) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushRed();
            } else {
                state = CurrentStates.CHECKCOLOR;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                isFinished = false;
            }
        } else if (state == CurrentStates.CHECKCOLOR) {
            if (robot.beaconNav.checkColor(GMRColorSensor.Color.RED)) {
                state = CurrentStates.STRAFERIGHT;
            } else {
                state = CurrentStates.FIXBEACON;
                beaconServoTime = (beaconTime.seconds() + 5);
            }
        } else if (state == CurrentStates.FIXBEACON) {
            if (beaconServoTime > beaconTime.seconds()) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
            } else if ((beaconServoTime + 1.5) > beaconTime.seconds()) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.EXTENDBOTHPUSHERS);
            } else {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                state = CurrentStates.STRAFERIGHT;
            }
        } else if (state == CurrentStates.STRAFERIGHT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, 0.2, 4);
            } else {
                state = CurrentStates.FORWARD;
                beaconServoTime = (beaconTime.seconds() + 1.5);
                isFinished = false;
            }
        } else if (state == CurrentStates.FORWARD) {
            if (beaconServoTime > beaconTime.seconds()) {
                robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, 0.2);
            } else {
                state = CurrentStates.COLORFORWARD2;
            }
        } else if (state == CurrentStates.COLORFORWARD2) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.2, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.COLORBACKWARD2;
                isFinished = false;
            }
        } else if (state == CurrentStates.COLORBACKWARD2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.1, 0.5);
            } else {
                state = CurrentStates.STRAFELEFT3;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT3) {
            if (!isFinished) {
                //isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 8);
                isFinished = robot.UltrasonicDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 14);
            } else {
                state = CurrentStates.PUSHBEACON2;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
                isFinished = false;
            }
        } else if (state == CurrentStates.PUSHBEACON2) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushRed();
            } else {
                state = CurrentStates.CHECKCOLOR2;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                isFinished = false;
            }
        } else if (state == CurrentStates.CHECKCOLOR2) {
            if (robot.beaconNav.checkColor(GMRColorSensor.Color.RED)) {
                state = CurrentStates.STRAFERIGHT2;
            } else {
                state = CurrentStates.FIXBEACON2;
                beaconServoTime = (beaconTime.seconds() + 5);
            }
        } else if (state == CurrentStates.FIXBEACON2) {
            if (beaconServoTime > beaconTime.seconds()) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
            } else if ((beaconServoTime + 1.5) > beaconTime.seconds()) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.EXTENDBOTHPUSHERS);
            } else {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                state = CurrentStates.STRAFERIGHT2;
            }
        } else if (state == CurrentStates.STRAFERIGHT2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, 0.2, 3);
            } else {
                state = CurrentStates.GYROTURNLEFT;
                isFinished = false;
            }
        } else if (state == CurrentStates.GYROTURNLEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNLEFT, 0.2, 32);
            } else {
                state = CurrentStates.BACKWARD;
                isFinished = false;
                robot.driveTrain.resetEncoders();
            }
        } else if(state == CurrentStates.BACKWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.9, 22);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.PROGRAMEND;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.stopRobot();
        }
        telemetry.addData("Current State", state);
    }
}
