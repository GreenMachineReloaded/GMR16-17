package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Payton on 10/9/2016
 */

@TeleOp(name="Mecanum Drive: Version Two", group="Mecanum Bot")
public class MecanumTeleOp extends OpMode {

    MoveMotors move = new MoveMotors();

    Hardwaresetup robot = new Hardwaresetup();

    double x;
    double y;
    double z;

    @Override
    public void init() {
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        move.setMotorPower(x, y, z);
        move.launchControl(gamepad1.left_bumper);
        move.sweeperControl(gamepad1.right_bumper, gamepad1.right_trigger);

        telemetry.addData("Current Yaw", move.getYaw());
        telemetry.addData("Launch Encoder", move.getLaunchEncoder());
        telemetry.addData("Launcher is Active", gamepad1.left_bumper);

//        if (gamepad1.a) {
//            robot.launchMotor.setPower(0.01);
//            telemetry.addData("Launcher Test is Active", true);
//        } else {
//            robot.launchMotor.setPower(0);
//            telemetry.addData("Launcher Test is Active", false);
//        }

        updateTelemetry(telemetry);

    }
}
