package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    ElapsedTime time = new ElapsedTime();

    boolean start = true;

    @Override
    public void init() {
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
        time.reset();
    }

    @Override
    public void loop() {

        if (start) {
            move.liftControl(false, true);
            start = false;
        }

        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        move.setMotorPower(x, y, z);
        if (!gamepad1.x) {
            move.launchControl(gamepad1.left_bumper);
        }
        move.sweeperControl(gamepad1.right_bumper, gamepad1.right_trigger);
        move.linearSlideControl(gamepad2.a, gamepad2.b);
        move.launcherServoControl(gamepad1.x);
        move.liftControl(gamepad2.dpad_up, gamepad2.dpad_down);

        updateTelemetry(telemetry);

        telemetry.addData("Current time", time.seconds());
        telemetry.addData("Current Position", robot.ballLiftServo.getPosition());

        if (gamepad1.y) {
            time.startTime();
        }

    }
}
