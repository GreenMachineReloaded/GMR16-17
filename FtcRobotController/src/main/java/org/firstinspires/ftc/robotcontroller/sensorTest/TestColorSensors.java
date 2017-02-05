package org.firstinspires.ftc.robotcontroller.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import static org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor.*;
@Autonomous(name = "TEST GMR COLOR SENSOR", group = "Tests")
@Disabled
public class TestColorSensors extends LinearOpMode{
    GMRColorSensor colorSensor;
    Robot robot;
    Continue c;
    public void runOpMode() throws InterruptedException{
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        c = new Continue();
        waitForStart();
        while(true) {
            telemetry.addData("RED", colorSensor.getRed(WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE", colorSensor.getBlue(WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREATER", colorSensor.whichGreaterColor(WhichGMRColorSensor.GROUNDLEFT, Color.BLUE, Color.RED));
            telemetry.update();
            c.Sleep(100);
        }
    }
}
