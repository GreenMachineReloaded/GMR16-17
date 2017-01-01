package org.firstinspires.ftc.robotcontroller.GMRCode.Robot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.WaitFor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {

    public DriveTrain driveTrain;
    public Launch launch;
    public BeaconNav beaconNav;
    public WaitFor waitFor;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {new Robot(hardwareMap, telemetry, false, false);}
    public Robot(HardwareMap hardwareMap, Telemetry telemetry, boolean writeDebug) {new Robot(hardwareMap, telemetry, writeDebug, false);}
    public Robot(HardwareMap hardwareMap, Telemetry telemetry, boolean writeDebug, boolean writeToFile) {
        this.driveTrain = new DriveTrain(hardwareMap, telemetry);
        this.launch = new Launch(hardwareMap, telemetry);
        this.beaconNav = new BeaconNav(hardwareMap, telemetry);
        waitFor = new WaitFor();
        if(writeDebug) {
            //start thread
        }
        if(writeToFile) {
            //start thread
        }
        telemetry.addData("Robot Starting", "");
        telemetry.addData("Drive Train", driveTrain);
        telemetry.addData("Launch", launch);
        telemetry.addData("Beacon Nav", beaconNav);
    }

    public boolean colorDrive(DriveTrain.Direction direction, double power, BeaconNav.WhichGMRColorSensor whichGMRColorSensor , BeaconNav.Color whichColor) {
        driveTrain.Drive(direction, power);
        if(beaconNav.isColor(whichGMRColorSensor, whichColor)) {driveTrain.stop(); return true;}
        else {return false;}
    }

    public boolean whiteDrive(DriveTrain.Direction direction, double power, BeaconNav.WhichGMRColorSensor whichGMRColorSensor) {
        driveTrain.Drive(direction, power);
        if(beaconNav.isWhite(whichGMRColorSensor)) {driveTrain.stop(); return true;}
        else {return false;}
    }

    public boolean ProxDrive(DriveTrain.Direction direction, double power) {
        driveTrain.Drive(direction, power);
        if(beaconNav.getDistance() > 400) {driveTrain.stop(); return true;}
        else {return false;}
    }

    public boolean ProxDrive(DriveTrain.Direction direction, double power, double prox) {
        driveTrain.Drive(direction, power);
        if(beaconNav.getDistance() > prox) {driveTrain.stop(); return true;}
        else {return false;}
    }

    public boolean ProxRawDrive(DriveTrain.Direction direction, double power, double prox) {
        driveTrain.Drive(direction, power);
        if(beaconNav.getRawDistance() > prox) {driveTrain.stop(); return true;}
        else {return false;}
    }

    public DriveTrain getDriveTrain() {
        return driveTrain;
    }

    public Launch getLaunch() {
        return launch;
    }

    public BeaconNav getBeaconNav() {
        return beaconNav;
    }
}
