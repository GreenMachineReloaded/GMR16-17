package org.firstinspires.ftc.robotcontroller.ColorSensorTestCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.Continue;

@Autonomous(name="TEST COLOR WHITE PROGRAM", group ="Tests")
@Disabled
public class TestColorWhite extends LinearOpMode{
    ColorSensor colorSensor;
    ColorSensors colorSensors;
    Continue c;
    public void runOpMode() throws InterruptedException{
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensors = new ColorSensors(colorSensor, false);
        c = new Continue();
        waitForStart();
        while(true) {
            telemetry.addData("WHITE", colorSensor.red() * 8 * colorSensor.blue() * colorSensor.green());
            telemetry.addData("GREATER", colorSensors.isWhite());
            telemetry.update();
            c.Sleep(100);
        }
    }
}
