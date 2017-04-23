package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Payton on 3/7/2017
 */

@Autonomous(name="Test Diagonals", group="Mecanum Bot")
public class TestDiagonals extends OpMode {

    private Robot robot;
    private boolean isFinished;
    private ElapsedTime time;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        isFinished = false;
        time = new ElapsedTime();
    }

    @Override
    public void loop() {
        if (time.seconds() <= 2) {
            robot.driveTrain.Drive(DriveTrain.Direction.DRIGHTUP, 0.4);
        } else if (2 <= time.seconds() && time.seconds() <= 4) {
            robot.driveTrain.Drive(DriveTrain.Direction.DLEFTDOWN, 0.4);
        } else if (4 <= time.seconds() && time.seconds() <= 6) {
            robot.driveTrain.Drive(DriveTrain.Direction.DLEFTUP, 0.4);
        } else if (6 <= time.seconds() && time.seconds() <= 8) {
            robot.driveTrain.Drive(DriveTrain.Direction.DRIGHTDOWN, 0.4);
        } else {
            robot.driveTrain.stop();
        }
    }
}
