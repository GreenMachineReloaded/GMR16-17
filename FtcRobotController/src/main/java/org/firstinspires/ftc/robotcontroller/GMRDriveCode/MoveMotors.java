package org.firstinspires.ftc.robotcontroller.GMRDriveCode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ColorSensors;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.SensorObjects.LightSensors;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 10/9/2016
 */
public class MoveMotors {
    //should we remove this after we finish the moveMotors object?
    Telemetry telemetry;

    Hardwaresetup robot = new Hardwaresetup();

    ColorSensors colorSensorsBeacon;
    ColorSensors colorSensorsGroundLeft;
    ColorSensors colorSensorsGroundRight;
    ProxSensors proxSensors;

    Continue c = new Continue();

    ColorSensors.whichColor whichColor;

    float goalDegrees = -1;



    //Why are we using init? shouldn't this all be in the constructior?
    public void init(HardwareMap hwMap, Telemetry telemetry){
        robot.init(hwMap);

        colorSensorsBeacon = new ColorSensors(robot.colorSensorBeacon);
        colorSensorsGroundLeft = new ColorSensors(robot.colorSensorGroundLeft);
        colorSensorsGroundRight = new ColorSensors(robot.colorSensorGroundRight);
        proxSensors = new ProxSensors(robot.proxSensor);
        this.telemetry = telemetry;
    }





    //instead of storing the varibul though LFpower, why not just stick the function inside set power?
    //PS by calling setMotorPower you are creating LFpower RFpower LRpower RRpower multipul timez.
    public void setMotorPower(double x, double y, double z){

        /*
        Guide to motor Powers:
        Left Front: - (y + x + Z)
        Right Front: y - x - z
        Left Rear: y + x - z
        Right Rear: - (y - x + z)
         */

        double LFpower = Range.clip(-(y+x+z),-1,1);
        double RFpower = Range.clip((y-x-z),-1,1);
        double LRpower = Range.clip(-(y-x+z),-1,1);
        double RRpower = Range.clip((y + x - z), -1, 1);

        robot.leftFront.setPower(LFpower);
        robot.rightFront.setPower(RFpower);
        robot.leftRear.setPower(LRpower);
        robot.rightRear.setPower(RRpower);

    }



    //should the gyro have its own object that move motors creates? aka Gyros gyros = new Gyros(gyro);
    public double currentDegrees(double x,double y) {

        //double magnitude = (Math.sqrt((x*x)+(-y*-y)));
        //return (Math.asin(x/magnitude)/0.0175);

        double currentRadians = Math.atan2(y, x);
        double degrees = currentRadians * (180/Math.PI);
        if (degrees<0) {
            degrees = 360 + degrees;
        }
        return degrees;

    }




    public void Drive(Directions direction, double power){

        double leftInput = -power;
        double rightInput = power;
        telemetry.addData("power: ", power);
        telemetry.update();
        switch (direction) {
            case Forward:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(rightInput);
                break;
            case Backward:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(-rightInput);
                break;
            case StrafeLeft:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(-rightInput);
                break;
            case StrafeRight:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(rightInput);
                break;
            case DLeftUp:
                robot.leftFront.setPower(0);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(0);
                break;
            case DLeftDown:
                robot.leftFront.setPower(0);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(0);
                break;
            case DRightUp:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightRear.setPower(rightInput);
                break;
            case DRightDown:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(0);
                robot.leftRear.setPower(0);
                robot.rightRear.setPower(-rightInput);
                break;
            case TurnLeft:
                robot.leftFront.setPower(-leftInput);
                robot.rightFront.setPower(rightInput);
                robot.leftRear.setPower(-leftInput);
                robot.rightRear.setPower(rightInput);
                break;
            case TurnRight:
                robot.leftFront.setPower(leftInput);
                robot.rightFront.setPower(-rightInput);
                robot.leftRear.setPower(leftInput);
                robot.rightRear.setPower(-rightInput);
                break;
        }

    }



