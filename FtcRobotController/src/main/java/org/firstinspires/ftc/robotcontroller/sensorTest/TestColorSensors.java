package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name = "TEST COLOR SENSORS", group = "Tests")
public class TestColorSensors extends LinearOpMode{
    Robot robot;
    public void runOpMode() throws InterruptedException{
        robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while(true) {
            telemetry.addData("beacon blue: :", robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.BEACON));
            telemetry.addData("beacon red:  :", robot.beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.BEACON));
            telemetry.addData("ground blue: :", robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("ground red:  :", robot.beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.update();
            robot.waitFor.Sleep(.5);
        }
    }
}
