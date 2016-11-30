package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="TEST COLOR WHITE PROGRAM", group ="Tests")
@Disabled
public class TestColorWhite extends LinearOpMode{
    ColorSensor colorSensor;
    ColorSensors colorSensors;
    Hardwaresetup hardwaresetup;
    Continue c;
    public void runOpMode() throws InterruptedException{
        hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);

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
