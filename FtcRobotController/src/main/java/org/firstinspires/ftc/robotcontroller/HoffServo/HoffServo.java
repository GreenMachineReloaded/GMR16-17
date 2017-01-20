package org.firstinspires.ftc.robotcontroller.HoffServo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Payton on 1/1/2017
 */
@TeleOp(name="Hoff Test", group="Hoff")
public class HoffServo extends OpMode {

    private Servo hoff;

    private double hoffPosition;

    @Override
    public void init() {
        hoffPosition = 0.5;
        hoff = hardwareMap.servo.get("beaconServo");
    }

    @Override
    public void start() {
        hoff.setPosition(hoffPosition);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            hoffPosition = .6;
        } else if (gamepad1.b) {
            hoffPosition = .4;
        }
        hoff.setPosition(hoffPosition);
    }
}
