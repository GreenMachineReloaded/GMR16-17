package org.firstinspires.ftc.robotcontroller.SensorObjects;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

public class ProxSensors {
    OpticalDistanceSensor ODS;
    public ProxSensors(OpticalDistanceSensor ODS) {
        this.ODS = ODS;
        turnOnLight(true);
    }
    public ProxSensors(OpticalDistanceSensor ODS, boolean ONOFF) {
        this.ODS = ODS;
        turnOnLight(ONOFF);
    }
    public double getDistance() {
        return ODS.getLightDetected();
    }
    public void turnOnLight(boolean ONOFF) {
        ODS.enableLed(ONOFF);
    }
    public double rawLight() {
        return ODS.getRawLightDetected();
    }
}
