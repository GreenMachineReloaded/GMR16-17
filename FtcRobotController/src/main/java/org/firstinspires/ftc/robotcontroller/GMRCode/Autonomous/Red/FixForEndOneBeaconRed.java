package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Red;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;

@Autonomous(name="One Beacon Red First Beacon", group="Beacon Programs")
@Disabled
public class FixForEndOneBeaconRed extends OpMode {
    private Robot robot;
    private boolean isDone;
    private CurrentStates state;
    private GMRColorSensor colorSensor;
    public void init() {
        isDone = false;
        state = CurrentStates.PUSHBEACON;
    }
    public void start() {robot = new Robot(hardwareMap, telemetry); colorSensor = new GMRColorSensor(hardwareMap, telemetry);}
    public void loop() {
        //basic directional movement cases
        if (state == CurrentStates.FORWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.BACKWARD) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.STRAFELEFT) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, .6, 7 );
            if (isDone) {
                isDone = false;
                state = CurrentStates.PUSHBEACON;
                robot.driveTrain.stop();
            }
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
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.ENCODERBACKWARD) {
            isDone = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.5, 0.1);
            if (isDone) {
                isDone = false;
                state = CurrentStates.STRAFELEFT;
                robot.driveTrain.stop();
            }
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
            isDone = robot.whiteDrive(DriveTrain.Direction.FORWARD, .05, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);
            if (isDone) {
                isDone = false;
                state = CurrentStates.PROXSTRAFELEFT;
            }
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
            isDone = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.6, 0.5);
            if (isDone) {
                isDone = false;
                state = CurrentStates.PUSHBEACON;
            }
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
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
            //delay
        }else if (state == CurrentStates.DELAY) {
            if (isDone) {isDone = false;state = CurrentStates.ELSE;}
        } else if (state == CurrentStates.PUSHBEACON) {
            if(colorSensor.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED)) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.EXTENDLEFTBEACONPUSHER);
            }
            else {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.EXTENDRIGHTBEACONPUSHER);
            }
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
            state = CurrentStates.ELSE;
        }
        //if state is ELSE
        else {
            robot.driveTrain.stop();
        }
    }
}