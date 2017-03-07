package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class BeaconNav {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PROX V
    //sensor for detecting the wall
    private OpticalDistanceSensor proxSensorLeft;
    private String proxSensorLeftName = "proxSensorLeft";
    boolean proxLightOnOff = true;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MISS V
    private Telemetry telemetry;
    private double colorFactor = 8;

    private Servo leftBeaconButtonPusher;
    private Servo rightBeaconButtonPusher;

    private String leftBeaconButtonPusherStringArg = "leftBeaconPusher";
    private String rightBeaconButtonPusherStringArg = "rightBeaconPusher";

    private double testBeaconServoPosition = 0.5;
    private double testBeaconServoPositionB = 0.5;

    GMRColorSensor colorSensors;

    private boolean hasPushed = false;
    private boolean isStraight = false;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //constructors with all references
    public BeaconNav(HardwareMap hardwareMap, Telemetry telemetry) {
        try {
            colorSensors = new GMRColorSensor(hardwareMap, telemetry);
        } catch(NullPointerException e) {
            telemetry.addData("Color Sensors Failed", "");
        }
        //telemetry.addData("BeaconNav Startup", "Beginning");
        //setup for all color sensors
            //setup for the beacon
        proxSensorLeft = hardwareMap.opticalDistanceSensor.get(proxSensorLeftName);
        proxSensorLeft.enableLed(proxLightOnOff);
        //setup for all miscellaneous variable
        this.telemetry = telemetry;
        //this.colorFactor = colorFactor;

        leftBeaconButtonPusher = hardwareMap.servo.get(leftBeaconButtonPusherStringArg);
        rightBeaconButtonPusher = hardwareMap.servo.get(rightBeaconButtonPusherStringArg);

//        leftBeaconButtonPusher.setPosition(0.577);
//        rightBeaconButtonPusher.setPosition(0.797);

        //telemetry.addData("BeaconNav Startup", "End");
        telemetry.update();
    }

    public void BeaconPusher(WhichBeaconPusherPosition whichBeaconPusherPosition) {
        if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDLEFTBEACONPUSHER) {
            leftBeaconButtonPusher.setPosition(.7);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDRIGHTBEACONPUSHER) {
            rightBeaconButtonPusher.setPosition(.679);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDBOTHPUSHERS) {
            leftBeaconButtonPusher.setPosition(.712);
            rightBeaconButtonPusher.setPosition(.679);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.RETRACTBOTHPUSHERS) {
            leftBeaconButtonPusher.setPosition(.56);
            rightBeaconButtonPusher.setPosition(.797);
        }
    }

    public void teleOpBeaconPush(boolean x) {
        if (x) {
            leftBeaconButtonPusher.setPosition(.7);
            rightBeaconButtonPusher.setPosition(.679);
        } else {
            leftBeaconButtonPusher.setPosition(.56);
            rightBeaconButtonPusher.setPosition(.797);
        }
    }

    public void fixBeaconServos(boolean a, boolean b) {
        if (a) {
            testBeaconServoPosition -= 0.001;
            testBeaconServoPositionB += 0.001;
        } else if (b) {
            testBeaconServoPosition += 0.001;
            testBeaconServoPositionB -= 0.001;
        }
        leftBeaconButtonPusher.setPosition(testBeaconServoPosition);
        rightBeaconButtonPusher.setPosition(testBeaconServoPositionB);
        telemetry.addData("Current Left Position", leftBeaconButtonPusher.getPosition());
        telemetry.addData("Current Right Position", rightBeaconButtonPusher.getPosition());
    }

    public void fixBeaconServos(boolean a, boolean b, boolean x, boolean y) {
        if (a) {
            testBeaconServoPosition -= 0.001;
        } else if (b) {
            testBeaconServoPositionB += 0.001;
        } else if (x) {
            testBeaconServoPosition += 0.001;
        } else if (y) {
            testBeaconServoPositionB -= 0.001;
        }
        leftBeaconButtonPusher.setPosition(testBeaconServoPosition);
        rightBeaconButtonPusher.setPosition(testBeaconServoPositionB);
        telemetry.addData("Current Left Position", leftBeaconButtonPusher.getPosition());
        telemetry.addData("Current Right Position", rightBeaconButtonPusher.getPosition());
    }

    public boolean pushRed() {
        if (!hasPushed) {
            if (colorSensors.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED, GMRColorSensor.Color.BLUE) == GMRColorSensor.Color.RED) {
                BeaconPusher(WhichBeaconPusherPosition.EXTENDLEFTBEACONPUSHER);

                if (leftBeaconButtonPusher.getPosition() == .59) {
                    hasPushed = true;
                }
            } else {
                BeaconPusher(WhichBeaconPusherPosition.EXTENDRIGHTBEACONPUSHER);
                if (rightBeaconButtonPusher.getPosition() == .83) {
                    hasPushed = true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    public boolean pushBlue() {
        if (!hasPushed) {
            if (colorSensors.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED, GMRColorSensor.Color.BLUE) == GMRColorSensor.Color.RED) {
                BeaconPusher(WhichBeaconPusherPosition.EXTENDRIGHTBEACONPUSHER);
                if (rightBeaconButtonPusher.getPosition() == .83) {
                    hasPushed = true;
                }
            } else {
                BeaconPusher(WhichBeaconPusherPosition.EXTENDLEFTBEACONPUSHER);
                if (leftBeaconButtonPusher.getPosition() == .59) {
                    hasPushed = true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    public void stopBeaconNav(){
        BeaconPusher(WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PROXS
    public void turnOnLightProx(boolean ONOFF) {
        proxSensorLeft.enableLed(ONOFF);}
    public double getRawDistance() {return proxSensorLeft.getLightDetected();}
    public double getDistance() {return (proxSensorLeft.getLightDetected() * 1000);}
    public double rawLight() {return proxSensorLeft.getRawLightDetected();}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENUMS
    public enum Color {BLUE, RED, GREEN, EQUAL}
    public enum WhichGMRColorSensor{GROUNDLEFT, GROUNDRIGHT, BEACON}
    public enum WhichBeaconPusherPosition {EXTENDLEFTBEACONPUSHER, EXTENDRIGHTBEACONPUSHER, EXTENDBOTHPUSHERS, RETRACTBOTHPUSHERS}
}