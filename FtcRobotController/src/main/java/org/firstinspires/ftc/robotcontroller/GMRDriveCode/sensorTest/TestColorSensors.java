package org.firstinspires.ftc.robotcontroller.GMRDriveCode.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;

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
            telemetry.addData("RED", colorSensor.getRed(GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE", colorSensor.getBlue(GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREATER", colorSensor.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT, GMRColorSensor.Color.BLUE, GMRColorSensor.Color.RED));
            telemetry.update();
            robot.waitFor.Sleep(.5);
        }
    }
}
