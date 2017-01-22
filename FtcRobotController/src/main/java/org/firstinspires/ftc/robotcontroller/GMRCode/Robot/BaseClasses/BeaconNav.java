package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class BeaconNav {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// COLOR V
    //All color sensor
        //sensor for detecting the beacon
    private ColorSensor colorSensorBeacon;
        //sensor for detecting the ground on the left side of the robot
    private ColorSensor colorSensorGroundLeft;
        //sensor for detecting the ground on the right side of the robot
    private ColorSensor colorSensorGroundRight;

    private String ColorSensorBeacon = "CSBeacon";
    private String ColorSensorGroundLeft = "CSGroundLeft";
    private String ColorSensorGroundRight = "CSGroundRight";

    boolean beaconLightOn = false;
    boolean groundLeftLightOn = true;
    boolean groundRightLightOn = true;
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

    private Servo beaconServoColor;
    public Servo beaconServo;

    private String beaconServoColorName = "beaconServoColor";
    private String beaconServoName = "beaconServo";

    private double beaconServoPosition = 0.39;
    private double beaconServoPositionB = 0.63;

    private String mostRecentCommand;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //constructors with all references
    public BeaconNav(HardwareMap hardwareMap, Telemetry telemetry) {
        telemetry.addData("BeaconNav Startup", "Beginning");
        telemetry.update();
        //setup for all color sensors
            //setup for the beacon
        colorSensorBeacon = hardwareMap.colorSensor.get(ColorSensorBeacon);
        colorSensorBeacon.setI2cAddress(I2cAddr.create7bit(0x26));
            //setup for the bottom left color sensor
        colorSensorGroundLeft = hardwareMap.colorSensor.get(ColorSensorGroundLeft);
        colorSensorGroundLeft.setI2cAddress(I2cAddr.create7bit(0x16));
            //setup for the bottom right color sensor
        colorSensorGroundRight = hardwareMap.colorSensor.get(ColorSensorGroundRight);
        //setup for the prox sensor
        proxSensor = hardwareMap.opticalDistanceSensor.get(ProxSensor);
        //turns lights on or off
        colorSensorBeacon.enableLed(beaconLightOn);
        colorSensorGroundLeft.enableLed(groundLeftLightOn);
        colorSensorGroundRight.enableLed(groundRightLightOn);
        proxSensor.enableLed(proxLightOnOff);
        //setup for all miscellaneous variable
        this.telemetry = telemetry;
        this.colorFactor = colorFactor;

        beaconServoColor = hardwareMap.servo.get(beaconServoColorName);
        beaconServo = hardwareMap.servo.get(beaconServoName);

        beaconServoColor.setPosition(0.63);
        beaconServo.setPosition(0.39);

        telemetry.addData("BeaconNav Startup", "End");
        telemetry.update();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// COLOR
    //get raw value of the color sensor
    public double getColorValue(Color desiredColor, WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getColorValue] desiredColor: "+desiredColor+" whichColorSensor: "+whichColorSensor;
        if(desiredColor == Color.RED) {return this.getRed(whichColorSensor);}
        else if(desiredColor == Color.BLUE) {return this.getBlue(whichColorSensor);}
        else {return this.getGreen(whichColorSensor);}
    }
    //logic methods
    public Color whichGreaterColor(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[whichGreaterColor] whichColorSensor: "+whichColorSensor;
        if (getColorValue(Color.RED, whichColorSensor) > getColorValue(Color.BLUE, whichColorSensor)) {return Color.RED; }
        else if (getColorValue(Color.RED, whichColorSensor) == getColorValue(Color.BLUE, whichColorSensor)) {return Color.EQUAL; }
        else {return Color.BLUE;}
    }
    public Color whichGreaterColor(WhichGMRColorSensor whichColorSensor, Color firstColor, Color secondColor) {
        mostRecentCommand = "[whichGreaterColor] whichColorSensor: "+whichColorSensor+" Color<first>: "+firstColor+" Color<second>: "+secondColor;
        if (getColorValue(firstColor, whichColorSensor) > getColorValue(secondColor, whichColorSensor)) {return firstColor; }
        else if((getColorValue(firstColor, whichColorSensor) > getColorValue(secondColor, whichColorSensor))) {return Color.EQUAL;}
        else {return secondColor;}
    }
    //returns true or false to indicate if this is the correct color
    public boolean isColor(WhichGMRColorSensor whichColorSensor, Color desiredColor) {
        mostRecentCommand = "[isColor] desiredColor: "+desiredColor+" whichColorSensor: "+whichColorSensor;
        if(getColorValue(desiredColor, whichColorSensor) > 5) {return true;}
        else {return false;}
    }
    public boolean isWhite(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[isWhite] whichColorSensor: "+whichColorSensor;
        if( (getRed(whichColorSensor) + getBlue(whichColorSensor) + getGreen(whichColorSensor))> 100) {return true;}
        else {return false;}
    }
    //private methods for getting the value of the color sensor
    public double getRed(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getRed] whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorGroundRight.red() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundRight.red() * 8);}
        else {return (colorSensorGroundRight.red() * 8);}
    }
    private double getBlue(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getBlue] whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorGroundRight.blue() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundRight.blue() * 8);}
        else {return (colorSensorGroundRight.blue() * 8);}
    }
    private double getGreen(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getGreen] whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorGroundRight.green() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundRight.green() * 8);}
        else {return (colorSensorGroundRight.green() * 8);}
    }
    //methods for altering the color sensors
    public void turnOnLightColor(boolean ONOFF, WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[turnOnLightColor] ONOFF: "+ONOFF+" whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {colorSensorBeacon.enableLed(ONOFF);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {colorSensorGroundLeft.enableLed(ONOFF);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDRIGHT) {colorSensorGroundRight.enableLed(ONOFF);}
    }
    public void BeaconPusher(boolean up, boolean down) {

        if (up && !down) {
            beaconServo.setPosition(.41);
            beaconServoColor.setPosition(0.59);
        } else if (down && !up) {
            beaconServo.setPosition(.17);
            beaconServoColor.setPosition(0.83);
        }
        telemetry.addData("Current Position", beaconServoPosition);
        telemetry.addData("Actual Position", beaconServo.getPosition());
        telemetry.addData("Actual Position Color", beaconServoColor.getPosition());
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// PROXS
    public void turnOnLightProx(boolean ONOFF) {mostRecentCommand = "[turnOnLightColor] ONOFF: "+ONOFF; proxSensor.enableLed(ONOFF);}
    public double getRawDistance() {mostRecentCommand = "[getRawDistance]";  return proxSensor.getLightDetected();}
    public double getDistance() {mostRecentCommand = "[getDistance]"; return (proxSensor.getLightDetected() * 1000);}
    public double rawLight() {mostRecentCommand = "[rawLight]"; return proxSensor.getRawLightDetected();}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENUMS
    public enum Color {BLUE, RED, GREEN, EQUAL}
    public enum WhichGMRColorSensor{GROUNDLEFT, GROUNDRIGHT, BEACON}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEBUG
    public String getDebugCommand() {
        return mostRecentCommand;
    }
}