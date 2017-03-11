package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.TestCode.Vuforia;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
@Autonomous(name="VuforiaTestRun", group="sensor tests")
//@Disabled
public class VuforiaTestRun extends LinearOpMode{
    //Vuforia v;  //dose nothing, I think it is like are hierarchy system but they have not made all the others subclasses of it
    VuforiaLocalizer vuforiaLocalizer;  //sets up things like image location, camera info and the view
    VuforiaLocalizer.Parameters vuforiaParameters; //sets up the camera direction and the license key
    VuforiaTrackables beacons;  //the main class for finding stuff
    public void runOpMode() throws InterruptedException {

        vuforiaParameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        vuforiaParameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaParameters.vuforiaLicenseKey = "AQVy9oH/////AAAAGehUO9FzLEfnt02jSN2/SZVPiYtmWEEfswg7kcmS2Bdb2PpepDN0Ah6lo3hTNv+z5b1CXSRLridjv2ZQLfN/mIE+cTxR1tJRqx87/ymlIlx3dpdhULKJir44ksGhz5NZpuiRU/CnBjsxnYEYkEFzqLd4kN9gd6LxeZDrvnzpArX3CNis9Do/nYGT/9r4J6kmI5iam32J/lBUUCqMIX7MQPjZGaLcTF0IeNHAM4yMe18S7Ud+bOkQa/T66ckt1oZwjS+uil9yWAfpYUsASpVsrIQOeyPe6NDhjMcJRgaV7gvHf8sscHO3b2OsobYcUsD5bq5o16utq+MV6SKNt8IkZcdNqpa2pznxewGh4sOHZYNe";
        vuforiaParameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(vuforiaParameters);

        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        beacons = vuforiaLocalizer.loadTrackablesFromAsset("FTC_2016_17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");
        waitForStart();
        while(true) {
            
            if (beacons.getName().equalsIgnoreCase("wheels")) {
                telemetry.addData("wheels", null);
            } else if (beacons.getName().equalsIgnoreCase("tools")) {
                telemetry.addData("tools", null);
            } else if (beacons.getName().equalsIgnoreCase("lego")) {
                telemetry.addData("lego", null);
            } else if (beacons.getName().equalsIgnoreCase("gears")) {
                telemetry.addData("gears", null);
            } else {
                telemetry.addData("cant see it", null);
            }
        }
    }
    public enum whatImage{WHEELS, TOOLS, LEGOS, GEARS, NULL}
}