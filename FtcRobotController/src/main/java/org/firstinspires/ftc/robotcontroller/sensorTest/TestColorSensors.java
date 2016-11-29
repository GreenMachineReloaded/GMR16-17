package org.firstinspires.ftc.robotcontroller.sensorTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name = "TEST COLOR SENSORS", group = "Tests")
public class TestColorSensors extends OpMode {
    ColorSensor colorSensor;
    ColorSensors colorSensors;
    Continue c;

    /*
    public void runOpMode() throws InterruptedException{
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensors = new ColorSensors(colorSensor, false);
        c = new Continue();
        while(true) {
            telemetry.addData("RED", colorSensors.getRed());
            telemetry.addData("BLUE", colorSensors.getBlue());
            telemetry.addData("GREATER", colorSensors.greaterColor());
            telemetry.update();
            c.Sleep(100);
        }
    }
    */

    @Override
    public void init() {
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensors = new ColorSensors(colorSensor, false);
        c = new Continue();
    }

    @Override
    public void loop() {
        telemetry.addData("RED", colorSensors.getRed());
        telemetry.addData("BLUE", colorSensors.getBlue());
        telemetry.addData("GREATER", colorSensors.greaterColor());
        telemetry.update();
    }
}
