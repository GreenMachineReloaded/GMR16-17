package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;
import com.qualcomm.ftcrobotcontroller.R;
import com.vuforia.HINT;
import com.vuforia.Vuforia;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
public class RobotEyes {
    VuforiaLocalizer.Parameters parameters;
    VuforiaLocalizer localizer;
    VuforiaTrackables trackables;

    VuforiaTrackable wheels;
    VuforiaTrackable tools;
    VuforiaTrackable legos;
    VuforiaTrackable gears;

    String whatCurrentBeacon;

    OpenGLMatrix theMatrix;

    float[] currentXYZ;
    public RobotEyes() {
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        parameters.vuforiaLicenseKey = "AQVy9oH/////AAAAGehUO9FzLEfnt02jSN2/SZVPiYtmWEEfswg7kcmS2Bdb2PpepDN0Ah6lo3hTNv+z5b1CXSRLridjv2ZQLfN/mIE+cTxR1tJRqx87/ymlIlx3dpdhULKJir44ksGhz5NZpuiRU/CnBjsxnYEYkEFzqLd4kN9gd6LxeZDrvnzpArX3CNis9Do/nYGT/9r4J6kmI5iam32J/lBUUCqMIX7MQPjZGaLcTF0IeNHAM4yMe18S7Ud+bOkQa/T66ckt1oZwjS+uil9yWAfpYUsASpVsrIQOeyPe6NDhjMcJRgaV7gvHf8sscHO3b2OsobYcUsD5bq5o16utq+MV6SKNt8IkZcdNqpa2pznxewGh4sOHZYNe";
        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        localizer = ClassFactory.createVuforiaLocalizer(parameters);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        trackables = localizer.loadTrackablesFromAsset("FTC_2016-17");
        trackables.get(0).setName("Wheels");
        trackables.get(1).setName("Tools");
        trackables.get(2).setName("Legos");
        trackables.get(3).setName("Gears");

        trackables.activate();

        wheels = trackables.get(0);
        tools = trackables.get(1);
        legos = trackables.get(2);
        gears = trackables.get(3);

        currentXYZ[0] = 0;
        currentXYZ[1] = 0;
        currentXYZ[2] = 0;
    }
//    public images imageInFrontCamera() {
//        if() {
//
//        }
//        if(trackablesName.equalsIgnoreCase("Wheels"))     {return  images.WHEELS;}
//        else if(trackablesName.equalsIgnoreCase("Tools")) {return  images.TOOLS; }
//        else if(trackablesName.equalsIgnoreCase("Legos")) {return  images.LEGOS; }
//        else if(trackablesName.equalsIgnoreCase("Gears")) {return  images.GEARS; }
//        else                                              {return  images.NULL;  }
//    }
    public float[] getArrayXYZ() {
        theMatrix = ((VuforiaTrackableDefaultListener) wheels.getListener()).getPose();
        if(theMatrix != null) {
            currentXYZ = theMatrix.getTranslation().getData();
        }
        else {
            currentXYZ[0] = 0;
            currentXYZ[1] = 0;
            currentXYZ[2] = 0;
        }
        return currentXYZ;
    }
    public enum images {WHEELS, TOOLS, LEGOS, GEARS, NULL}
}
