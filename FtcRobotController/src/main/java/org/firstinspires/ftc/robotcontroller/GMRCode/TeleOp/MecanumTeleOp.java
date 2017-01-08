package org.firstinspires.ftc.robotcontroller.GMRCode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Payton on 10/9/2016
 */

@TeleOp(name="Mecanum Drive: Version Four", group="Mecanum Bot")
public class MecanumTeleOp extends OpMode {

    private Robot robot;

    private BeaconNav beaconNav;
    private DriveTrain driveTrain;
    private Launch launch;

    private double x;
    private double y;
    private double z;

    private ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
//        beaconNav = robot.getBeaconNav();
        driveTrain = robot.getDriveTrain();
//        launch = robot.getLaunch();
        time.reset();
    }

    @Override
    public void start() {
        time.reset();
        //robot.launch.liftControl(false, true);
    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        z = gamepad1.right_stick_x;

        robot.driveTrain.setMotorPower(x, y, z);
        //robot.driveTrain.experimentalDrive(x, y, x);
        robot.launch.launchControl(gamepad1.left_bumper, gamepad1.x);
        robot.launch.sweeperControl(gamepad1.right_bumper, gamepad1.right_trigger);
        robot.launch.linearSlideControl(gamepad2.a, gamepad2.b);
        robot.launch.liftControl(gamepad2.dpad_up, gamepad2.dpad_down);
        updateTelemetry(telemetry);

        telemetry.addData("Current time", time.seconds());
        telemetry.addData("Current Yaw", robot.driveTrain.getYaw());
        telemetry.addData("-----------", "");
        telemetry.addData("Robot Class", driveTrain.getClass().getDeclaredMethods());
        telemetry.addData("-----------", "");

        //robot.launch.fixLauncher(gamepad2.left_stick_y);
    }
}
