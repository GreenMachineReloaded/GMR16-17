package org.firstinspires.ftc.robotcontroller.LightSensorStuff;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LightSensor;

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
