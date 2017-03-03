package org.firstinspires.ftc.robotcontroller.GMRCode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.RobotEyes;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.WaitFor;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {

    public DriveTrain driveTrain;
    public Launch launch;
    public BeaconNav beaconNav;
    public RobotEyes robotEyes;

    public WaitFor waitFor;
    public GMRColorSensor colorSensor;

    public HardwareMap hwMap;
    public Telemetry telemtry;


    private int number = 0;

    private double VuforiaX;
    private double VuforiaZ;

    public Robot(HardwareMap hwMap, Telemetry telemtry) {
        colorSensor = new GMRColorSensor(hwMap, telemtry);
        telemtry.addData("Robot Startup", "Beginning");
        telemtry.update();
        robotEyes = new RobotEyes();
        try {
            this.driveTrain = new DriveTrain(hwMap, telemtry);
        } catch(NullPointerException e) {
            telemtry.addData("Drive Train Failed", "");
        }
        try {
            this.launch = new Launch(hwMap, telemtry);
        } catch(NullPointerException e) {
            telemtry.addData("Launch Failed", "");
        }
        try {
            this.beaconNav = new BeaconNav(hwMap, telemtry);
        } catch(NullPointerException e) {
            telemtry.addData("Beacon Nav Failed", "");
        }

        waitFor = new WaitFor();
        this.hwMap = hwMap;
        this.telemtry = telemtry;

//        if(writeDebug) {
//            //start thread
//        }
//        if(writeToFile) {
//            //start thread
//        }
        telemtry.update();
    }
<<<<<<< HEAD

//    public void startDebug(boolean debug, boolean debugFile) {
//        debugThread.whichDebug(debug, debugFile);
//        debugThread.start();
//    }
//    public void stopDebug() {debugThread.setOn(false);}

    public boolean colorDrive(DriveTrain.Direction direction, double power, GMRColorSensor.WhichGMRColorSensor whichGMRColorSensor , GMRColorSensor.Color whichColor) {

=======
    public boolean colorDrive(DriveTrain.Direction direction, double power, GMRColorSensor.WhichGMRColorSensor whichGMRColorSensor , GMRColorSensor.Color whichColor) {
>>>>>>> refs/remotes/origin/master
        this.driveTrain.Drive(direction, power);
        telemtry.addData("Current Blue", colorSensor.getColorValue(GMRColorSensor.Color.BLUE, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
        if (this.colorSensor.whichGreaterColor(whichGMRColorSensor, whichColor)) {
            this.driveTrain.stop();
            return true;
        } else {
            return false;
        }
    }
    public boolean whiteDrive(DriveTrain.Direction direction, double power, GMRColorSensor.WhichGMRColorSensor whichGMRColorSensor) {
        this.driveTrain.Drive(direction, power);
        if(this.colorSensor.isWhite(whichGMRColorSensor)) {driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean ProxDrive(DriveTrain.Direction direction, double power) {
        telemtry.addData("Current Distance", beaconNav.getDistance());
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.getDistance() > 400) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean ProxDrive(DriveTrain.Direction direction, double power, double prox) {
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.getDistance() >= prox) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean ProxRawDrive(DriveTrain.Direction direction, double power, double prox) {
        this.driveTrain.Drive(direction, power);
        if (this.beaconNav.getRawDistance() > prox) {
            this.driveTrain.stop();
            return true;
        } else {
            return false;
        }
    }

    public DriveTrain getDriveTrain() {
        if (this.driveTrain == null) {
            this.driveTrain = new DriveTrain(hwMap, telemtry);
        }
        return this.driveTrain;
    }

    public Launch getLaunch() {
        if (this.launch == null) {
            this.launch = new Launch(hwMap, telemtry);
        }
        return this.launch;
    }

    public BeaconNav getBeaconNav() {
        if (this.beaconNav == null) {
            this.beaconNav = new BeaconNav(hwMap, telemtry);
        }
        return this.beaconNav;
    }

    public void printObjects() {
        telemtry.addData("Drive Train", driveTrain);
        telemtry.addData("Launch", launch);
        telemtry.addData("Beacon Nav", beaconNav);
    }

    public int number() {
        return number += 1;
    }
    public void stopRobot(){
        driveTrain.stop();
        beaconNav.stopBeaconNav();
        launch.stopLaunch();
    }
    public void driveVuforia() {
        driveTrain.setMaxSpeed(.2);
        while((VuforiaX > 30 || VuforiaX < -30) && (VuforiaZ > 30 || VuforiaZ < -30)) {
            VuforiaX = robotEyes.getSpecificArrayXYZ(robotEyes.getImageOfCurrentVisualBeacon())[0];
            VuforiaZ = -robotEyes.getSpecificArrayXYZ(robotEyes.getImageOfCurrentVisualBeacon())[2];
            driveTrain.setMotorPower(VuforiaX, VuforiaZ, 0);
        }
        driveTrain.setMaxSpeed(1);
        driveTrain.stop();
    }
}
