package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

@Autonomous(name="Long Shot robot", group="Mecanum Bot")

public class LongShotDelay extends OpMode {
    private Robot robot;
    private GMRColorSensor colorSensor;
    private boolean isDone;
    private CurrentStates state;
    private double encoderDistance;
    private boolean ifRepeat;
    public void init() {
        isDone = false;
        state = CurrentStates.DELAY;
        encoderDistance = 20;
        ifRepeat = false;
    }
    public void start() {
        robot = new Robot(hardwareMap, telemetry);
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
    }
    public void loop() {
        //basic directional movement cases
        if (state == CurrentStates.FORWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.BACKWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.STRAFELEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.STRAFERIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.TURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.TURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.DIAGONALUPRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.DIAGONALUPLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.DIAGONALDOWNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.DIAGONALDOWNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }
        //basic gyro turning cases
        else if (state == CurrentStates.GYRO) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.GYROTURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.GYROTURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }
        //basic Encoder movement cases
        else if (state == CurrentStates.ENCODERFORWARD) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, .6, encoderDistance);

            if(!ifRepeat) {if (isDone) {ifRepeat = true; isDone = false;state = CurrentStates.LAUNCH;}}
            else {if (isDone) {isDone = false;state = CurrentStates.ELSE;}}

        } else if (state == CurrentStates.ENCODERBACKWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERSTRAFELEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERSTRAFERIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERTURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERTURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERDIAGONALUPRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERDIAGONALUPLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERDIAGONALDOWNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERDIAGONALDOWNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }
        //basic color directional movement
        else if (state == CurrentStates.COLORFORWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORBACKWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORSTRAFELEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORSTRAFERIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORTURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORTURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORDIAGONALUPRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORDIAGONALUPLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORDIAGONALDOWNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.COLORDIAGONALDOWNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        }
        //basic prox directional movement
        else if (state == CurrentStates.PROXFORWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXSTRAFELEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXSTRAFERIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXTURNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXTURNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALUPRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALUPLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALDOWNRIGHT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PROXDIAGONALDOWNLEFT) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        //launch
        }else if (state == CurrentStates.LAUNCH) {
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launcherServoControl(true);
            robot.waitFor.Sleep(1);
            robot.launch.launchControl(false);
            robot.launch.launchControl(true);
            robot.waitFor.Sleep(1);
            isDone = true;
            encoderDistance = 10;
            if (isDone) {isDone = false;state = CurrentStates.ENCODERFORWARD;}
        //delay
        } else if (state == CurrentStates.DELAY) {
            robot.waitFor.Sleep(10);
            isDone = true;
            if (isDone) {isDone = false;state = CurrentStates.ENCODERFORWARD;}
        }
        //if state is ELSE
        else {
            robot.driveTrain.stop();
        }
    }
}