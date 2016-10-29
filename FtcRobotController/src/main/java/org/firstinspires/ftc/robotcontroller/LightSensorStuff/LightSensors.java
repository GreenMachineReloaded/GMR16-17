package org.firstinspires.ftc.robotcontroller.LightSensorStuff;
import android.test.mock.MockApplication;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
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
    public void Drive(Directions direction, double power) {
        while(lightSensor.getLightDetected() < 1) {
            super.Drive(direction, power);
        }
        super.Stop();
    }

}