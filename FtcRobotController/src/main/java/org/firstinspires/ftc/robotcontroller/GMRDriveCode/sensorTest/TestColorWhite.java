//package org.firstinspires.ftc.robotcontroller.GMRDriveCode.sensorTest;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.ColorSensor;
//
//import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
//import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
//import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
//@Disabled
//@Autonomous(name="TEST COLOR WHITE PROGRAM", group ="Tests")
//public class TestColorWhite extends LinearOpMode{
//    ColorSensors colorSensor;
//    ColorSensors colorSensor1;
//    Hardwaresetup hardwaresetup;
//    Continue c;
//    public void runOpMode() throws InterruptedException{
//        hardwaresetup = new Hardwaresetup();
//        hardwaresetup.init(hardwareMap);
//
//        colorSensor1 = new ColorSensors(hardwaresetup.colorSensorGroundLeft);
//        colorSensor = new ColorSensors(hardwaresetup.colorSensorBeacon);
//        colorSensor.turnOnLight(false);
//        colorSensor1.turnOnLight(true);
//        c = new Continue();
//        waitForStart();
//        while(true) {
//            telemetry.addData("red", colorSensor.getRed() * 8);
//            telemetry.addData("red", colorSensor.isWhite());
//            telemetry.addData("red1", colorSensor1.getRed() * 8);
//            telemetry.addData("red1", colorSensor1.isWhite());
//            telemetry.update();
//            c.Sleep(100);
//        }
//    }
//}
