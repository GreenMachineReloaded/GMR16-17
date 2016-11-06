package org.firstinspires.ftc.robotcontroller.LightSensorTestCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcontroller.SensorObjects.LightSensors;

@Autonomous(name = "Test Light Sesor OFF", group = "Test")
@Disabled
public class TestLightSensorOFF extends LinearOpMode{
    LightSensor lightSensor;
    LightSensors lightSensors;
    public void runOpMode() throws InterruptedException {
        lightSensor = hardwareMap.lightSensor.get("lightSensor");
        lightSensors = new LightSensors(lightSensor);
        waitForStart();
        while(true) {
            telemetry.addData("value off: ", lightSensors.LevelOfLight());
            telemetry.update();
            sleep(100);
        }
    }
}
