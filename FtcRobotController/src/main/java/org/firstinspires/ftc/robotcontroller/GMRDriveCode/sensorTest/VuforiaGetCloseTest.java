package org.firstinspires.ftc.robotcontroller.GMRDriveCode.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous(name="get close test", group="Tests")
public class VuforiaGetCloseTest extends OpMode {
    private Robot robot;
    CurrentStates whichState;
    boolean isFinished;
    public void init() {

    }
    public void start() {robot = new Robot(hardwareMap, telemetry); whichState = CurrentStates.FORWARD;}
    public void loop() {
        telemetry.addData("X", robot.robotEyes.getArrayXYZ()[0]);
        telemetry.addData("Y", robot.robotEyes.getArrayXYZ()[1]);
        telemetry.addData("Z", robot.robotEyes.getArrayXYZ()[2]);
        telemetry.addData("Current Visual", robot.robotEyes.getImageOfCurrentVisualBeacon());
        if(whichState == CurrentStates.FORWARD) {
            if (!isFinished) {
                isFinished = robot.getCloseBeacon(80, .1);
                robot.launch.launcherServoControl(false);
            } else {
                whichState = CurrentStates.ELSE;
                isFinished = false;
            }
        }
        else {
            if (!isFinished) {
                isFinished = robot.goAwayBeacon(160, .1);
                robot.launch.launcherServoControl(false);
            } else {
                whichState = CurrentStates.FORWARD;
                isFinished = false;
            }
        }
    }
}