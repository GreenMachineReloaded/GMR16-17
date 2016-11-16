package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="test color sensors port")
public class TestColorPort extends LinearOpMode{
    public void runOpMode() throws InterruptedException{

        ColorSensor colorbeacon = hardwareMap.colorSensor.get("c");
        Continue c = new Continue();
        waitForStart();
        colorbeacon.enableLed(false);
        while(true) {
            telemetry.addData("blue: ", colorbeacon.blue());
            telemetry.addData("red: ", colorbeacon.red());
            telemetry.addData("address: ", colorbeacon.getI2cAddress());
            telemetry.addData("info: ", colorbeacon.getConnectionInfo());
            telemetry.addData("name: ", colorbeacon.getDeviceName());

            telemetry.update();
            c.Sleep(100);
        }
    }
}
