package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
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
    public DcMotor sweeperMotor;
    public DcMotor launchMotor;
    public DcMotor ballLiftMotor;

    public Servo hopperDoorServo;
    public Servo ballLiftServo;

    public ColorSensor colorSensorBeacon;
    public ColorSensor colorSensorGroundLeft;
    public ColorSensor colorSensorGroundRight;

    public OpticalDistanceSensor proxSensor;

    public Telemetry telemetry;

    HardwareMap hwMap;

    AHRS ahrs;

    Continue sleep;

    public Hardwaresetup(){

    }

    public void init(HardwareMap hardwaremap) {

        sleep = new Continue();

        hwMap = hardwaremap;

        leftFront = hwMap.dcMotor.get("leftfront");
        rightFront = hwMap.dcMotor.get("rightfront");
        leftRear = hwMap.dcMotor.get("leftrear");
        rightRear = hwMap.dcMotor.get("rightrear");
        sweeperMotor = hwMap.dcMotor.get("sweepermotor");
        launchMotor = hwMap.dcMotor.get("launchmotor");
        ballLiftMotor = hwMap.dcMotor.get("balllift");

        hopperDoorServo = hwMap.servo.get("hopperdoorservo");
        ballLiftServo = hwMap.servo.get("ballliftservo");

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep.Sleep(5);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sweeperMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ahrs = AHRS.getInstance(hwMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
        ahrs.zeroYaw();

        colorSensorBeacon = hwMap.colorSensor.get("CSBeacon");
        colorSensorBeacon.setI2cAddress(I2cAddr.create7bit(0x26));
        colorSensorGroundLeft = hwMap.colorSensor.get("CSGroundLeft");
        colorSensorGroundLeft.setI2cAddress(I2cAddr.create7bit(0x16));
        colorSensorGroundLeft.enableLed(true);
        colorSensorGroundRight = hwMap.colorSensor.get("CSGroundRight");

        proxSensor = hwMap.opticalDistanceSensor.get("proxSensor");
    }
}
