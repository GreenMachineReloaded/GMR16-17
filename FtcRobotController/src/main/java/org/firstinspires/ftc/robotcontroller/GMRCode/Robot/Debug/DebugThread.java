package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;
import android.os.Environment;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
<<<<<<< HEAD
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.WaitFor;
=======
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
>>>>>>> refs/remotes/origin/m-Automaintenence
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DebugThread extends Thread{
    GMRColorSensor colorSensor;
    BeaconNav beaconNav;
    DriveTrain driveTrain;
    Launch launch;
    Telemetry telemetry;
<<<<<<< HEAD
    WaitFor waitFor;
    boolean debug;
    boolean fileDebug;
    boolean ON;
    double secondInterval;
    BufferedWriter bufferedWriter;
    String allDebugCommands;
    public DebugThread(Telemetry telemetry, DriveTrain driveTrain, Launch launch, BeaconNav beaconNav, WaitFor waitFor, String fileName, boolean debug, boolean fileDebug, double secondInterval) {
        this.beaconNav = beaconNav;
=======
    public DebugThread(HardwareMap hardware, Telemetry telemetry, BeaconNav beaconNav, DriveTrain driveTrain, Launch launch, boolean debug, boolean writeDebugFile) {
        this.colorSensor = new GMRColorSensor(hardware, telemetry);
>>>>>>> refs/remotes/origin/m-Automaintenence
        this.driveTrain = driveTrain;
        this.launch = launch;
        this.telemetry = telemetry;
        this.waitFor = waitFor;
        int fileNumber = 0;
        this.debug = debug;
        this.fileDebug = fileDebug;
        this.secondInterval = secondInterval;
        this.allDebugCommands = "";
        ON = true;
        if(fileDebug) {
            File file = new File(Environment.getExternalStorageDirectory(), ""+fileName+fileNumber);
            while(file.exists()) {fileNumber++; file = new File(Environment.getExternalStorageDirectory(), ""+fileName+fileNumber);}
            try {bufferedWriter = new BufferedWriter(new FileWriter(file));}
            catch (IOException e) {
                telemetry.addData("filewriter crashed", null);
                e.printStackTrace();
            }
        }
        this.run();
    }
    public void run() {
<<<<<<< HEAD
        int debugThreadLoupCount = 0;
        while(ON || debugThreadLoupCount <= 10000) {
            debugThreadLoupCount++;
            if(debug) {
                telemetry.addData("drive",  driveTrain.getDebugCommand());
                this.allDebugCommands = (   beaconNav.getDebugCommand());
                telemetry.addData("launch", launch.getDebugCommand());
                this.allDebugCommands = (   launch.getDebugCommand());
                telemetry.addData("beacon", beaconNav.getDebugCommand());
                this.allDebugCommands = (   beaconNav.getDebugCommand());
                telemetry.update();
            }
            if(fileDebug) {
                try {
                    bufferedWriter.write("[DriveTrain].....:"+driveTrain.getDebugCommand() +"\n");
                    bufferedWriter.write("[Launch].........:"+launch.getDebugCommand()     +"\n");
                    bufferedWriter.write("[BeaconNav]......:"+beaconNav.getDebugCommand()  +"\n");
                } catch (IOException e) {e.printStackTrace();}
            }
            waitFor.Sleep(secondInterval);
=======
        if(debug) {
            telemetry.addData("RED GL", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("BLUE GL", colorSensor.getColorValue(GMRColorSensor.Color.BLUE, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("GREEN GL", colorSensor.getColorValue(GMRColorSensor.Color.GREEN, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));

            telemetry.addData("RED B", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.BEACON));
            telemetry.addData("BLUE B", colorSensor.getColorValue(GMRColorSensor.Color.BLUE, GMRColorSensor.WhichGMRColorSensor.BEACON));
            telemetry.addData("GREEN B", colorSensor.getColorValue(GMRColorSensor.Color.GREEN, GMRColorSensor.WhichGMRColorSensor.BEACON));

            telemetry.addData("WHITE GL", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT));
            telemetry.addData("WHITE B", colorSensor.getColorValue(GMRColorSensor.Color.RED, GMRColorSensor.WhichGMRColorSensor.BEACON));
>>>>>>> refs/remotes/origin/m-Automaintenence
        }
        if(debug) {
            telemetry.clear();
            telemetry.addData(this.allDebugCommands, null);
            telemetry.update();
        }
    }
    public void stopThread() {ON = false;}
}