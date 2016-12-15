package org.firstinspires.ftc.robotcontroller.GMRCode.Robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 12/15/2016
 */
@SuppressWarnings("FieldCanBeLocal")

public class BeaconNav {

    private ColorSensor colorSensorBeacon;
    private ColorSensor colorSensorGroundLeft;
    private ColorSensor colorSensorGroundRight;

    private OpticalDistanceSensor proxSensor;

    public BeaconNav(HardwareMap hwMap, Telemetry Telemetry) {

        colorSensorBeacon = hwMap.colorSensor.get("CSBeacon");
        colorSensorBeacon.setI2cAddress(I2cAddr.create7bit(0x26));
        colorSensorGroundLeft = hwMap.colorSensor.get("CSGroundLeft");
        colorSensorGroundLeft.setI2cAddress(I2cAddr.create7bit(0x16));
        colorSensorGroundLeft.enableLed(true);
        colorSensorGroundRight = hwMap.colorSensor.get("CSGroundRight");

        proxSensor = hwMap.opticalDistanceSensor.get("proxSensor");

    }

}
