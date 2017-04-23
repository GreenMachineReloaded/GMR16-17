package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Blue;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;

@Autonomous(name="One Beacon Blue", group="Beacon Programs Blue")

public class OneBeaconBlue extends OpMode {

    private Robot robot;
    private GMRColorSensor colorSensor;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private boolean isStraight = false;
    private int launches = 0;
    private double startingOrientation;
    private int launchNumber = 0;

    private double startingGyro;
    private boolean isGyroWorking = false;

    private ElapsedTime beaconTime = new ElapsedTime();
    private double beaconServoTime;

    private ElapsedTime gyroCheck = new ElapsedTime();
    private double gyroCheckTime;

    private int iterations = 0;

    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
        startingOrientation = robot.driveTrain.getYaw();
        beaconTime.reset();
        telemetry.addData("Gyro Done Calibrating", robot.driveTrain.checkGyro());
    }

    public void start() {
        telemetry.addData("Starting Servos", "");
        //startingGyro = robot.driveTrain.getYaw();
        startingGyro = robot.driveTrain.getYaw();
        telemetry.update();
        beaconTime.reset();
        gyroCheck.reset();
        gyroCheckTime = (gyroCheck.seconds() + 4);
    }

    public void loop() {

        //if (startingGyro == robot.driveTrain.getYaw() && !isGyroWorking && (gyroCheckTime > gyroCheck.seconds())) {
        if (gyroCheckTime > gyroCheck.seconds()) {
            if (startingGyro == robot.driveTrain.getYaw() && !isGyroWorking) {
                isGyroWorking = false;
            } else {
                isGyroWorking = true;
            }
        }

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
                if (isGyroWorking) {
                    state = CurrentStates.GYROTURNRIGHT;
                } else {
                    state = CurrentStates.ENCODERTURN;
                }
                //state = CurrentStates.GYROTURNRIGHT;
                isFinished = false;
            } else if (!isFinished) {
                isFinished = robot.launch.launchControl(true);
            } else {
                launches += 1;
                isFinished = false;
            }
        } else if (state == CurrentStates.GYROTURNRIGHT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.2, 179);
            } else {
                state = CurrentStates.STRAFELEFT;
                isFinished = false;
                robot.driveTrain.resetEncoders();
            }
        } else if (state == CurrentStates.ENCODERTURN) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.TURNRIGHT, 0.2, 22);
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
                isFinished = robot.whiteDrive(DriveTrain.Direction.BACKWARD, 0.1, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.STRAFELEFT2;
                robot.driveTrain.resetEncoders();
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT2) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.2, 4);
            } else {
                state = CurrentStates.PUSHBEACON;
                iterations += 1;
                isFinished = false;
                beaconServoTime = (beaconTime.seconds() + 2);
            }
        } else if(state == CurrentStates.PUSHBEACON) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushBlue();
                telemetry.addData("Pushing Beacon", "");
            } else {
                state = CurrentStates.PROGRAMEND;
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.stopRobot();
            telemetry.addData("Program End", "");
        }
        telemetry.addData("Current State", state);
        telemetry.addData("Is the Gyro working", isGyroWorking);
    }
}
