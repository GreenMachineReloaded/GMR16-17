package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
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
    public DcMotor sweeper;
    public DcMotor launcher;
    public DcMotor ballLift;

    public Servo ballLiftServo;
    public Servo launchAim;

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
        sweeper = hwMap.dcMotor.get("sweeper");
        launcher = hwMap.dcMotor.get("launcher");
        ballLift = hwMap.dcMotor.get("balllift");

        ballLiftServo = hwMap.servo.get("ballliftservo");
        launchAim = hwMap.servo.get("launchaim");

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep.Sleep(5);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ahrs = AHRS.getInstance(hwMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);
        ahrs.zeroYaw();

        colorSensorBeacon = hwMap.colorSensor.get("colorSensorBeacon");
        colorSensorGroundLeft = hwMap.colorSensor.get("colorSensorGroundLeft");
        colorSensorGroundRight = hwMap.colorSensor.get("colorSensorGroundRight");

        lightSensor = hwMap.lightSensor.get("lightSensor");
        proxSensor = hwMap.opticalDistanceSensor.get("proxSensor");
    }
}
