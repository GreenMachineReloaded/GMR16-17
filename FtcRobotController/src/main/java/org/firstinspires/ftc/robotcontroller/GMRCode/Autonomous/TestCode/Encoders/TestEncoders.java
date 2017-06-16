package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.TestCode.Encoders;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;
@Disabled
@Autonomous(name="TEST DRIVE ENCODER SENSORS", group ="Tests")
public class TestEncoders extends OpMode {
    private Robot robot;
    private boolean isDone;
    private CurrentStates state;
    public void init() {
        isDone = false;
        state = CurrentStates.ENCODERFORWARD;
        robot = new Robot(hardwareMap, telemetry);
    }
    public void loop() {
        //basic directional movement cases
        if (state == CurrentStates.GYRO) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.GYROTURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.GYROTURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }


        else if (state == CurrentStates.ENCODERFORWARD) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, .50, 10);
                        if (isDone) {isDone = false;state = CurrentStates.ENCODERBACKWARD;}
        } else if (state == CurrentStates.ENCODERBACKWARD) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, .50, 10);
                        if (isDone) {isDone = false;state = CurrentStates.ENCODERSTRAFELEFT;}
        } else if (state == CurrentStates.ENCODERSTRAFELEFT) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, .50, 10);
                        if (isDone) {isDone = false;state = CurrentStates.ENCODERSTRAFERIGHT;}
        } else if (state == CurrentStates.ENCODERSTRAFERIGHT) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, .50, 10);
            if (isDone) {
                isDone = false;
                state = CurrentStates.ELSE;
            }
        }
        else if (state == CurrentStates.PROXFORWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXSTRAFELEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXSTRAFERIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXTURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXTURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALUPRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALUPLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALDOWNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALDOWNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }else if (state == CurrentStates.LAUNCH) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }

        else {
            robot.driveTrain.stop();
        }
    }
}