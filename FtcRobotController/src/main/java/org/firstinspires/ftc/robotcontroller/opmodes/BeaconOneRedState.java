package org.firstinspires.ftc.robotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.currentState;

/**
 * Created by Payton on 11/26/2016
 */
@Autonomous(name="hit beacon red!!!!!!!", group="Mecanum Bot")
public class BeaconOneRedState extends OpMode {

    MoveMotors move = new MoveMotors();
    Hardwaresetup robot = new Hardwaresetup();
    ColorSensors colorSensors;
    currentState state = currentState.stateOne;
    boolean isFinished = false;

    Continue sleep = new Continue();
    @Override
    public void init() {
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
        colorSensors = new ColorSensors(robot.colorSensorBeacon);
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
                isFinished = move.encoderDrive(Directions.Forward, 0.6, 3);
            } else {
                state = currentState.stateFour;
                isFinished = false;
            }


        } else if (state == currentState.stateFour) {
            if (!isFinished) {
                isFinished = move.gyroTurn(Directions.TurnLeft, 0.4, 70);
            } else {
                state = currentState.stateFive;
                isFinished = false;
            }


        } else if (state == currentState.stateFive) {
            if (!isFinished) {
                isFinished = move.encoderDrive(Directions.Forward, 0.6, 15);
            } else {
                state = currentState.stateSix;
                isFinished = false;
            }
        }else if(state == currentState.stateSix) {
            if (!isFinished) {
                isFinished = move.gyroTurn(Directions.TurnRight, 0.3, 70);
            } else {
                state = currentState.stateSeven;
                isFinished = false;
            }
        } else if (state == currentState.stateSeven) {
            if (!isFinished) {
                isFinished = move.colorWhiteDrive(Directions.Forward, .25, ColorSensors.whichColorSensor.GROUNDLEFT);
            } else {
                state = currentState.stateEight;
                isFinished = false;
            }
        } else if(state == currentState.stateEight) {
            if (!isFinished) {
                isFinished = move.gyroTurn(Directions.TurnLeft, 0.3, 70);
            } else {
                state = currentState.stateNine;
                isFinished = false;
            }
        }

        else if(state == currentState.stateNine) {
        if (!isFinished) {
            isFinished = move.ProxDrive(Directions.Forward, 0.25, 0.2);
        } else {
            state = currentState.stateTen;
            isFinished = false;
            }
        } else if(state == currentState.stateTen) {
            move.Drive(Directions.Forward, .25);
            sleep.Sleep(100);
            move.Stop();
            move.Drive(Directions.Backward, .25);
            sleep.Sleep(100);
            move.Stop();
            state = currentState.stateEleven;
        } else if(state == currentState.stateEleven) {
            if(colorSensors.getBlue() > colorSensors.getRed()) {
                move.Drive(Directions.Forward, .25);
                sleep.Sleep(100);
                move.Stop();
                move.Drive(Directions.Backward, .25);
                sleep.Sleep(100);
                move.Stop();
            }
            state = currentState.stateTwelve;
        }

        else {
            move.Stop();
        }
    }
}
