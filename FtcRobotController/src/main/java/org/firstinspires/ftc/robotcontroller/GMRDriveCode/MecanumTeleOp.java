package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Payton on 10/9/2016
 */

@TeleOp(name="Mecanum Drive: Version Three", group="Mecanum Bot")
@Disabled
//@Disabled
public class MecanumTeleOp extends OpMode {

    MoveMotors move = new MoveMotors();

    Hardwaresetup robot = new Hardwaresetup();

    double x;
    double y;
    double z;

    ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
        time.reset();
    }

    @Override
    public void start() {
        move.liftControl(false, true);
    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        move.setMotorPower(x, y, z);
        move.launchControl(gamepad1.left_bumper, gamepad1.x);
        move.sweeperControl(gamepad1.right_bumper, gamepad1.right_trigger);
        move.linearSlideControl(gamepad2.a, gamepad2.b);
        move.liftControl(gamepad2.dpad_up, gamepad2.dpad_down);

        updateTelemetry(telemetry);

        telemetry.addData("Current time", time.seconds());
        telemetry.addData("Current Yaw", move.getYaw());

        if (gamepad1.y) {
            time.startTime();
        }

    }
}
