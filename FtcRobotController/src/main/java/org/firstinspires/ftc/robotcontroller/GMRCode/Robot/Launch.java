package org.firstinspires.ftc.robotcontroller.GMRCode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 12/15/2016
 */
@SuppressWarnings("FieldCanBeLocal")

public class Launch {

    private DcMotor sweeperMotor;
    private DcMotor launchMotor;

    private Servo hopperDoorServo;

    public Launch(HardwareMap hwMap, Telemetry Telemetry) {

        sweeperMotor = hwMap.dcMotor.get("sweepermotor");
        launchMotor = hwMap.dcMotor.get("launchmotor");

        hopperDoorServo = hwMap.servo.get("hopperdoorservo");

        sweeperMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

}
