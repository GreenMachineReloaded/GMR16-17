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
    public Robot(HardwareMap hardwareMap, Telemetry Telemetry) {new Robot(hardwareMap, Telemetry, false, false);}
    public Robot(HardwareMap hardwareMap, Telemetry Telemetry, boolean writeDebug) {new Robot(hardwareMap, Telemetry, writeDebug, false);}
    public Robot(HardwareMap hardwareMap, Telemetry Telemetry, boolean writeDebug, boolean writeToFile) {
        Telemetry.addData("Robot Starting", "");
        driveTrain = new DriveTrain(hardwareMap, Telemetry);
        launch = new Launch(hardwareMap, Telemetry);
        beaconNav = new BeaconNav(hardwareMap, Telemetry);
        waitFor = new WaitFor();
        if(writeDebug) {
            //start thread
        }
        if(writeToFile) {
            //start thread
        }
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
}
