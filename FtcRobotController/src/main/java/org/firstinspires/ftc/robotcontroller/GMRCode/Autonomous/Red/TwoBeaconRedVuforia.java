package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Red;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.RobotEyes;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.GMRCode.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.GMRCode.otherObjects.CurrentStates;

@Autonomous(name="Two Beacon Red Vuforia", group="Beacon Programs Red")
public class TwoBeaconRedVuforia extends OpMode {
    private Robot robot;
    private GMRColorSensor colorSensor;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private int launches = 0;
    private double startingOrientation;
    private ElapsedTime beaconTime = new ElapsedTime();
    private double beaconServoTime;
    private boolean isStraight = false;
    private boolean reuse = false;
    private final double beaconPushTime = 1.5;
    private String thing;
    private RobotEyes.Images currentImage;
    private boolean setCurrentImage;
    @Override
    public void init() {
        beaconTime.reset();
        try {
            robot = new Robot(hardwareMap, telemetry);
        } catch(NullPointerException e) {
            telemetry.addData("Robot Failed", "");
        }
        try {
            colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        } catch(NullPointerException e) {
            telemetry.addData("ColorSensor Failed", "");
        }
        try {
            startingOrientation = robot.driveTrain.getYaw();
        } catch(NullPointerException e) {
            telemetry.addData("Robot Failed Again", "");
        }
        telemetry.addData("Gyro Done Calibrating", robot.driveTrain.checkGyro());
        robot.beaconNav.teleOpBeaconPush(false);
        setCurrentImage = false;
    }
    @Override
    public void start() {
        telemetry.addData("Starting Servos", "");
        telemetry.update();
        beaconTime.reset();
    }
    @Override
    public void loop() {
        telemetry.addData("states run", thing);
        telemetry.addData("image", robot.robotEyes.getImageOfCurrentVisualBeacon());
        if (state == CurrentStates.ENCODERFORWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.3, 12);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.LAUNCH;
                isFinished = false;
            }
        } else if (state == CurrentStates.LAUNCH) {
            if ((launches >= 1) && isFinished) {
                robot.launch.launchControl(false);
                state = CurrentStates.STRAFELEFT;
                isFinished = false;
            } else if (!isFinished) {
                isFinished = robot.launch.launchControl(true);
            } else {
                launches += 1;
                isFinished = false;
            }
        } else if (state == CurrentStates.STRAFELEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFELEFT, 0.5, 20);
            } else {
                state = CurrentStates.VUFORIADRIVE;
                isFinished = false;
            }
        } else if(state == CurrentStates.VUFORIADRIVE) {
            if(!isFinished) {
                robot.driveTrain.Drive(DriveTrain.Direction.FORWARD, .1);
                if(robot.robotEyes.getImageOfCurrentVisualBeacon() == RobotEyes.Images.GEARS) {
                    isFinished = true;
                }
            }
            else {
                robot.driveTrain.stop();
                isFinished = false;
                state = CurrentStates.VUFORIA;
            }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } else if (state == CurrentStates.VUFORIA) {
            if (!isFinished) {
                isFinished = robot.isAlinedWithBeacon(.2);
            } else {
                isFinished = false;
                state = CurrentStates.VUFORIA2;
            }
        } else if (state == CurrentStates.VUFORIA2) {
            if (!isFinished) {
                isFinished = robot.getCloseBeacon(110, .2);
            } else {
                state = CurrentStates.PUSHBEACON;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
                isFinished = false;
            }
        } else if(state == CurrentStates.PUSHBEACON) {
            if (!isFinished && (beaconServoTime > beaconTime.seconds())) {
                isFinished = robot.beaconNav.pushRed();
            } else {
                state = CurrentStates.CHECKCOLOR;
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                isFinished = false;
            }
        } else if (state == CurrentStates.CHECKCOLOR) {
            if (robot.beaconNav.checkColor(GMRColorSensor.Color.RED)) {
                state = CurrentStates.VUFORIA3;
            } else {
                state = CurrentStates.FIXBEACON;
                beaconServoTime = (beaconTime.seconds() + 5);


            }
        } else if (state == CurrentStates.FIXBEACON) {
            if (beaconServoTime > beaconTime.seconds()) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
            } else if ((beaconServoTime + 1.5) > beaconTime.seconds()) {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.EXTENDBOTHPUSHERS);
            } else {
                robot.beaconNav.BeaconPusher(BeaconNav.WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
                state = CurrentStates.VUFORIA3;
            }
        } else if (state == CurrentStates.VUFORIA3) {
            if (!isFinished) {
                isFinished = robot.goAwayBeacon(200, .2);
            } else {
                if (!reuse) {
                    state = CurrentStates.ENCODERFORWARD2;
                    reuse = true;
                    setCurrentImage = false;
                } else {
                    state = CurrentStates.GYROTURNLEFT;
                }

                beaconServoTime = (beaconTime.seconds() + 1.5);
                isFinished = false;
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }else if(state == CurrentStates.ENCODERFORWARD2) {
            if(!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, .3, 20);
            } else {
                isFinished = false;
                state = CurrentStates.VUFORIA;
            }
        } else if (state == CurrentStates.GYROTURNLEFT) {
            if (!isFinished) {
                isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNLEFT, 0.2, 32);
            } else {
                state = CurrentStates.BACKWARD;
                isFinished = false;
                robot.driveTrain.resetEncoders();
            }
        } else if(state == CurrentStates.BACKWARD) {
            if (!isFinished) {
                isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.9, 22);
                robot.launch.launcherServoControl(false);
            } else {
                state = CurrentStates.PROGRAMEND;
                beaconServoTime = (beaconTime.seconds() + beaconPushTime);
                isFinished = false;
            }
        } else if (state == CurrentStates.PROGRAMEND) {
            robot.stopRobot();
        }
        telemetry.addData("Current State", state);
    }
}
