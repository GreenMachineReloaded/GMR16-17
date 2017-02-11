package org.firstinspires.ftc.robotcontroller.sensorTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.RobotEyes;

/**
 * Created by Noah Bennett on 2/11/2017.
 */
@Autonomous(name = "butt")
public class TestRobotEyes extends LinearOpMode{
    RobotEyes robotEyes;

    @Override
    public void runOpMode() throws InterruptedException {
        robotEyes = new RobotEyes();
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("image", robotEyes.imageInFrontCamera());
            telemetry.update();
        }
    }
}
