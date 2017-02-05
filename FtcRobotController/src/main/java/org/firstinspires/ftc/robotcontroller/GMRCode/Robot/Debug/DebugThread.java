package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;

public class DebugThread extends Thread{
    GMRColorSensor colorSensor;
    BeaconNav beaconNav;
    DriveTrain driveTrain;
    Launch launch;
    boolean debug;
    boolean writeDebugFile;
    Telemetry telemetry;
    public DebugThread(HardwareMap hardware, Telemetry telemetry, BeaconNav beaconNav, DriveTrain driveTrain, Launch launch, boolean debug, boolean writeDebugFile) {
        this.colorSensor = new GMRColorSensor(hardware, telemetry);
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
            telemetry.addData("RED GL", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE GL", colorSensor.getColorValue(GMRColorSensor.Color.BLUE, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREEN GL", colorSensor.getColorValue(GMRColorSensor.Color.GREEN, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));

            telemetry.addData("RED B", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.BEACON));
            telemetry.addData("BLUE B", colorSensor.getColorValue(GMRColorSensor.Color.BLUE, GMRColorSensor.WhichGMRColorSensor.BEACON));
            telemetry.addData("GREEN B", colorSensor.getColorValue(GMRColorSensor.Color.GREEN, GMRColorSensor.WhichGMRColorSensor.BEACON));

            telemetry.addData("WHITE GL", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("WHITE B", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.BEACON));
        }
        if(writeDebugFile) {

        }
    }
}
