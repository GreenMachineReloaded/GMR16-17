package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="test color white bottom right")
public class TestColorWhiteBottomRight extends LinearOpMode{
    public void runOpMode() throws InterruptedException{
        Hardwaresetup hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);
        ColorSensors colorSensors = new ColorSensors(hardwaresetup.colorSensorGroundRight, true);
        Continue c = new Continue();
        waitForStart();
        while(true) {
            telemetry.addData("BLUE: ", colorSensors.getBlue());
            telemetry.addData("GREATER", colorSensors.isWhite());
            telemetry.update();
            c.Sleep(100);
        }
    }
}
