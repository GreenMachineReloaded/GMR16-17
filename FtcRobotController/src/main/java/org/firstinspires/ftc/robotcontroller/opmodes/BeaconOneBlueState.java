package org.firstinspires.ftc.robotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.otherObjects.currentState;

/**
 * Created by Payton on 11/11/2016
 */
@Autonomous(name="Blue Side, Beacon One", group="Beacon Pushers")
@Disabled
public class BeaconOneBlueState extends OpMode {

    MoveMotors move = new MoveMotors();

    ElapsedTime time = new ElapsedTime();
    boolean programStart;
    boolean stop;

    currentState state;

    @Override
    public void init() {
        stop  = true;
        programStart = true;
        state = currentState.stateOne;
        move.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        if (programStart){
            time.reset();
            programStart = false;
        }
        if (state == currentState.stateOne) {

        }

    }
}
