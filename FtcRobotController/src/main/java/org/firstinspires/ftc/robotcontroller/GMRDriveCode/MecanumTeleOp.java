package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Payton on 10/9/2016
 */

@TeleOp(name="Mecanum Drive: Version One", group="Mecanum Bot")
public class MecanumTeleOp extends OpMode {

    MoveMotors move = new MoveMotors();

    double x;
    double y;
    double z;

    @Override
    //Is this required?
    public void init() {
        //move.init(hardwareMap);
        move.startEncoders();
    }

    @Override
    public void loop() {

        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        move.setMotorPower(x, y, z);

        telemetry.addData("Left Encoder", move.getLeftEncoder());
        telemetry.addData("Right Encoder", move.getRightEncoder());
        telemetry.addData("Current Yaw", move.getYaw());
        updateTelemetry(telemetry);

    }
}
