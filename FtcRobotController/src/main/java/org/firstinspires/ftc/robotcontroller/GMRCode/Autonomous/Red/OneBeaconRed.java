package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

/**
 * Created by Payton on 1/18/2017
 */
@Autonomous(name="One Beacon Red", group="Beacon Programs")
public class OneBeaconRed extends OpMode {

    private Robot robot;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private boolean isStraight = false;

    private int launches = 0;

    private double startingOrientation;

    private Continue sleep = new Continue();

    private int launchNumber = 0;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
        startingOrientation = robot.driveTrain.getYaw();
    }

    @Override
    public void start() {
        telemetry.addData("Starting Servos", "");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addData("Program Start", "");
        if (state == CurrentStates.ENCODERFORWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 10);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.ENCODERBACKWARD;
                isFinished = false;
            }
        } else if (state == CurrentStates.LAUNCH) {
            //if (launches == 1 && isFinished) {
            //   robot.launch.launchControl(false);
            state = CurrentStates.ENCODERBACKWARD;
            //} else if (!isFinished) {
            //   isFinished = robot.launch.launchControl(true);
            //   sleep.Sleep(1.5);
            //} else {
            // launches += 1;
            isFinished = false;
            //}
        } else if (state == CurrentStates.ENCODERBACKWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.5, 3);
            } else {
                state = CurrentStates.STRAFELEFT;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 0.75, 18);
            } else if (!isStraight) {
                isStraight = robot.driveTrain.straighten(startingOrientation);
            } else {
                state = CurrentStates.COLORFORWARD;
                isFinished = false;
                isStraight = false;
            }
        } else if (state == CurrentStates.COLORFORWARD) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.1, BeaconNav.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.ENCODERBACKWARD2;
                isFinished = false;
            }
        } else if (state == CurrentStates.ENCODERBACKWARD2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.8, 0.2);
            } else if (!isStraight) {
                isStraight = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.1, 15);
            } else {
                state = CurrentStates.STRAFELEFT2;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT2) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 0.6, 12);
            } else if (!isStraight) {
                isStraight = robot.driveTrain.straighten(startingOrientation);
            } else {
                state = CurrentStates.PROGRAMEND;
                isFinished = false;
                isStraight = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.driveTrain.stop();
            telemetry.addData("Current Blue", robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            //telemetry.addData("Program End", "");
        }
    }
}
