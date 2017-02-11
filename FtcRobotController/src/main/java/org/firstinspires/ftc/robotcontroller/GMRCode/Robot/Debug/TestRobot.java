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

    private double someInt;

    @Override
    public void init() {
        try {
            robot = new Robot(hardwareMap, telemetry);
        } catch (NullPointerException e) {
            telemetry.addData("Robot Failed", "");
        }
    }

    @Override
    public void loop() {

    }
}
