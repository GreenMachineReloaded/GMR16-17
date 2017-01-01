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
        this.waitFor = new WaitFor();
        if(writeDebug || writeToFile) {
            //start thread here
        }
    }
    public boolean colorDrive(DriveTrain.Direction direction, double power, BeaconNav.WhichGMRColorSensor whichGMRColorSensor , BeaconNav.Color whichColor) {
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.isColor(whichGMRColorSensor, whichColor)) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean whiteDrive(DriveTrain.Direction direction, double power, BeaconNav.WhichGMRColorSensor whichGMRColorSensor , BeaconNav.Color whichColor) {
        this.driveTrain.Drive(direction, power);
        if(beaconNav.isWhite(whichGMRColorSensor)) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean ProxDrive(DriveTrain.Direction direction, double power) {
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.getDistance() > 400) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean ProxDrive(DriveTrain.Direction direction, double power, double prox) {
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.getDistance() > prox) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean ProxRawDrive(DriveTrain.Direction direction, double power, double prox) {
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.getRawDistance() > prox) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
}