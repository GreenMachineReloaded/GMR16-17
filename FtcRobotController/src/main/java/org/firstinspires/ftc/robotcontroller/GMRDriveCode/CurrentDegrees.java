package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Payton on 10/16/2016
 */
@TeleOp(name="Degrees Test", group="Mecanum Bot")
public class CurrentDegrees extends OpMode {

    MoveMotors move = new MoveMotors();

    double x;
    double y;

    @Override
    public void init() {
        move.init(hardwareMap);
    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        telemetry.addData("Current Degrees", move.currentDegrees(x,y));
        telemetry.addData("Gamepad 1 Left Stick y", gamepad1.left_stick_y);
        telemetry.addData("Gamepad 1 Left Stick x", gamepad1.left_stick_x);
    }
}
