package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="test all color sensors")
public class TestAllColorSensors extends LinearOpMode{
    public void runOpMode() throws InterruptedException{
        Hardwaresetup hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);
        ColorSensors colorSensorsGL = new ColorSensors(hardwaresetup.colorSensorGroundLeft, true);
        ColorSensors colorSensorsGR = new ColorSensors(hardwaresetup.colorSensorGroundRight, true);
        ColorSensors colorSensorsB = new ColorSensors(hardwaresetup.colorSensorGroundRight, true);
        Continue c = new Continue();
        waitForStart();
        while(true) {

            telemetry.addData("Beacon   RED: ", colorSensorsB.getRed());
            telemetry.addData("GroundL  RED: ", colorSensorsGL.getRed());
            telemetry.addData("GroundR  RED: ", colorSensorsGR.getRed());

            telemetry.update();
            c.Sleep(1000);
            telemetry.clearAll();

            telemetry.addData("Beacon  BLUE: ", colorSensorsB.getBlue());
            telemetry.addData("GroundL BLUE: ", colorSensorsGL.getBlue());
            telemetry.addData("GroundR BLUE: ", colorSensorsGR.getBlue());

            telemetry.update();
            c.Sleep(10000);
            telemetry.clearAll();

            telemetry.addData("Beacon  ColW: ", colorSensorsB.isWhite());
            telemetry.addData("GroundL ColW: ", colorSensorsGL.isWhite());
            telemetry.addData("GroundR ColW: ", colorSensorsGR.isWhite());

            telemetry.update();
            c.Sleep(10000);
            telemetry.clearAll();
        }
    }
}
