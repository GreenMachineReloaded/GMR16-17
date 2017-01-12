package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

@Autonomous(name="Long Shot", group="Mecanum Bot")

public class LongShot extends OpMode {
    private Robot robot;

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
        sleepTime = (time.seconds() + 15);
    }

    public void loop() {
        //basic Encoder movement cases
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
        }
        else if (state == CurrentStates.LAUNCH) {
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
                state = CurrentStates.ENCODERFORWARD;
            }
        }
        //if state is ELSE
        else {
            robot.driveTrain.stop();
        }
    }
}
