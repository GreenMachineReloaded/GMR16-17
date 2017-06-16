package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.TestCode.Gyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;
@Disabled
@Autonomous(name="TEST GYRO SENSORS", group ="Tests")
public class TestGyro extends OpMode {
    private Robot robot;
    private boolean isDone;
    private CurrentStates state;
    public void init() {
        isDone = false;
        state = CurrentStates.GYRO;
        robot = new Robot(hardwareMap, telemetry);
    }
    public void loop() {
        //basic directional movement cases
        if (state == CurrentStates.GYRO) {
            isDone = robot.driveTrain.checkGyro();
            telemetry.addData("GYRO OK? :", isDone);
            if (isDone) {isDone = false;state = CurrentStates.GYROTURNLEFT;}
        } else if (state == CurrentStates.GYROTURNLEFT) {
            isDone = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNLEFT, .3, 180);
            if (isDone) {isDone = false;state = CurrentStates.GYROTURNRIGHT;}
        } else if (state == CurrentStates.GYROTURNRIGHT) {
            isDone = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, .3, 180);
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }
        else {
            robot.driveTrain.stop();
        }
    }
}