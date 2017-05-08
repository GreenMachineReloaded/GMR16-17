package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Payton on 2/5/2017
 */
@TeleOp(name="Test Robot", group="Robot Test")
public class TestRobot extends OpMode {

    private double x;
    private double y;
    private Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        telemetry.addData("--------------------", "");
        telemetry.addData("Gyro", robot.driveTrain.getYaw());
        telemetry.addData("--------------------", "");
        telemetry.addData("Controller X", gamepad1.left_stick_x);
        telemetry.addData("Controller Y", gamepad1.left_stick_y);
        telemetry.addData("--------------------", "");
        telemetry.addData("Current Zone", (int) robot.driveTrain.currentZone(x, y));
    }
}
