package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="test color white bottom left")
@Disabled
public class TestColorWhiteBottomLeft extends LinearOpMode{
    public void runOpMode() throws InterruptedException{
        Hardwaresetup hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);
        ColorSensors colorSensors = new ColorSensors(hardwareMap.colorSensor.get("colorSensorGroundLeft"));
        colorSensors.turnOnLight(true);
        Continue c = new Continue();
        waitForStart();
        while(true) {
            telemetry.addData("BLUE: ", "" + hardwaresetup.colorSensorGroundLeft.blue());
            telemetry.addData("BLUE: ", "" + colorSensors.getBlue());
            telemetry.addData("GREATER", "" + colorSensors.isWhite());
            telemetry.update();
            c.Sleep(100);
        }
    }
}
