package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
<<<<<<< HEAD
=======
<<<<<<< HEAD
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
=======
>>>>>>> refs/remotes/origin/master
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import static org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor.*;
@Autonomous(name = "TEST GMR COLOR SENSOR", group = "Tests")
public class TestColorSensors extends LinearOpMode{
    GMRColorSensor colorSensor;
    Robot robot;
    public void runOpMode() throws InterruptedException{
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        waitForStart();
        while(true) {
            telemetry.addData("RED", colorSensor.getRed(WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE", colorSensor.getBlue(WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREATER", colorSensor.whichGreaterColor(WhichGMRColorSensor.GROUNDLEFT, Color.BLUE, Color.RED));
<<<<<<< HEAD
=======
>>>>>>> refs/remotes/origin/m-Automaintenence
>>>>>>> refs/remotes/origin/master
            telemetry.update();
            robot.waitFor.Sleep(.5);
        }
    }
}
