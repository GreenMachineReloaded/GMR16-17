package org.firstinspires.ftc.robotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
@Autonomous(name="lanch hit ball")
public class ShootNockPlantRED extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Hardwaresetup set = new Hardwaresetup();
        set.init(hardwareMap);
        MoveMotors move = new MoveMotors();
        move.init(hardwareMap, telemetry);
        Continue c = new Continue();
        DcMotor fire = hardwareMap.dcMotor.get("launchmotor");
        waitForStart();
        move.Drive(Directions.Forward, .5);
        c.Sleep(1100);
        move.Stop();
        fire.setPower(.75);
        c.Sleep(2000);
        fire.setPower(0);
        move.Drive(Directions.Forward, .25);
        c.Sleep(200);
        move.Drive(Directions.TurnLeft, .5);
        c.Sleep(1000);
        move.Drive(Directions.TurnRight, .5);
        c.Sleep(1000);
        move.Drive(Directions.Forward, .3);
        c.Sleep(700);
        move.Stop();
    }
}
