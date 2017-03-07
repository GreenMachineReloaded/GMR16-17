package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

/**
 * Created by Payton on 3/5/2017
 */
@Autonomous(name="Ramp Shot Blue", group="Ramp Shot")
public class BlueRampShot extends OpMode {

    private Robot robot;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private double startingOrientation;
    private int launches = 0;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
        startingOrientation = robot.driveTrain.getYaw();
        robot.beaconNav.teleOpBeaconPush(false);
    }

    @Override
    public void start() {
        robot.beaconNav.teleOpBeaconPush(false);
    }

    @Override
    public void loop() {

        if (state == CurrentStates.ENCODERFORWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.3, 19);
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
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.3, 60);
            } else {
                state = CurrentStates.ENCODERFORWARD2;
                isFinished = false;
                robot.driveTrain.resetEncoders();
            }
        } else if (state == CurrentStates.ENCODERFORWARD2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.3, 30);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.PROGRAMEND;
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.stopRobot();
            telemetry.addData("Program End", "");
            robot.beaconNav.teleOpBeaconPush(false);
        }

    }
}
