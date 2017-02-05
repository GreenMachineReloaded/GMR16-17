package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class BeaconNav {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PROX V
    //sensor for detecting the wall
    private OpticalDistanceSensor proxSensor;
    private String ProxSensor = "proxSensor";
    boolean proxLightOnOff = true;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MISS V
    private Telemetry telemetry;
    private double colorFactor = 8;

    private Servo leftBeaconButtonPusher;
    private Servo rightBeaconButtonPusher;

    private String leftBeaconButtonPusherStringArg = "leftBeaconPusher";
    private String rightBeaconButtonPusherStringArg = "rightBeaconPusher";

    private double beaconServoPosition = 0.39;
    private double beaconServoPositionB = 0.63;

    GMRColorSensor colorSensors;

    private boolean hasPushed = false;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //constructors with all references
    public BeaconNav(HardwareMap hardwareMap, Telemetry telemetry) {
        colorSensors = new GMRColorSensor(hardwareMap, telemetry);
        telemetry.addData("BeaconNav Startup", "Beginning");
        telemetry.update();
        //setup for all color sensors
            //setup for the beacon
        proxSensor.enableLed(proxLightOnOff);
        //setup for all miscellaneous variable
        this.telemetry = telemetry;
        this.colorFactor = colorFactor;

        leftBeaconButtonPusher = hardwareMap.servo.get(leftBeaconButtonPusherStringArg);
        rightBeaconButtonPusher = hardwareMap.servo.get(rightBeaconButtonPusherStringArg);

        leftBeaconButtonPusher.setPosition(0.63);
        rightBeaconButtonPusher.setPosition(0.39);

        telemetry.addData("BeaconNav Startup", "End");
        telemetry.update();
    }

    public void BeaconPusher(WhichBeaconPusherPosition whichBeaconPusherPosition) {
        if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDLEFTBEACONPUSHER) {
            leftBeaconButtonPusher.setPosition(.59);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDRIGHTBEACONPUSHER) {
            rightBeaconButtonPusher.setPosition(.83);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDBOTHPUSHERS) {
            leftBeaconButtonPusher.setPosition(.59);
            rightBeaconButtonPusher.setPosition(.83);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.RETRACTBOTHPUSHERS) {
            leftBeaconButtonPusher.setPosition(.17);
            rightBeaconButtonPusher.setPosition(.41);
        }
    }

    public boolean pushRed() {
        if (!hasPushed) {
            if (colorSensors.isColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.RED)) {
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

    public boolean pushBlue() {
        if (!hasPushed) {
            if (colorSensors.isColor(GMRColorSensor.WhichGMRColorSensor.BEACON, GMRColorSensor.Color.BLUE)) {
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PROXS
    public void turnOnLightProx(boolean ONOFF) {proxSensor.enableLed(ONOFF);}
    public double getRawDistance() {return proxSensor.getLightDetected();}
    public double getDistance() {return (proxSensor.getLightDetected() * 1000);}
    public double rawLight() {return proxSensor.getRawLightDetected();}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENUMS
    public enum Color {BLUE, RED, GREEN, EQUAL}
    public enum WhichGMRColorSensor{GROUNDLEFT, GROUNDRIGHT, BEACON}
    public enum WhichBeaconPusherPosition {EXTENDLEFTBEACONPUSHER, EXTENDRIGHTBEACONPUSHER, EXTENDBOTHPUSHERS, RETRACTBOTHPUSHERS}
}