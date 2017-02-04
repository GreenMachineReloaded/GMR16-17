package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;
import com.qualcomm.ftcrobotcontroller.R;
import com.vuforia.HINT;
import com.vuforia.Vuforia;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
public class RobotEyes{
    //Vuforia v;  //dose nothing, I think it is like are hierarchy system but they have not made all the others subclasses of it
    VuforiaLocalizer vuforiaLocalizer;  //sets up things like image location, camera info and the view
        VuforiaLocalizer.Parameters vuforiaParameters; //sets up the camera direction and the license key
    VuforiaTrackables beacons;  //the main class for finding stuff
    public RobotEyes() {
        vuforiaParameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        vuforiaParameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaParameters.vuforiaLicenseKey = "";
        vuforiaParameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(vuforiaParameters);
        
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        beacons = vuforiaLocalizer.loadTrackablesFromAsset("FTC_2016_17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");
    }
    public whatImage infrontBeacon() {
             if(beacons.getName().equalsIgnoreCase("wheels")) {return whatImage.WHEELS;}
        else if(beacons.getName().equalsIgnoreCase("tools") ) {return whatImage.TOOLS; }
        else if(beacons.getName().equalsIgnoreCase("lego")  ) {return whatImage.LEGOS; }
        else if(beacons.getName().equalsIgnoreCase("gears") ) {return whatImage.GEARS; }
        else                                                  {return whatImage.NULL;  }
    }
    public enum whatImage{WHEELS, TOOLS, LEGOS, GEARS, NULL}
}