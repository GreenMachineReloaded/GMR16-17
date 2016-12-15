package org.firstinspires.ftc.robotcontroller.GMRCode.Robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Payton on 12/15/2016
 */

public class Robot {

    DriveTrain driveTrain;
    Launch launch;
    BeaconNav beaconNav;

    public Robot(HardwareMap hwMap, Telemetry Telemetry) {
        driveTrain = new DriveTrain(hwMap, Telemetry);
        launch = new Launch(hwMap, Telemetry);
        beaconNav = new BeaconNav(hwMap, Telemetry);

    }

}
