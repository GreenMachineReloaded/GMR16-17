package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.TestCode.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.SensorObjects.GMRColorSensor;
@Disabled
@Autonomous(name="TEST COLOR SENSORS", group ="Tests")
public class TestColorSensors extends OpMode{
    GMRColorSensor colorSensor;
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
    }
    @Override
    public void loop() {
        telemetry.addData("ground is white? : ", colorSensor.isWhite(GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
        telemetry.addData("beacon is color? : ", colorSensor.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED));
        telemetry.update();
    }
}
