package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

/**
 * Created by Payton on 1/18/2017
 */
@Autonomous(name="One Beacon Red", group="Beacon Programs")
public class OneBeaconRed extends OpMode {

    private Robot robot;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private int launchNumber = 0;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
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
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 12);
            } else {
                state = CurrentStates.LAUNCH;
                isFinished = false;
            }
        } else if (state == CurrentStates.LAUNCH) {
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launcherServoControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launchControl(false);
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            state = CurrentStates.DIAGONALUPLEFT;
        } else if (state == CurrentStates.DIAGONALUPLEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.DLEFTUP, 0.6, 51);
            } else {
                state = CurrentStates.TEST;
                isFinished = false;
            }
        } else if (state == CurrentStates.TEST) {
            telemetry.addData("Color Sensor Data", robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("Color Sensor Data", robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDRIGHT));
            telemetry.addData("Color Sensor Data", robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.BEACON));
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.driveTrain.stop();
        }
    }
}
