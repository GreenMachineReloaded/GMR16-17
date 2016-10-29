package org.firstinspires.ftc.robotcontroller.LightSensorStuff;
import android.test.mock.MockApplication;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
public class LightSensors extends MoveMotors{
    LightSensor lightSensor;
    public LightSensors(LightSensor lightSensor) {
        lightSensor.enableLed(false);
        this.lightSensor = lightSensor;
    }
    public void enableLight(boolean onoff) {
        lightSensor.enableLed(onoff);
    }
    public double LevelOfLight() {
        return lightSensor.getLightDetected();
    }
    public void CHANGENAME(Directions d) {
        switch (d) {
            case Forward:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case Backward:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case StrafeLeft:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case StrafeRight:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case DLeftUp:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case DRightUp:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case DLeftDown:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
            case DRightDown:
                while (lightSensor.getLightDetected() < 1) {
                    super.driveForward();
                }
                break;
        }

    }

}
