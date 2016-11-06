package org.firstinspires.ftc.robotcontroller.ColorSensorTestCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.Continue;

@Autonomous(name = "TEST COLOR SENSORS", group = "Tests")
@Disabled
public class TestColorSensors extends LinearOpMode{
    ColorSensor colorSensor;
    ColorSensors colorSensors;
    Continue c;
    public void runOpMode() throws InterruptedException{
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensors = new ColorSensors(colorSensor, false);
        c = new Continue();
        waitForStart();
        while(true) {
            telemetry.addData("RED", colorSensors.getRed());
            telemetry.addData("BLUE", colorSensors.getBlue());
            telemetry.addData("GREATER", colorSensors.greaterColor());
            telemetry.update();
            c.Sleep(100);
        }
    }
}
