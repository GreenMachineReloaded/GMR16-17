package org.firstinspires.ftc.robotcontroller.SensorObjects;
import com.qualcomm.robotcore.hardware.ColorSensor;
public class ColorSensors {
    private ColorSensor colorSensor;
    public ColorSensors(ColorSensor colorSensor) {
        this.colorSensor = colorSensor;
        this.colorSensor.enableLed(false);
    }
    public ColorSensors(ColorSensor colorSensor, boolean OnOff) {
        this.colorSensor = colorSensor;
        this.colorSensor.enableLed(OnOff);
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

    public ColorSensors.whichColor isWhite() {
            return whichColor.WHITE;
        }
        else {
            return whichColor.BLACK;
        }
    }

    public enum whichColor {
        BLUE, RED, BLACK, WHITE, EQUAL
    }
    public enum whichColorSensor{
        GROUNDLEFT, GROUNDRIGHT, BEACON

    }
}
