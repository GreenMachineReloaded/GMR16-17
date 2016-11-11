package org.firstinspires.ftc.robotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;

@Autonomous(name="hit beacon red one")
public class SecondBeaconRed extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Hardwaresetup set = new Hardwaresetup();
        set.init(hardwareMap);
        MoveMotors move = new MoveMotors();
        move.init(hardwareMap);
        Continue c = new Continue();
        waitForStart();

        move.colorWhiteDrive(Directions.DRightUp, 50, ColorSensors.whichColorSensor.GROUNDLEFT);

        move.ProxDrive(Directions.Forward, 25, .7);

        move.colorDriveRedBlue(Directions.StrafeLeft, 50, ColorSensors.whichColorSensor.BEACON, ColorSensors.whichColor.RED);

        move.Drive(Directions.Forward, 25);
        c.Sleep(300);
        move.Stop();

        move.Drive(Directions.Backward, 25);
        c.Sleep(300);
        move.Stop();

        move.colorDriveRedBlue(Directions.StrafeRight, 50, ColorSensors.whichColorSensor.BEACON, ColorSensors.whichColor.RED);

        move.Drive(Directions.Forward, 25);
        c.Sleep(300);
        move.Stop();
    }
}
