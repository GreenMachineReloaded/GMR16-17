package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Payton on 11/26/2016
 */
@Autonomous(name="Hit Beacon Blue", group="Mecanum Bot")
public class BeaconOneAdvanced extends OpMode {

    private Robot robot;
    private currentState state = currentState.stateOne;
    private boolean isFinished = false;

    private Continue sleep = new Continue();

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
    }

    @Override
    public void start() {
        telemetry.addData("Starting Servos", "");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addData("Program Start", "");
        if (state == currentState.stateOne) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 11.5);
            } else {
                state = currentState.stateTwo;
                isFinished = false;
            }
        } else if (state == currentState.stateTwo) {
            robot.launch.launchControl(true);
            sleep.Sleep(1000);
            robot.launch.launchControl(true);
            sleep.Sleep(1000);
            robot.launch.launchControl(false);
            sleep.Sleep(5);
            robot.launch.launchControl(true);
            sleep.Sleep(1000);
            state = currentState.stateFour;
        } else if (state == currentState.stateThree) {

//            if (!isFinished) {
//                isFinished = move.encoderDrive(Directions.Forward, 0.6, 3);
//            } else {
//                state = currentState.stateFour;
//                isFinished = false;
//            }


        } else if (state == currentState.stateFour) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.4, 70);
            } else {
                state = currentState.stateFive;
                isFinished = false;
            }


        } else if (state == currentState.stateFive) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 15);
            } else {
                state = currentState.stateSix;
                isFinished = false;
            }
        }else if(state == currentState.stateSix) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNLEFT, 0.3, 70);
            } else {
                state = currentState.stateSeven;
                isFinished = false;
            }
        } else if (state == currentState.stateSeven) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.12, BeaconNav.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = currentState.stateEight;
                isFinished = false;
            }
        } else if(state == currentState.stateEight) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.3, 70);
            } else {
                state = currentState.stateNine;
                isFinished = false;
            }
        }

        else if(state == currentState.stateNine) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.FORWARD, 0.2, 0.2);
            } else {
                state = currentState.stateTen;
                isFinished = false;
            }
        } else if(state == currentState.stateTen) {
            robot.driveTrain.Drive(DriveTrain.Direction.STRAFELEFT, 0.25);
            sleep.Sleep(200);
            robot.driveTrain.stop();
            state = currentState.stateEleven;
        } else if(state == currentState.stateEleven) {
            robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, 0.25);
            sleep.Sleep(150);
            robot.driveTrain.stop();
            robot.driveTrain.Drive(DriveTrain.Direction.BACKWARD, 0.25);
            sleep.Sleep(50);
            robot.driveTrain.stop();
            state = currentState.stateTwelve;
        } else if(state == currentState.stateTwelve) {
            sleep.Sleep(4000);
            if(robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDLEFT) < (robot.beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.GROUNDLEFT))) {
                telemetry.addData("Hitting Beacon","");
                robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, 0.25);
                sleep.Sleep(150);
                robot.driveTrain.stop();
                robot.driveTrain.Drive(DriveTrain.Direction.BACKWARD, 0.25);
                sleep.Sleep(50);
                robot.driveTrain.stop();
            } else {
                telemetry.addData("Done Hitting Beacon","");
                robot.driveTrain.stop();
            }
        }
    }
}
