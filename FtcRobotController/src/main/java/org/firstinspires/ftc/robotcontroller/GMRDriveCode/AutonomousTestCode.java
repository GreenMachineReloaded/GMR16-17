package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Payton on 10/9/2016
 */
@TeleOp(name="Autonomous Test V3", group="Mecanum Bot")
public class AutonomousTestCode extends OpMode {

    boolean programStart;
    MoveMotors move = new MoveMotors();
    ElapsedTime time = new ElapsedTime();
    Hardwaresetup robot = new Hardwaresetup();
    boolean stop = true;

    @Override
    public void init() {
        programStart = true;
        move.init(hardwareMap);
        robot.init(hardwareMap);
        move.startEncoders();
    }

    @Override
    public void loop() {
        if (programStart){
            time.reset();
            programStart = false;
        }
        if (time.seconds()<5 && stop) {
            stop = move.gyroTurn(Directions.TurnLeft,0.3,180, telemetry);
            telemetry.addData("Current Degrees: ", move.getYaw());
            telemetry.update();
        } else {
            move.Stop();
        }
    }
}
