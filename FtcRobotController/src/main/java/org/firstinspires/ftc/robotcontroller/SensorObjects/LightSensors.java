package org.firstinspires.ftc.robotcontroller.SensorObjects;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
public class LightSensors extends MoveMotors{
    LightSensor lightSensor;
    public LightSensors(LightSensor lightSensor) {
        this.lightSensor = lightSensor;
        this.lightSensor.enableLed(false);
    }
    public LightSensors(LightSensor lightSensor, boolean OnOff) {
        this.lightSensor = lightSensor;
        this.lightSensor.enableLed(OnOff);
    }
    public void enableLight(boolean onoff) {
        lightSensor.enableLed(onoff);
    }
    public double LevelOfLight() {
        return 8 * lightSensor.getLightDetected();
    }
    public ColorSensors.whichColor WhichColor() {
        if(lightSensor.getLightDetected() < 1) {
            return ColorSensors.whichColor.BLACK;
        }
        else if(lightSensor.getLightDetected() > 1) {
            return ColorSensors.whichColor.WHITE;
        }
        else {
            return ColorSensors.whichColor.EQUAL;
        }
    }
}