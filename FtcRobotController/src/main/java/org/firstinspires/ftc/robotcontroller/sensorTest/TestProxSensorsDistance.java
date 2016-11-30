package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;

@Autonomous(name = "TestProxSensorDistance")

public class TestProxSensorsDistance extends LinearOpMode{
    Continue c;
    Hardwaresetup hardwaresetup;
    ProxSensors proxSensors;
    @Override
    public void runOpMode() throws InterruptedException {
        c = new Continue();
        hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);
        proxSensors = new ProxSensors(hardwaresetup.proxSensor);
        waitForStart();
        while(true) {
            telemetry.addData("distance", proxSensors.getDistance());
            telemetry.update();
            c.Sleep(100);
        }
    }
}
