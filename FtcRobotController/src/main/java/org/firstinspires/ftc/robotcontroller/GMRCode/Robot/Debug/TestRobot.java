package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Payton on 2/5/2017
 */
@TeleOp(name="Test Robot", group="Robot Test")
public class TestRobot extends OpMode {

    private final int gyroPort = 0;

    private AHRS gyro;

    @Override
    public void init() {
        gyro = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"), gyroPort, AHRS.DeviceDataType.kProcessedData);
    }

    @Override
    public void loop() {
        telemetry.addData("Gyro", gyro.getYaw());
        telemetry.addData("Gyro Calibrating", gyro.isCalibrating());
        telemetry.addData("Gyro Connected", gyro.isConnected());
        telemetry.addData("Gyro Moving", gyro.isMoving());
        telemetry.addData("Gyro Rotating", gyro.isRotating());
    }
}
