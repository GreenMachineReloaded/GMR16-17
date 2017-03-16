package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Payton on 2/5/2017
 */
@TeleOp(name="Test Robot", group="Robot Test")
public class TestRobot extends OpMode {

    private Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        telemetry.addData("--------------------", "");
        telemetry.addData("Gyro", robot.driveTrain.getYaw());
        telemetry.addData("--------------------", "");
        telemetry.addData("Proximity Sensor", robot.beaconNav.getDistance());
    }
}
