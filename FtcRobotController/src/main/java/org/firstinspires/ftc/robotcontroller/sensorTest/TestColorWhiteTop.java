package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="test color sensors beacon")
@Disabled
public class TestColorWhiteTop extends LinearOpMode{
    public void runOpMode() throws InterruptedException{
        Hardwaresetup hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);
        ColorSensor colorSensor = hardwareMap.colorSensor.get("colorSensorBeacon");
        ColorSensor colorSensor1 = hardwareMap.colorSensor.get("colorSensorGroundLeft");
        ColorSensors colorSensors = new ColorSensors(colorSensor);
        ColorSensors colorSensors1 = new ColorSensors(colorSensor1);
        Continue c = new Continue();
        colorSensors.turnOnLight(true);
        colorSensors1.turnOnLight(true);
        waitForStart();
        while(true) {
            telemetry.addData("BLUEfloor: ", colorSensors1.getBlue());
            telemetry.addData("BLUEbeacon: ", colorSensors.getBlue());
            telemetry.addData("beacon", colorSensor.blue());
            telemetry.addData("ground", colorSensor1.blue());
            telemetry.update();
            c.Sleep(100);
        }
    }
}
