package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcontroller.SensorObjects.LightSensors;

@Autonomous(name="TEST LIGHT SENSOR ON", group ="Tests")
@Disabled
public class TestLightSensorON extends LinearOpMode{
    LightSensor lightSensor;
    LightSensors lightSensors;
    public void runOpMode() throws InterruptedException {
        lightSensor = hardwareMap.lightSensor.get("lightSensor");
        lightSensors = new LightSensors(lightSensor);
        lightSensors.enableLight(true);
        waitForStart();
        while(true) {
            telemetry.addData("value on: ", lightSensors.LevelOfLight());
            telemetry.update();
            sleep(100);
        }
    }
}
