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

    private Servo leftBeaconButtonPusher;
    private Servo rightBeaconButtonPusher;

    private String leftBeaconButtonPusherStringArg = "leftBeaconPusher";
    private String rightBeaconButtonPusherStringArg = "rightBeaconPusher";

    private double beaconServoPosition = 0.39;
    private double beaconServoPositionB = 0.63;

    private boolean hasPushed = false;
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

        leftBeaconButtonPusher = hardwareMap.servo.get(leftBeaconButtonPusherStringArg);
        rightBeaconButtonPusher = hardwareMap.servo.get(rightBeaconButtonPusherStringArg);

        leftBeaconButtonPusher.setPosition(0.63);
        rightBeaconButtonPusher.setPosition(0.39);

        telemetry.addData("BeaconNav Startup", "End");
        telemetry.update();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// COLOR
    //get raw value of the color sensor
    public double getColorValue(Color desiredColor, WhichGMRColorSensor whichSensor) {
        if(desiredColor == Color.RED) {return this.getRed(whichSensor);}
        else if(desiredColor == Color.BLUE) {return this.getBlue(whichSensor);}
        else {return this.getGreen(whichSensor);}
    }
    //logic methods
    public Color whichGreaterColor(WhichGMRColorSensor whichColorSensor) {
        if (getColorValue(Color.RED, whichColorSensor) > getColorValue(Color.BLUE, whichColorSensor)) {
            return Color.RED;
        }
        else if (getColorValue(Color.RED, whichColorSensor) == getColorValue(Color.BLUE, whichColorSensor)) {
            return Color.EQUAL;
        }
        else {
            return Color.BLUE;
        }
    }
    public Color whichGreaterColor(WhichGMRColorSensor whichColorSensor, Color firstColor, Color secondColor) {
        if (getColorValue(firstColor, whichColorSensor) > getColorValue(secondColor, whichColorSensor)) {return firstColor; }
        else if((getColorValue(firstColor, whichColorSensor) > getColorValue(secondColor, whichColorSensor))) {return Color.EQUAL;}
        else {return secondColor;}
    }
    //returns true or false to indicate if this is the correct color
    public boolean isColor(WhichGMRColorSensor whichColorSensor, Color desiredColor) {
        if(getColorValue(desiredColor, whichColorSensor) > 5) {return true;}
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
        else {return (colorSensorGroundRight.red() * 8);}
    }
    private double getBlue(WhichGMRColorSensor whichSensor) {
        if(whichSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.blue() * 8);}
        else if(whichSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.blue() * 8);}
        else {return (colorSensorGroundRight.blue() * 8);}
    }
    private double getGreen(WhichGMRColorSensor whichSensor) {
        if(whichSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.green() * 8);}
        else if(whichSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.green() * 8);}
        else {return (colorSensorGroundRight.green() * 8);}
    }
    //methods for altering the color sensors
    public void turnOnLightColor(boolean OnOff, WhichGMRColorSensor whichColorSensor) {
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {colorSensorBeacon.enableLed(OnOff);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {colorSensorGroundLeft.enableLed(OnOff);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDRIGHT) {colorSensorGroundLeft.enableLed(OnOff);}
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
            if (whichGreaterColor(WhichGMRColorSensor.BEACON) == Color.BLUE) {
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
            if (whichGreaterColor(WhichGMRColorSensor.BEACON) == Color.BLUE) {
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