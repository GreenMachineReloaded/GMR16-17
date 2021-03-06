package org.firstinspires.ftc.robotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.currentState;

/**
 * Created by Payton on 11/26/2016
 */
@Disabled
@Autonomous(name="Skeleton Drive: Version Three", group="Mecanum Bot")
public class SkeletonAuto extends OpMode {

    private MoveMotors move = new MoveMotors();
    private Hardwaresetup robot = new Hardwaresetup();

    private currentState state = currentState.stateFive;

    private boolean isFinished = false;

    private Continue sleep = new Continue();
    @Override
    public void init() {
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
    }

    @Override
    public void start() {
        move.liftControl(false, true);
    }

    @Override
    public void loop() {
        if (state == currentState.stateOne) {
            if (!isFinished) {
                isFinished = move.encoderDrive(Directions.Forward, 0.6, 11);
            } else {
                state = currentState.stateTwo;
                isFinished = false;
            }
        } else if (state == currentState.stateTwo) {
            move.launchControl(true);
            sleep.Sleep(1000);
            move.launcherServoControl(true);
            sleep.Sleep(1000);
            move.launchControl(false);
            move.launchControl(true);
            sleep.Sleep(1000);
            state = currentState.stateThree;
        } else if (state == currentState.stateThree) {
            if (!isFinished) {
                isFinished = move.gyroTurn(Directions.TurnLeft, 0.4, 70);
            } else {
                state = currentState.stateFive;
                isFinished = false;
            }
        } else if (state == currentState.stateFour) {
            if (!isFinished) {
                isFinished = move.encoderDrive(Directions.Forward, 0.6, 11);
            } else {
                state = currentState.stateFive;
                isFinished = false;
            }
        } else if (state == currentState.stateFive) {
            if (!isFinished) {
                isFinished = move.ProxDrive(Directions.StrafeRight, 0.6, 0.15);
            } else {
                state = currentState.stateSix;
                isFinished = false;
            }
        }
    }
}
