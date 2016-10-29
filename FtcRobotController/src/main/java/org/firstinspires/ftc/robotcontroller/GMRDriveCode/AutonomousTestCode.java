package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Payton on 10/9/2016
 */
@TeleOp(name="Autonomous Test V1", group="Mecanum Bot")
public class AutonomousTestCode extends OpMode {

    boolean programStart;
    MoveMotors move = new MoveMotors();
    ElapsedTime time = new ElapsedTime();
    Hardwaresetup robot = new Hardwaresetup();

    @Override
    public void init() {
        programStart = true;
        move.init(hardwareMap);
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (programStart){
            time.reset();
            programStart = false;
        }
        if (time.seconds()<2) {
            move.gyroTurn(Directions.TurnLeft,0.3,90);
        } else if (2<time.seconds()&&time.seconds()<4) {
            move.gyroTurn(Directions.TurnRight,0.3,90);
        }
    }
}
