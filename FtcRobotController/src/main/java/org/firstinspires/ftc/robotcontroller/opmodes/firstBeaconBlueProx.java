package org.firstinspires.ftc.robotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

@Autonomous(name="hit beacon blue one")
@Disabled
public class firstBeaconBlueProx extends LinearOpMode{
    public void runOpMode() throws InterruptedException {
        Hardwaresetup set = new Hardwaresetup();
        set.init(hardwareMap);
        MoveMotors move = new MoveMotors();
        move.init(hardwareMap, telemetry);
        ColorSensors colorSensor = new ColorSensors(set.colorSensorBeacon);
        ProxSensors proxSensors = new ProxSensors(set.proxSensor);
        Continue c = new Continue();
        waitForStart();
        move.ProxDrive(Directions.DLeftUp, 50);
        move.Stop();
        move.Drive(Directions.Backward, 50);
        c.Sleep(200);
        move.Stop();
        move.colorDriveRedBlue(Directions.StrafeRight,.50, ColorSensors.whichColorSensor.BEACON, ColorSensors.whichColor.BLUE);
        move.Stop();
        c.Sleep(200);
        move.Drive(Directions.Forward, 25);
        c.Sleep(300);
        move.Stop();
    }
}
