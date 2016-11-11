package org.firstinspires.ftc.robotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="hit beacon red one")
public class firstBeaconRed extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Hardwaresetup set = new Hardwaresetup();
        set.init(hardwareMap);
        MoveMotors move = new MoveMotors();
        move.init(hardwareMap, telemetry);
        Continue c = new Continue();
        waitForStart();
        move.colorWhiteDrive(Directions.DRightUp, 50, ColorSensors.whichColorSensor.GROUNDLEFT);
        move.Stop();
        c.Sleep(100);
        move.ProxDrive(Directions.Forward, 25, .7);
        c.Sleep(100);
        move.colorDriveRedBlue(Directions.StrafeLeft, 50, ColorSensors.whichColorSensor.BEACON, ColorSensors.whichColor.RED);
        move.Stop();
        c.Sleep(100);
        move.Drive(Directions.Forward, 25);
        c.Sleep(300);
        move.Stop();
    }
}
