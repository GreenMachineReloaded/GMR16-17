package org.firstinspires.ftc.robotcontroller.sensorTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.RobotEyes;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

/**
 * Created by Noah Bennett on 2/11/2017.
 */
@Autonomous(name = "Test Drive Vuforia aline")
public class TestDriveVuforiaIsAlinedBeacon extends OpMode{
    Robot robot;
    boolean isclose;
    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        isclose = false;
        telemetry.addData("image:", robot.robotEyes.getImageOfCurrentVisualBeacon());
        telemetry.addData("XYZ:", "X:"+robot.robotEyes.getArrayXYZ()[0]+" Y: "+robot.robotEyes.getArrayXYZ()[1]+" Z: "+robot.robotEyes.getArrayXYZ()[2]);
        telemetry.addData("currentXYZ", "X:"+robot.robotEyes.getSpecificArrayXYZ(robot.robotEyes.getImageOfCurrentVisualBeacon())[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(robot.robotEyes.getImageOfCurrentVisualBeacon())[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(robot.robotEyes.getImageOfCurrentVisualBeacon())[2]);
        telemetry.addData("WHEELS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[2]);
        telemetry.addData("TOOLS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[2]);
        telemetry.addData("LEGOS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[2]);
        telemetry.addData("GEARS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[2]);
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addData("image:", robot.robotEyes.getImageOfCurrentVisualBeacon());
        telemetry.addData("XYZ:", "X:"+robot.robotEyes.getArrayXYZ()[0]+" Y: "+robot.robotEyes.getArrayXYZ()[1]+" Z: "+robot.robotEyes.getArrayXYZ()[2]);
        telemetry.addData("currentXYZ", "X:"+robot.robotEyes.getSpecificArrayXYZ(robot.robotEyes.getImageOfCurrentVisualBeacon())[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(robot.robotEyes.getImageOfCurrentVisualBeacon())[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(robot.robotEyes.getImageOfCurrentVisualBeacon())[2]);
        telemetry.addData("WHEELS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.WHEELS)[2]);
        telemetry.addData("TOOLS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.TOOLS)[2]);
        telemetry.addData("LEGOS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.LEGOS)[2]);
        telemetry.addData("GEARS", "X:"+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[0]+" Y: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[1]+" Z: "+robot.robotEyes.getSpecificArrayXYZ(RobotEyes.Images.GEARS)[2]);
        telemetry.update();
        if(isclose == false) {
            isclose = robot.isAlinedWithBeacon(.1);
        }
    }
}
