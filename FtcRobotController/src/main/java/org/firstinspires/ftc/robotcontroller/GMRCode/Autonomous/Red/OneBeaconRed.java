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
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 11);
                robot.launch.launcherServoControl(false);
            } else if (!isStraight) {
                isStraight = robot.driveTrain.straighten(startingOrientation);
            } else {
                state = CurrentStates.LAUNCH;
                isFinished = false;
                isStraight = false;
            }
        } else if (state == CurrentStates.LAUNCH) {
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launcherServoControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launchControl(false);
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            state = CurrentStates.STRAFELEFT;
        } else if (state == CurrentStates.STRAFELEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 1, 3);
            } else {
                state = CurrentStates.COLORFORWARD;
                isFinished = false;
            }
        } else if (state == CurrentStates.COLORFORWARD) {
            if (!isFinished) {
                isFinished = robot.colorDrive(DriveTrain.Direction.FORWARD, 0.5, BeaconNav.WhichGMRColorSensor.GROUNDLEFT, BeaconNav.Color.BLUE);
            } else {
                state = CurrentStates.PROGRAMEND;
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.driveTrain.stop();
            telemetry.addData("Program End", "");
        }
    }
}
