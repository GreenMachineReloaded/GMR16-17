package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="TEST COLOR WHITE PROGRAM", group ="Tests")
public class TestColorWhite extends LinearOpMode{
    ColorSensor colorSensor;
    ColorSensor colorSensor1;
    Hardwaresetup hardwaresetup;
    Continue c;
    public void runOpMode() throws InterruptedException{
        hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);

        colorSensor1 = hardwaresetup.colorSensorGroundLeft;
        colorSensor = hardwaresetup.colorSensorBeacon;
        c = new Continue();
        waitForStart();
        while(true) {
            telemetry.addData("red", colorSensor.red() * 8);
            telemetry.addData("red1", colorSensor1.red() * 8);
            telemetry.update();
            c.Sleep(100);
        }
    }
}
