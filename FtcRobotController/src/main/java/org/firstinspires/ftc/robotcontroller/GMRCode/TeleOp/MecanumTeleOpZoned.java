package org.firstinspires.ftc.robotcontroller.GMRCode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Payton on 4/23/2017
 */

@TeleOp(name="Mecanum Drive: Zoned Version", group="Mecanum Bot")
public class MecanumTeleOpZoned extends OpMode {

    private Robot robot;

    private double x;
    private double y;
    private double z;

    private double power;

    private ElapsedTime time = new ElapsedTime();

    private double servoPosition = 0.5;

    @Override
    public void init() {
        telemetry.addData("Initializing", "");
        telemetry.update();
        robot = new Robot(hardwareMap, telemetry);
        time.reset();
    }

    @Override
    public void start() {
        time.reset();
    }

    @Override
    public void loop() {

        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        robot.driveTrain.zonedDrive((int) robot.driveTrain.currentZone(x, y), x, y, z);
        //robot.driveTrain.experimentalDrive(x, y, z);
        robot.launch.launchControl(gamepad1.left_bumper, gamepad1.x);
        robot.launch.sweeperControl(gamepad1.right_bumper, gamepad1.right_trigger);
        robot.driveTrain.setLiftMotor(gamepad2.right_bumper, gamepad2.right_trigger);
        robot.driveTrain.setLiftServo(gamepad2.left_bumper, gamepad2.left_trigger);
        robot.beaconNav.teleOpBeaconPush(gamepad2.x);

        telemetry.addData("Current time", time.seconds());
        telemetry.addData("Current Yaw", robot.driveTrain.getYaw());
        telemetry.addData("---------------", "");
        telemetry.addData("Current Degrees", robot.driveTrain.currentDegrees(x, y));
        telemetry.addData("Current Strength", (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))));
        telemetry.addData("Current Zone", (int) robot.driveTrain.currentZone(x, y));

        //robot.launch.fixLauncher(gamepad2.left_stick_y);
        //robot.beaconNav.fixBeaconServos(gamepad2.a, gamepad2.b, gamepad2.x, gamepad2.y);
    }
}
