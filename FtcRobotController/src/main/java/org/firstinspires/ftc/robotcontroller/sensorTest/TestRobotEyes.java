package org.firstinspires.ftc.robotcontroller.sensorTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.RobotEyes;

/**
 * Created by Noah Bennett on 2/11/2017.
 */
@Autonomous(name = "TestRobotEyes")
public class TestRobotEyes extends LinearOpMode{
    RobotEyes robotEyes;
    @Override
    public void runOpMode() throws InterruptedException {
        robotEyes = new RobotEyes();
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("image:", robotEyes.getImageOfCurrentVisualBeacon());
            //telemetry.addData("XYZ:", "X:"+robotEyes.getArrayXYZ()[0]+" Y: "+robotEyes.getArrayXYZ()[1]+" Z: "+robotEyes.getArrayXYZ()[2]);

            //telemetry.addData("currentXYZ", "X:"+robotEyes.getSpecificArrayXYZ(robotEyes.getImageOfCurrentVisualBeacon())[0]+" Y: "+robotEyes.getSpecificArrayXYZ(robotEyes.getImageOfCurrentVisualBeacon())[1]+" Z: "+robotEyes.getSpecificArrayXYZ(robotEyes.getImageOfCurrentVisualBeacon())[2]);

            telemetry.addData("WHEELS", "X:"+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[0]+" Y: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[1]+" Z: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[2]);
            //telemetry.addData("TOOLS", "X:"+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[0]+" Y: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[1]+" Z: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[2]);
            //telemetry.addData("LEGOS", "X:"+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[0]+" Y: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[1]+" Z: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[2]);
            //telemetry.addData("GEARS", "X:"+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[0]+" Y: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[1]+" Z: "+robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[2]);
            telemetry.update();
        }
    }
}
