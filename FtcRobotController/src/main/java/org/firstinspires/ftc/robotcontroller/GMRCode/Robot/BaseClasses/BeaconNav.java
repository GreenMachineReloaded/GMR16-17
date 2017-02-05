package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
<<<<<<< HEAD
=======

import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
>>>>>>> refs/remotes/origin/m-Automaintenence
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

<<<<<<< HEAD
    private double beaconServoPosition = 0.39;
    private double beaconServoPositionB = 0.63;

    private String mostRecentCommand;
=======
    private double testBeaconServoPosition = 0.5;
    private double testBeaconServoPositionB = 0.5;

    GMRColorSensor colorSensors;

    private boolean hasPushed = false;
>>>>>>> refs/remotes/origin/m-Automaintenence
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
<<<<<<< HEAD
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
    private double getRed(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getRed] whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.red() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.red() * 8);}
        else {return (colorSensorGroundRight.red() * 8);}
    }
    private double getBlue(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getBlue] whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.blue() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.blue() * 8);}
        else {return (colorSensorGroundRight.blue() * 8);}
    }
    private double getGreen(WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[getGreen] whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {return (colorSensorBeacon.green() * 8);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {return (colorSensorGroundLeft.green() * 8);}
        else {return (colorSensorGroundRight.green() * 8);}
    }
    //methods for altering the color sensors
    public void turnOnLightColor(boolean ONOFF, WhichGMRColorSensor whichColorSensor) {
        mostRecentCommand = "[turnOnLightColor] ONOFF: "+ONOFF+" whichColorSensor: "+whichColorSensor;
        if(whichColorSensor == WhichGMRColorSensor.BEACON) {colorSensorBeacon.enableLed(ONOFF);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDLEFT) {colorSensorGroundLeft.enableLed(ONOFF);}
        else if(whichColorSensor == WhichGMRColorSensor.GROUNDRIGHT) {colorSensorGroundRight.enableLed(ONOFF);}
=======

    public void BeaconPusher(WhichBeaconPusherPosition whichBeaconPusherPosition) {
        if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDLEFTBEACONPUSHER) {
            leftBeaconButtonPusher.setPosition(.9);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDRIGHTBEACONPUSHER) {
            rightBeaconButtonPusher.setPosition(.17);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.EXTENDBOTHPUSHERS) {
            leftBeaconButtonPusher.setPosition(.9);
            rightBeaconButtonPusher.setPosition(.17);
        }
        else if(whichBeaconPusherPosition == WhichBeaconPusherPosition.RETRACTBOTHPUSHERS) {
            leftBeaconButtonPusher.setPosition(.63);
            rightBeaconButtonPusher.setPosition(.41);
        }
    }

    public void teleOpBeaconPush(boolean x) {
        if (x) {
            leftBeaconButtonPusher.setPosition(.9);
            rightBeaconButtonPusher.setPosition(.17);
        } else {
            leftBeaconButtonPusher.setPosition(.63);
            rightBeaconButtonPusher.setPosition(.41);
        }
    }

    public void beaconServos(boolean a, boolean b) {
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
>>>>>>> refs/remotes/origin/m-Automaintenence
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
    public void turnOnLightProx(boolean ONOFF) {mostRecentCommand = "[turnOnLightColor] ONOFF: "+ONOFF; proxSensor.enableLed(ONOFF);}
    public double getRawDistance() {mostRecentCommand = "[getRawDistance]";  return proxSensor.getLightDetected();}
    public double getDistance() {mostRecentCommand = "[getDistance]"; return (proxSensor.getLightDetected() * 1000);}
    public double rawLight() {mostRecentCommand = "[rawLight]"; return proxSensor.getRawLightDetected();}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ENUMS
    public enum Color {BLUE, RED, GREEN, EQUAL}
    public enum WhichGMRColorSensor{GROUNDLEFT, GROUNDRIGHT, BEACON}
<<<<<<< HEAD
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEBUG
    public String getDebugCommand() {
        return mostRecentCommand;
    }
=======
    public enum WhichBeaconPusherPosition {EXTENDLEFTBEACONPUSHER, EXTENDRIGHTBEACONPUSHER, EXTENDBOTHPUSHERS, RETRACTBOTHPUSHERS}
>>>>>>> refs/remotes/origin/m-Automaintenence
}