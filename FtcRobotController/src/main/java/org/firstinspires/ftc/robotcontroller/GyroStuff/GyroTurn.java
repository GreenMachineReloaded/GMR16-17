package org.firstinspires.ftc.robotcontroller.GyroStuff;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Directions;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.MoveMotors;
public class GyroTurn {
    private AHRS gyro;
    private MoveMotors move;
    public GyroTurn(AHRS gyroArg, MoveMotors moveArg) {
        gyro = gyroArg;
        move = moveArg;
    }
    public void turnRight(int degree) {
        gyro.zeroYaw();
        if (degree > 179 || degree < 0) {
            throw new IndexOutOfBoundsException("Degrees must be between 179 and 0.");
        }
        while(gyro.getYaw() < degree) {
            move.Drive(Directions.TurnRight, 50);
        }
        move.Stop();
    }
    public void turnLeft(int degree) {
        gyro.zeroYaw();
        degree = -degree;
        if (degree < -179 || degree > 0) {
            throw new IndexOutOfBoundsException("Degrees must be between 179 and 0.");
        }
        while (gyro.getYaw() > degree) {
            move.Drive(Directions.TurnLeft, 50);
        }
        move.Stop();
    }
}




