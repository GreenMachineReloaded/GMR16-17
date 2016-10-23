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
    public void init() {
        move.init(hardwareMap);
    }

    @Override
    public void loop() {

        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        move.setMotorPower(x,y,z);

        telemetry.addData("Gamepad 1 Left Stick y", gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Left Stick x", gamepad1.left_stick_x);
        telemetry.addData("Gamepad 1 Right Stick x", gamepad1.right_stick_x);
        updateTelemetry(telemetry);

    }
}
