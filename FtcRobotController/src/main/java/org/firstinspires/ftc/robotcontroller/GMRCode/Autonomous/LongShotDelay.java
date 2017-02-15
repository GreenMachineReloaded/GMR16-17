package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

@Autonomous(name="Long shot delay", group="Mecanum Bot")

public class LongShotDelay extends OpMode {
    private Robot robot;
    private GMRColorSensor colorSensor;
    private boolean isDone;

    private CurrentStates state;

    private double encoderDistance;

    private boolean ifRepeat;

    private ElapsedTime time;
    private double sleepTime;

    public void init() {
        isDone = false;

        state = CurrentStates.ENCODERFORWARD;
        encoderDistance = 20;

        ifRepeat = false;

        time = new ElapsedTime();
    }

    public void start() {
        robot = new Robot(hardwareMap, telemetry);
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        sleepTime = (time.seconds() + 15);
    }

    public void loop() {
        //basic Encoder movement cases
        if (time.seconds() > sleepTime) {
            if (state == CurrentStates.ENCODERFORWARD) {
                isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, .6, encoderDistance);
                if (!ifRepeat) {
                    if (isDone) {
                        ifRepeat = true;
                        isDone = false;
                        state = CurrentStates.LAUNCH;
                    }
                } else {
                    if (isDone) {
                        isDone = false;
                        state = CurrentStates.ELSE;

                    }
                }
            } else if (state == CurrentStates.LAUNCH) {
                robot.launch.launchControl(true);
                robot.waitFor.Sleep(1);
                robot.launch.launcherServoControl(true);
                robot.waitFor.Sleep(1);
                robot.launch.launchControl(false);
                robot.launch.launchControl(true);
                robot.waitFor.Sleep(1);
                isDone = true;
                encoderDistance = 10;
                if (isDone) {
                    isDone = false;
                    state = CurrentStates.ENCODERFORWARD2;
                }
            } else if (state == CurrentStates.ENCODERFORWARD2) {
                isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, .6, 4);
                if (isDone) {
                    ifRepeat = true;
                    isDone = false;
                    state = CurrentStates.PROGRAMEND;
                }
            }
            //if state is ELSE
            else if (state == CurrentStates.PROGRAMEND) {
                robot.driveTrain.stop();
            }
        }
    }
}