    //should the gyro have its own object that move motors creates? aka Gyros gyros = new Gyros(gyro);
    //PS why dose this return a true or false?
    public boolean gyroTurn(Directions direction, double power, float degrees, Telemetry telemetry){

        switch(direction) {
            case TurnLeft:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() - degrees);
                    if (goalDegrees < 0) {
                        goalDegrees = (goalDegrees + 360);
                    }
                }
                if (!(getYaw() > goalDegrees - 1 && getYaw() < goalDegrees + 1)) {
                    Drive(direction, power);
                    telemetry.addData("Goal degrees: ", goalDegrees);
                } else {
                    Stop();
                    goalDegrees = -1;
                    return false;
                }
                break;
            case TurnRight:
                if (goalDegrees == -1) {
                    goalDegrees = (getYaw() + degrees);
                    if (goalDegrees > 360) {
                        goalDegrees = (goalDegrees - 360);
                    }
                }
                if (!(getYaw() > goalDegrees - 1 && getYaw() < goalDegrees + 1)) {
                    Drive(direction, power);
                } else {
                    Stop();
                    goalDegrees = -1;
                    return false;
                }
                break;
        }
        return false;

    }




    //what is this? my understanding: it is the drive function that uses incoders.
    public void encoderDriveC(Directions direction, double power) {

        double leftInput = -power;
        double rightInput = power;

        switch(direction) {

            case Forward:
                break;
            case Backward:
                break;
            case StrafeLeft:
                break;
            case StrafeRight:
                break;
            case DRightUp:
                break;
            case DRightDown:
                break;
            case DLeftUp:
                break;
            case DLeftDown:
                break;
            case TurnLeft:
                break;
            case TurnRight:
                break;
        }
    }





    //Why is this here?
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void startEncoders(){
        robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //PS it is used in MecanumTeleOp



    //should the encoders be there own object?
    public int getLeftEncoder() {
        return -robot.leftFront.getCurrentPosition();
    }

    public int getRightEncoder() {
        return robot.rightFront.getCurrentPosition();
    }


    //should the gyro have its own object that move motors creates? aka Gyros gyros = new Gyros(gyro);
    public float getYaw(){
        if(robot.ahrs.getYaw() < 0) {
            return (360 + robot.ahrs.getYaw());
        } else {
            return robot.ahrs.getYaw();
        }
    }




    //should Stop be lowercased?
    public void Stop(){
        robot.leftFront.setPower(0);
        robot.leftRear.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightRear.setPower(0);
    }


        //should we remove this?


//    public void lightDrive(Directions direction, double power) {
//        Drive(direction, power);
//        while(lightSensors.WhichColor() != ColorSensors.whichColor.WHITE) {
//            c.Sleep(10);
//        }
//        Stop();
//    }




    //should we combine color white drive with color drive red blue?
    public void colorWhiteDrive(Directions direction, double power, ColorSensors.whichColorSensor which) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            while(colorSensorsBeacon.isWhite() != ColorSensors.whichColor.WHITE) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT){
            while(colorSensorsGroundLeft.isWhite() != ColorSensors.whichColor.WHITE) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            while(colorSensorsGroundRight.isWhite() != ColorSensors.whichColor.WHITE) {
                c.Sleep(10);
            }
        }
        else {
            telemetry.addData("ERROR NO ENUM WHICH COLOR SENSOR", null);
            telemetry.update();
        }
        Stop();
    }

    public void colorDriveRedBlue(Directions direction, double power, ColorSensors.whichColorSensor which, ColorSensors.whichColor whichColor) {
        Drive(direction, power);
        if(which == ColorSensors.whichColorSensor.BEACON) {
            while (colorSensorsBeacon.greaterColor() == whichColor) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDLEFT) {
            while (colorSensorsGroundLeft.greaterColor() == whichColor) {
                c.Sleep(10);
            }
        }
        else if(which == ColorSensors.whichColorSensor.GROUNDRIGHT) {
            while (colorSensorsGroundRight.greaterColor() == whichColor) {
                c.Sleep(10);
            }
        }
        else {
            telemetry.addData("ERROR NO ENUM WHICH COLOR SENSOR", null);
            telemetry.update();
        }
        Stop();
    }




    //should we remove this?
    public void ProxDrive(Directions direction, double power) {
        Drive(direction, power);
        while(proxSensors.getDistance() < .5) {
            c.Sleep(10);
        }
        Stop();
    }
    public void ProxDrive(Directions direction, double power, double prox) {
        Drive(direction, power);
        while(proxSensors.getDistance() < prox) {
            c.Sleep(10);
        }
        Stop();
    }
}
