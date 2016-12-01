package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensor;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

/**
 * Created by Payton on 10/9/2016
 */
@Autonomous(name="Autonomous Test V4", group="Mecanum Bot")
public class AutonomousTestCode extends OpMode {

    boolean programStart;
    MoveMotors move = new MoveMotors();
    ElapsedTime time = new ElapsedTime();
    Hardwaresetup robot = new Hardwaresetup();
    ColorSensor color = new ColorSensor();
    ColorSensors colorSensorsBeacon;
    ColorSensors colorSensorsGroundLeft;
    ProxSensors proxSensor;
    Continue sleep = new Continue();
    boolean stop = false;

    @Override
    public void init() {
        programStart = true;
        move.init(hardwareMap, telemetry);
        robot.init(hardwareMap);
        move.startEncoders();
        proxSensor = new ProxSensors(robot.proxSensor);
        colorSensorsBeacon = new ColorSensors(robot.colorSensorBeacon, true);
        colorSensorsGroundLeft = new ColorSensors(robot.colorSensorGroundLeft, true);
    }

    @Override
    public void loop() {
        if (programStart){
            time.reset();
            programStart = false;
        }
        if (!stop) {
            telemetry.addData("Gyro Yaw", move.getYaw());
            robot.colorSensorBeacon.enableLed(true);
            robot.colorSensorGroundLeft.enableLed(true);
            telemetry.addData("Current Red", colorSensorsGroundLeft.getRed());
            telemetry.addData("Current Blue", colorSensorsGroundLeft.getBlue());
        } else {
            move.Stop();
        }
    }
}
