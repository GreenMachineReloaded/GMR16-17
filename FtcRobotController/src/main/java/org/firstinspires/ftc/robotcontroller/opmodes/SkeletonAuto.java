package org.firstinspires.ftc.robotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.currentState;

/**
 * Created by Payton on 11/26/2016
 */
@Autonomous(name="Skeleton Drive: Version One", group="Mecanum Bot")
public class SkeletonAuto extends OpMode {

    MoveMotors move = new MoveMotors();
    Hardwaresetup robot = new Hardwaresetup();

    currentState state = currentState.stateOne;

    boolean isFinished = false;

    Continue sleep = new Continue();
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
                isFinished = move.encoderDrive(Directions.Forward, 0.8, 50);
            } else {
                state = currentState.stateTwo;
            }
        } else if (state == currentState.stateTwo) {
            move.launchControl(true);
            sleep.Sleep(1);
            move.launcherServoControl(true);
            sleep.Sleep(0.7);
            move.launcherServoControl(true);
            sleep.Sleep(1);
            state = currentState.stateThree;
        } else if (state == currentState.stateThree) {
            if (!isFinished) {
                isFinished = move.encoderDrive(Directions.Forward, 0.8, 2);
            } else {
                state = currentState.stateFour;
            }
        } else if (state == currentState.stateFour) {
            move.Stop();
        }
    }
}
