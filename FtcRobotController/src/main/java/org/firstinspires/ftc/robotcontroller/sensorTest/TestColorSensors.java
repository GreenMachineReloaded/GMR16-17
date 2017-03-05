package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import static org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor.*;
@Autonomous(name = "TEST GMR COLOR SENSOR", group = "Tests")
public class TestColorSensors extends LinearOpMode{
    GMRColorSensor colorSensor;
    Robot robot;
    public void runOpMode() throws InterruptedException{
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        waitForStart();
        while(true) {
            telemetry.addData("RED", colorSensor.getRed(WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE", colorSensor.getBlue(WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREATER", colorSensor.whichGreaterColor(WhichGMRColorSensor.GROUNDLEFT, Color.BLUE, Color.RED));
            telemetry.update();
            robot.waitFor.Sleep(.5);
        }
    }
}
