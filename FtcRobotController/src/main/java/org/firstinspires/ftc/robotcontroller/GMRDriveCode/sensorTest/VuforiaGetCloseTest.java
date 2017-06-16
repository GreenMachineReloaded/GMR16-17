package org.firstinspires.ftc.robotcontroller.GMRDriveCode.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous(name="get close test", group="Tests")
public class VuforiaGetCloseTest extends OpMode {
    private Robot robot;
    CurrentStates whichState;
    boolean isFinished;
    GMRColorSensor colorSensor;
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        whichState = CurrentStates.FORWARD;
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        robot.beaconNav.teleOpBeaconPush(false);
        isFinished = false;
    }
    public void start() {}
    public void loop() {
        telemetry.addData("X", robot.robotEyes.getArrayXYZ()[0]);
        telemetry.addData("Y", robot.robotEyes.getArrayXYZ()[1]);
        telemetry.addData("Z", robot.robotEyes.getArrayXYZ()[2]);
        telemetry.addData("Current Visual", robot.robotEyes.getImageOfCurrentVisualBeacon());
        if (!isFinished) {
                isFinished = robot.getCloseBeacon(90, .2);
                robot.launch.launcherServoControl(false);
        }
    }
}