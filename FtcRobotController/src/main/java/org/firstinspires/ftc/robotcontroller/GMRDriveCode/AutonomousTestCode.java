package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensor;

/**
 * Created by Payton on 10/9/2016
 */
@TeleOp(name="Autonomous Test V4", group="Mecanum Bot")
public class AutonomousTestCode extends OpMode {

    boolean programStart;
    MoveMotors move = new MoveMotors();
    ElapsedTime time = new ElapsedTime();
    Hardwaresetup robot = new Hardwaresetup();
    ColorSensor color = new ColorSensor();
    boolean stop = true;

    @Override
    public void init() {
        programStart = true;
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
        move.startEncoders();
    }

    @Override
    public void loop() {
        if (programStart){
            time.reset();
            programStart = false;
        }
        if (stop) {
            telemetry.addData("Current Color", 6);
        } else {
            move.Stop();
        }
    }
}
