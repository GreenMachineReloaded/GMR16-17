package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Debug;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.BeaconNav;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.Launch;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.WaitFor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class DebugThread extends Thread{
    BeaconNav beaconNav;
    DriveTrain driveTrain;
    Launch launch;
    Telemetry telemetry;
    WaitFor waitFor;

    boolean debug;
    boolean fileDebug;
    boolean ON;
    BufferedWriter bufferedWriter;

    public DebugThread(Telemetry telemetry, DriveTrain driveTrain, Launch launch, BeaconNav beaconNav, WaitFor waitFor) {
        this.beaconNav = beaconNav;
        this.driveTrain = driveTrain;
        this.launch = launch;
        this.telemetry = telemetry;
        this.waitFor = waitFor;
    }
    public void start(String fileName, boolean debug, boolean fileDebug) {
        int fileNumber = 0;
        this.debug = debug;
        this.fileDebug = fileDebug;
        if(fileDebug) {
            File file = new File("PATH"+fileName+""+fileNumber);
            while(file.exists()) {
                fileNumber++;
                file = new File("PATH"+fileName+""+fileNumber);
            }
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
            } catch (IOException e) {
                telemetry.addData("filewriter crashed", null);
                e.printStackTrace();
            }
        }
        ON = true;
        this.run();
    }
    public void run() {
        while(ON) {
            if(debug) {
                telemetry.addData("drive", beaconNav.getDebugCommand());
                telemetry.addData("launch", beaconNav.getDebugCommand());
                telemetry.addData("beacon", beaconNav.getDebugCommand());
                telemetry.update();
            }
            if(fileDebug) {
                try {
                    bufferedWriter.write("[DriveTrain]...: \n"+beaconNav.getDebugCommand());
                    bufferedWriter.write("[Launch].......: \n"+beaconNav.getDebugCommand());
                    bufferedWriter.write("[BeaconNav]....: \n"+beaconNav.getDebugCommand());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(debug) {
            telemetry.clear();
            telemetry.addData("drive", beaconNav.getAllDebugCommands());
            telemetry.addData("launch", beaconNav.getAllDebugCommands());
            telemetry.addData("beacon", beaconNav.getAllDebugCommands());
            telemetry.update();
        }
    }
    public void stopThread() {
        ON = false;
    }
}