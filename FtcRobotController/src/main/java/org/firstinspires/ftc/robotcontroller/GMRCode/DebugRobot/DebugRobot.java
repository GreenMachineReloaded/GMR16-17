package org.firstinspires.ftc.robotcontroller.GMRCode.DebugRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;

@Autonomous(name="debugrobot")
public class DebugRobot extends OpMode {
    Continue aContinue;
    Robot robot;
    int number = 0;
    public void init() {
        aContinue.Sleep(2000);
    }
    public void start() {
        aContinue = new Continue();
        telemetry.addData("made aContinue", null);
        telemetry.update();
        aContinue.Sleep(2000);
        telemetry.clear();

        robot = new Robot(hardwareMap, telemetry);
        telemetry.addData("made robot", null);
        telemetry.update();

    }
    public void loop() {
        number += 1;
        telemetry.addData("in loop", number);
        telemetry.update();
        aContinue.Sleep(1000);
    }
}
