package org.firstinspires.ftc.robotcontroller.GMRCode.Robot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.WaitFor;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug.DebugThread;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public DriveTrain driveTrain;
    public Launch launch;
    public BeaconNav beaconNav;
    public WaitFor waitFor;
//    DebugThread debugThread;
    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.driveTrain = new DriveTrain(hardwareMap, telemetry);
        this.launch = new Launch(hardwareMap, telemetry);
        this.beaconNav = new BeaconNav(hardwareMap, telemetry);
        this.waitFor = new WaitFor();
//        debugThread = new DebugThread(telemetry, driveTrain, launch, beaconNav);
    }
//    public void startDebug(boolean debug, boolean debugFile) {
//        debugThread.whichDebug(debug, debugFile);
//        debugThread.start();
//    }
//    public void stopDebug() {debugThread.setOn(false);}
    public boolean colorDrive(DriveTrain.Direction direction, double power, BeaconNav.WhichGMRColorSensor whichGMRColorSensor , BeaconNav.Color whichColor) {
        this.driveTrain.Drive(direction, power);
        if(this.beaconNav.isColor(whichGMRColorSensor, whichColor)) {this.driveTrain.stop(); return true;}
        else {return false;}
    }
    public boolean whiteDrive(DriveTrain.Direction direction, double power, BeaconNav.WhichGMRColorSensor whichGMRColorSensor) {
        this.driveTrain.Drive(direction, power);
        if(beaconNav.isWhite(whichGMRColorSensor)) {driveTrain.stop(); return true;}
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
