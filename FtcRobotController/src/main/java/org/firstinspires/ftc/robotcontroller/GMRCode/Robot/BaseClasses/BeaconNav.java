package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
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

    private double beaconServoPosition = 0.39;
    private double beaconServoPositionB = 0.63;

    private String mostRecentCommand;
    GMRColorSensor colorSensors;

    private boolean hasPushed = false;
    private boolean isStraight = false;

    byte[] range1Cache; //The read will return an array of bytes. They are stored in this variable

    private I2cAddr RANGE1ADDRESS = new I2cAddr(0x14); //Default I2C address for MR Range (7-bit)
    private static final int RANGE1_REG_START = 0x04; //Register to start reading
    private static final int RANGE1_READ_LENGTH = 2; //Number of byte to read

    private I2cDevice RANGE1;
    private I2cDeviceSynch RANGE1Reader;

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

        RANGE1 = hardwareMap.i2cDevice.get("range");
        RANGE1Reader = new I2cDeviceSynchImpl(RANGE1, RANGE1ADDRESS, false);
        RANGE1Reader.engage();

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

    public boolean checkColor(GMRColorSensor.Color color) {
        if (color == GMRColorSensor.Color.RED) {
            if (colorSensors.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED, GMRColorSensor.Color.BLUE) == GMRColorSensor.Color.RED) {
                return true;
            } else {
                return false;
            }
        } else {
            if (!(colorSensors.whichGreaterColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED, GMRColorSensor.Color.BLUE) == GMRColorSensor.Color.RED)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void stopBeaconNav(){
        BeaconPusher(WhichBeaconPusherPosition.RETRACTBOTHPUSHERS);
    }

    public int ultrasonicDistance() {
        range1Cache = RANGE1Reader.read(RANGE1_REG_START, RANGE1_READ_LENGTH);
        return range1Cache[0] & 0xFF;
    }

    public int IRDistance() {
        range1Cache = RANGE1Reader.read(RANGE1_REG_START, RANGE1_READ_LENGTH);
        return range1Cache[1] & 0xFF;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PROXS
    public void turnOnLightProx(boolean ONOFF) {mostRecentCommand = "[turnOnLightColor] ONOFF: "+ONOFF; proxSensorLeft.enableLed(ONOFF);}
    public double getRawDistance() {mostRecentCommand = "[getRawDistance]";  return proxSensorLeft.getLightDetected();}
    public double getDistance() {mostRecentCommand = "[getDistance]"; return (proxSensorLeft.getLightDetected() * 1000);}
    public double rawLight() {mostRecentCommand = "[rawLight]"; return proxSensorLeft.getRawLightDetected();}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENUMS
    public enum Color {BLUE, RED, GREEN, EQUAL}
    public enum WhichGMRColorSensor{GROUNDLEFT, GROUNDRIGHT, BEACON}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEBUG
    public String getDebugCommand() {
        return mostRecentCommand;
    }
    public enum WhichBeaconPusherPosition {EXTENDLEFTBEACONPUSHER, EXTENDRIGHTBEACONPUSHER, EXTENDBOTHPUSHERS, RETRACTBOTHPUSHERS}
}