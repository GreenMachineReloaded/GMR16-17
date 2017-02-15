package org.firstinspires.ftc.robotcontroller.SensorObjects;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class GMRColorSensor {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// COLOR V
    //All color sensor
    //sensor for detecting the beacon
    private ColorSensor colorSensorBeacon;
    //sensor for detecting the ground on the left side of the robot
    private ColorSensor colorSensorGroundLeft;
    //sensor for detecting the ground on the right side of the robot

    private String ColorSensorBeacon = "CSBeacon";
    private String ColorSensorGroundLeft = "CSGroundLeft";


    boolean beaconLightOn = false;
    boolean groundLeftLightOn = true;

    private double colorFactor = 8;

    private Servo leftBeaconButtonPusher;
    private Servo rightBeaconButtonPusher;

    private String leftBeaconButtonPusherStringArg = "leftBeaconPusher";
    private String rightBeaconButtonPusherStringArg = "rightBeaconPusher";

    private Telemetry telemetry;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCT
    //constructors with all references
    public GMRColorSensor(HardwareMap hardwareMap, Telemetry telemetry) {
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
        colorSensorBeacon.enableLed(beaconLightOn);
        colorSensorGroundLeft.enableLed(groundLeftLightOn);

        this.telemetry = telemetry;

        leftBeaconButtonPusher = hardwareMap.servo.get(leftBeaconButtonPusherStringArg);
        rightBeaconButtonPusher = hardwareMap.servo.get(rightBeaconButtonPusherStringArg);

        leftBeaconButtonPusher.setPosition(0.63);
        rightBeaconButtonPusher.setPosition(0.39);

        telemetry.addData("BeaconNav Startup", "End");
        telemetry.update();
    }
    public double getColorValue(Color desiredColor, WhichGMRColorSensor whichSensor) {
        if(desiredColor == Color.RED) {return this.getRed(whichSensor);}
        else if(desiredColor == Color.BLUE) {return this.getBlue(whichSensor);}
        else {return this.getGreen(whichSensor);}
    }
    public Color whichGreaterColor(WhichGMRColorSensor whichColorSensor, Color firstColor, Color secondColor) {
        if (getColorValue(firstColor, whichColorSensor) > getColorValue(secondColor, whichColorSensor)) {return firstColor; }
        else if((getColorValue(firstColor, whichColorSensor) > getColorValue(secondColor, whichColorSensor))) {return Color.EQUAL;}
        else {return secondColor;}
    }
    //returns true or false to indicate if this is the correct color
    public boolean whichGreaterColor(WhichGMRColorSensor whichColorSensor, Color desiredColor) {
        if(getColorValue(desiredColor, whichColorSensor) > 6) {return true;}
        else {return false;}
    }
    public boolean isWhite(WhichGMRColorSensor whichColorSensor) {
        if( (getRed(whichColorSensor) + getBlue(whichColorSensor) + getGreen(whichColorSensor))> 100) {return true;}
        else {return false;}
    }
    //private methods for getting the value of the color sensor
    public double getRed(WhichGMRColorSensor whichColorSensor) {
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.red() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.red() * 8);}
        return 0;
    }
    public double getBlue(WhichGMRColorSensor whichSensor) {
        if(whichSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.blue() * 8);}
        else if(whichSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.blue() * 8);}
        return 0;
    }
    public double getGreen(WhichGMRColorSensor whichSensor) {
        if(whichSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.green() * 8);}
        else if(whichSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.green() * 8);}
        return 0;
    }
    //methods for altering the color sensors
    public enum Color {BLUE, RED, GREEN, EQUAL}
    public enum WhichGMRColorSensor{GROUNDLEFT, BEACON}
}