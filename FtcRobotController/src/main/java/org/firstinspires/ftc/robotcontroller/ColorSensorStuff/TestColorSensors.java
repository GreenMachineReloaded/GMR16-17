package org.firstinspires.ftc.robotcontroller.ColorSensorStuff;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
            c.StopFor(100);
        }
    }
}
