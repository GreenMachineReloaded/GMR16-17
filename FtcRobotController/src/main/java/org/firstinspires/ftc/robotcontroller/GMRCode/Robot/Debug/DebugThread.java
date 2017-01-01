package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;

public class DebugThread extends Thread{
    BeaconNav beaconNav;
    DriveTrain driveTrain;
    Launch launch;
    boolean debug;
    boolean writeDebugFile;
    Telemetry telemetry;
    public DebugThread(Telemetry telemetry, BeaconNav beaconNav, DriveTrain driveTrain, Launch launch, boolean debug, boolean writeDebugFile) {
        this.beaconNav = beaconNav;
        this.driveTrain = driveTrain;
        this.launch = launch;
        this.debug = debug;
        this.writeDebugFile = writeDebugFile;
        this.telemetry = telemetry;
        if(writeDebugFile) {
            File file = new File("Debug0");
            while(file.exists()) {

            }
        }
    }
    public void run() {
        if(debug) {
            telemetry.addData("RED GL", beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE GL", beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREEN GL", beaconNav.getColorValue(BeaconNav.Color.GREEN, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));

            telemetry.addData("RED B", beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.BEACON));
            telemetry.addData("BLUE B", beaconNav.getColorValue(BeaconNav.Color.BLUE, BeaconNav.WhichGMRColorSensor.BEACON));
            telemetry.addData("GREEN B", beaconNav.getColorValue(BeaconNav.Color.GREEN, BeaconNav.WhichGMRColorSensor.BEACON));

            telemetry.addData("WHITE GL", beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("WHITE B", beaconNav.getColorValue(BeaconNav.Color.RED, BeaconNav.WhichGMRColorSensor.BEACON));
        }
        if(writeDebugFile) {

        }
    }
}
