package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 10/9/2016
 */
public class Hardwaresetup {

    private final int NAVX_DIM_I2C_PORT = 0;

    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftRear;
    public DcMotor rightRear;

    public ColorSensor colorSensorBeacon;
    public ColorSensor colorSensorGroundLeft;
    public ColorSensor colorSensorGroundRight;

    public LightSensor lightSensor;
    public OpticalDistanceSensor proxSensor;

    public Telemetry telemetry;

    HardwareMap hwMap;

    AHRS ahrs;

    public Hardwaresetup(){

    }

    public void init(HardwareMap hardwaremap) {

        hwMap = hardwaremap;

        leftFront = hwMap.dcMotor.get("leftfront");
        rightFront = hwMap.dcMotor.get("rightfront");
        leftRear = hwMap.dcMotor.get("leftrear");
        rightRear = hwMap.dcMotor.get("rightrear");

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ahrs = AHRS.getInstance(hwMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
        ahrs.zeroYaw();

        colorSensorBeacon = hwMap.colorSensor.get("colorSensorBeacon");
        colorSensorGroundLeft = hwMap.colorSensor.get("colorSensorGroundLeft");
        colorSensorGroundRight = hwMap.colorSensor.get("colorSensorGroundRight");

        proxSensor = hwMap.opticalDistanceSensor.get("proxSensor");
    }
}
