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

@Autonomous(name="testMotors")
public class testMotors extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Hardwaresetup set = new Hardwaresetup();
        set.init(hardwareMap);
        MoveMotors move = new MoveMotors();
        move.init(hardwareMap, telemetry);
        Continue sleep = new Continue();
        waitForStart();
        move.Drive(Directions.Forward, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.Backward, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.TurnLeft, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.TurnRight, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.StrafeLeft, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.StrafeRight, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.DRightUp, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.DLeftDown, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.DLeftUp, 50);
        sleep.Sleep(500);
        move.Stop();
        sleep.Sleep(1000);
        move.Drive(Directions.DRightDown, 50);
        sleep.Sleep(500);
        move.Stop();
    }
}
