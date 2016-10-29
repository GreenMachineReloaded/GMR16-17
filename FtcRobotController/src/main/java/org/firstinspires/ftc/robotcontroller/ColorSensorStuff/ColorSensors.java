package org.firstinspires.ftc.robotcontroller.ColorSensorStuff;
import com.qualcomm.robotcore.hardware.ColorSensor;
public class ColorSensors {
    private ColorSensor colorSensor;
    public ColorSensors(ColorSensor colorArg) {
        colorSensor = colorArg;
        colorSensor.enableLed(false);
    }
    public ColorSensors(ColorSensor colorArg, boolean OnOff) {
        colorSensor = colorArg;
        colorSensor.enableLed(OnOff);
    }
    public void turnOnLight(boolean OnOff) {
        colorSensor.enableLed(OnOff);
    }
    public double getRed() {
        return (8 * colorSensor.red());
    }
    public double getBlue() {
        return (8 * colorSensor.blue());
    }
    public ColorSensors.whichColor greaterColor() {
        if (getRed() > getBlue()) {
            return whichColor.RED;
        } else if (getBlue() > getRed()) {
            return whichColor.BLUE;
        } else {
            return whichColor.EQUAL;
        }
    }
    public enum whichColor {
        BLUE, RED, EQUAL
    }
}
