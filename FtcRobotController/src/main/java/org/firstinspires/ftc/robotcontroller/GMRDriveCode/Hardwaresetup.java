package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 10/9/2016
 */
public class Hardwaresetup {
    //Should we make all these protected final static objects?

    //why is this private while everything else is public?
    private final int NAVX_DIM_I2C_PORT = 0;

    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftRear;
    public DcMotor rightRear;

    public ColorSensor colorSensorBeacon;
    public ColorSensor colorSensorGroundLeft;
    public ColorSensor colorSensorGroundRight;

    public OpticalDistanceSensor proxSensor;

    public Telemetry telemetry;

    AHRS ahrs;

    //I don't think we need this
    public Hardwaresetup(){

    }


    //shouldn't this be the constructior?
    public void init(HardwareMap hardwaremap) {

        leftFront = hardwaremap.dcMotor.get("leftfront");
        rightFront = hardwaremap.dcMotor.get("rightfront");
        leftRear = hardwaremap.dcMotor.get("leftrear");
        rightRear = hardwaremap.dcMotor.get("rightrear");


        //I do not think we need this
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);



        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //IZ DIFZ NEEDZ CHECKZ
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //I think stop and reset encoders stops the motors intirely
        //PS if you look at MecanumTeleOp instead of using hardwaresetup init uses MoveMotors startEncoders


        //the defalut is to run without out, should we leave this in?
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ahrs = AHRS.getInstance(hardwaremap.deviceInterfaceModule.get("dim"), NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData);
        ahrs.zeroYaw();

        colorSensorBeacon = hardwaremap.colorSensor.get("colorSensorBeacon");
        colorSensorGroundLeft = hardwaremap.colorSensor.get("colorSensorGroundLeft");
        colorSensorGroundRight = hardwaremap.colorSensor.get("colorSensorGroundRight");

        proxSensor = hardwaremap.opticalDistanceSensor.get("proxSensor");
    }
}
