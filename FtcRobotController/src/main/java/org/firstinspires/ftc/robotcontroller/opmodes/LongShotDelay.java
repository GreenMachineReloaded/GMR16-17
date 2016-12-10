package org.firstinspires.ftc.robotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.currentState;

/**
 * Created by Payton on 12/2/2016
 */
@Autonomous(name="Long Shot Delay", group="Mecanum Bot")
public class LongShotDelay extends OpMode {

    MoveMotors move = new MoveMotors();
    Hardwaresetup robot = new Hardwaresetup();

    currentState state = currentState.stateOne;

    ElapsedTime time = new ElapsedTime();
    double sleepTime;

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
        sleepTime = (time.seconds() + 15);
    }

    @Override
    public void loop() {
        if (state == currentState.stateOne) {
            if (time.seconds() > sleepTime) {
                if (!isFinished) {
                    isFinished = move.encoderDrive(Directions.Forward, 0.6, 20);
                } else {
                    state = currentState.stateTwo;
                }
            }
        } else if (state == currentState.stateTwo) {
            isFinished = false;
            move.launchControl(true);
            sleep.Sleep(1000);
            move.launcherServoControl(true);
            sleep.Sleep(1000);
            move.launchControl(false);
            move.launchControl(true);
            sleep.Sleep(1000);
            state = currentState.stateFour;
            sleepTime = (time.seconds() + 15);
        } else if (state == currentState.stateThree) {
            if (time.seconds() > sleepTime) {
                if (!isFinished) {
                    isFinished = move.encoderDrive(Directions.Forward, 0.8, 7);
                } else {
                    state = currentState.stateFour;
                }
            }
        } else if (state == currentState.stateFour) {
            move.Stop();
        }
    }
}
