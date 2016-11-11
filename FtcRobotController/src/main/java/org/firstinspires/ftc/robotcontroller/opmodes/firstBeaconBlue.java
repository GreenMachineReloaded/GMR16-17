package org.firstinspires.ftc.robotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="hit beacon blue one")
@Disabled
public class FirstBeaconBlue extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Hardwaresetup set = new Hardwaresetup();
        set.init(hardwareMap);
        MoveMotors move = new MoveMotors();
        move.init(hardwareMap, telemetry);
        Continue c = new Continue();
        waitForStart();

        move.colorWhiteDrive(Directions.DLeftUp, .1, ColorSensors.whichColorSensor.GROUNDLEFT);
        telemetry.addData("finished move.colorwhiteDrive", 0);
        telemetry.update();
//
//        move.ProxDrive(Directions.Forward, .1, .7);
//        telemetry.addData("finished move.colorwhiteDrive", 0);
//        telemetry.addData("finished move.proxDrive", 1);
//        telemetry.update();
//
//        move.colorDriveRedBlue(Directions.StrafeRight, .1, ColorSensors.whichColorSensor.BEACON, ColorSensors.whichColor.BLUE);
//        telemetry.addData("finished move.colorwhiteDrive", 0);
//        telemetry.addData("finished move.proxdrive", 1);
//        telemetry.addData("finished move.colorwhiteDrive", 2);
//        telemetry.update();
//
//        move.Drive(Directions.Forward, .1);
//        c.Sleep(300);
//        move.Stop();
//        telemetry.addData("finished move.colorwhiteDrive", 0);
//        telemetry.addData("finished move.proxdrive", 1);
//        telemetry.addData("finished move.colorwhiteDrive", 2);
//        telemetry.addData("finished move.Drive", 3);
//        telemetry.update();
    }
}
