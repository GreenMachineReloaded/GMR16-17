package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

/**
 * Created by Payton on 11/26/2016
 */
@Autonomous(name="Hit Beacon Blue", group="Mecanum Bot")
public class BeaconOneAdvanced extends OpMode {

    private Robot robot;
    private CurrentStates state = CurrentStates.STATEONE;
    private boolean isFinished = false;

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
        if (state == CurrentStates.STATEONE) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 11.5);
            } else {
                state = CurrentStates.STATETWO;
                isFinished = false;
            }
        } else if (state == CurrentStates.STATETWO) {
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launchControl(false);
            robot.waitFor.Sleep(.005);
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            state = CurrentStates.STATEFOUR;
        } else if (state == CurrentStates.STATETHREE) {

//            if (!isFinished) {
//                isFinished = move.encoderDrive(Directions.Forward, 0.6, 3);
//            } else {
//                state = currentState.stateFour;
//                isFinished = false;
//            }


        } else if (state == CurrentStates.STATEFOUR) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.4, 70);
            } else {
                state = CurrentStates.STATEFIVE;
                isFinished = false;
            }


        } else if (state == CurrentStates.STATEFIVE) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 15);
            } else {
                state = CurrentStates.STATESIX;
                isFinished = false;
            }
        }else if(state == CurrentStates.STATESIX) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNLEFT, 0.3, 70);
            } else {
                state = CurrentStates.STATESEVEN;
                isFinished = false;
            }
        } else if (state == CurrentStates.STATESEVEN) {
            if (!isFinished) {
                isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.12, BeaconNav.WhichGMRColorSensor.GROUNDLEFT);
            } else {
                state = CurrentStates.STATEEIGHT;
                isFinished = false;
            }
        } else if(state == CurrentStates.STATEEIGHT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, 0.3, 70);
            } else {
                state = CurrentStates.STATENINE;
                isFinished = false;
            }
        }

        else if(state == CurrentStates.STATENINE) {
            if (!isFinished) {
                isFinished = robot.ProxDrive(DriveTrain.Direction.FORWARD, 0.2, 0.2);
            } else {
                state = CurrentStates.STATETEN;
                isFinished = false;
            }
        } else if(state == CurrentStates.STATETEN) {
            robot.driveTrain.Drive(DriveTrain.Direction.STRAFELEFT, 0.25);
            robot.waitFor.Sleep(.2);
            robot.driveTrain.stop();
            state = CurrentStates.STATEELEVEN;
        } else if(state == CurrentStates.STATEELEVEN) {
            robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, 0.25);
            robot.waitFor.Sleep(.150);
            robot.driveTrain.stop();
            robot.driveTrain.Drive(DriveTrain.Direction.BACKWARD, 0.25);
            robot.waitFor.Sleep(.05);
            robot.driveTrain.stop();
            state = CurrentStates.STATETWELVE;
        } else if(state == CurrentStates.STATETWELVE) {
            robot.waitFor.Sleep(4);
            if(robot.beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDLEFT) < (robot.beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.GROUNDLEFT))) {
                telemetry.addData("Hitting Beacon","");
                robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, 0.25);
                robot.waitFor.Sleep(.150);
                robot.driveTrain.stop();
                robot.driveTrain.Drive(DriveTrain.Direction.BACKWARD, 0.25);
                robot.waitFor.Sleep(.05);
                robot.driveTrain.stop();
            } else {
                telemetry.addData("Done Hitting Beacon","");
                robot.driveTrain.stop();
            }
        }
    }
}
