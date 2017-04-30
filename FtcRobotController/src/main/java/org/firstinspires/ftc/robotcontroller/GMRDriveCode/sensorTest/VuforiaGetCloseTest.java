package org.firstinspires.ftc.robotcontroller.GMRDriveCode.sensorTest;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous(name="get close test", group="Tests")
public class VuforiaGetCloseTest extends OpMode {
    private Robot robot;
    public void init() {

    }
    public void start() {robot = new Robot(hardwareMap, telemetry);}
    public void loop() {
        robot.waitFor.Sleep(100);
        telemetry.addData("getArrayXYZ", robot.robotEyes.getArrayXYZ());
        telemetry.addData("Current Visual", robot.robotEyes.getImageOfCurrentVisualBeacon());
    }
}