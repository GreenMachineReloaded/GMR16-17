package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;
import com.qualcomm.ftcrobotcontroller.R;
import com.vuforia.HINT;
import com.vuforia.Image;
import com.vuforia.Vuforia;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
public class RobotEyes {
    private VuforiaLocalizer.Parameters parameters;
    private VuforiaLocalizer localizer;
    private VuforiaTrackables trackables;

    private VuforiaTrackable wheels;
    private VuforiaTrackable tools;
    private VuforiaTrackable legos;
    private VuforiaTrackable gears;

    private String whatCurrentBeacon;

    private OpenGLMatrix MatrixWheels;
    private OpenGLMatrix MatrixTools;
    private OpenGLMatrix MatrixLegos;
    private OpenGLMatrix MatrixGears;

    private float[] currentXYZ;


    public RobotEyes() {
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        parameters.vuforiaLicenseKey = "AQVy9oH/////AAAAGehUO9FzLEfnt02jSN2/SZVPiYtmWEEfswg7kcmS2Bdb2PpepDN0Ah6lo3hTNv+z5b1CXSRLridjv2ZQLfN/mIE+cTxR1tJRqx87/ymlIlx3dpdhULKJir44ksGhz5NZpuiRU/CnBjsxnYEYkEFzqLd4kN9gd6LxeZDrvnzpArX3CNis9Do/nYGT/9r4J6kmI5iam32J/lBUUCqMIX7MQPjZGaLcTF0IeNHAM4yMe18S7Ud+bOkQa/T66ckt1oZwjS+uil9yWAfpYUsASpVsrIQOeyPe6NDhjMcJRgaV7gvHf8sscHO3b2OsobYcUsD5bq5o16utq+MV6SKNt8IkZcdNqpa2pznxewGh4sOHZYNe";
        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        localizer = ClassFactory.createVuforiaLocalizer(parameters);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 1);

        trackables = localizer.loadTrackablesFromAsset("FTC_2016-17");
        trackables.get(0).setName("Wheels");
        trackables.get(1).setName("Tools");
        trackables.get(2).setName("Legos");
        trackables.get(3).setName("Gears");
        trackables.activate();

        wheels = trackables.get(0);
        tools  = trackables.get(1);
        legos  = trackables.get(2);
        gears  = trackables.get(3);

        currentXYZ = new float[3];
        for(int I = 0; I < 3; I++) {currentXYZ[I] = 0;}
    }

    public float[] getArrayXYZ() {
             if (getSpecificArrayXYZ(Images.WHEELS)[0] != 0 && getSpecificArrayXYZ(Images.WHEELS)[1] != 0 && getSpecificArrayXYZ(Images.WHEELS)[2] != 0) {return getSpecificArrayXYZ(Images.WHEELS);}
        else if ( getSpecificArrayXYZ(Images.TOOLS)[0] != 0 &&  getSpecificArrayXYZ(Images.TOOLS)[1] != 0 &&  getSpecificArrayXYZ(Images.TOOLS)[2] != 0) { return getSpecificArrayXYZ(Images.TOOLS);}
        else if ( getSpecificArrayXYZ(Images.LEGOS)[0] != 0 &&  getSpecificArrayXYZ(Images.LEGOS)[1] != 0 &&  getSpecificArrayXYZ(Images.LEGOS)[2] != 0) { return getSpecificArrayXYZ(Images.LEGOS);}
        else if ( getSpecificArrayXYZ(Images.GEARS)[0] != 0 &&  getSpecificArrayXYZ(Images.GEARS)[1] != 0 &&  getSpecificArrayXYZ(Images.GEARS)[2] != 0) { return getSpecificArrayXYZ(Images.GEARS);}
        else {currentXYZ[0] = 0; currentXYZ[1] = 0; currentXYZ[2] = 0;}
        float[] returnOfCurrentXYZ = {currentXYZ[0], currentXYZ[1], currentXYZ[2]};
        return returnOfCurrentXYZ;
    }
    public Images getImageOfCurrentVisualBeacon() {
             if (getSpecificArrayXYZ(Images.WHEELS)[0] != 0 && getSpecificArrayXYZ(Images.WHEELS)[1] != 0 && getSpecificArrayXYZ(Images.WHEELS)[2] != 0) {return Images.WHEELS;}
        else if ( getSpecificArrayXYZ(Images.TOOLS)[0] != 0 &&  getSpecificArrayXYZ(Images.TOOLS)[1] != 0 &&  getSpecificArrayXYZ(Images.TOOLS)[2] != 0) { return Images.TOOLS;}
        else if ( getSpecificArrayXYZ(Images.LEGOS)[0] != 0 &&  getSpecificArrayXYZ(Images.LEGOS)[1] != 0 &&  getSpecificArrayXYZ(Images.LEGOS)[2] != 0) { return Images.LEGOS;}
        else if ( getSpecificArrayXYZ(Images.GEARS)[0] != 0 &&  getSpecificArrayXYZ(Images.GEARS)[1] != 0 &&  getSpecificArrayXYZ(Images.GEARS)[2] != 0) { return Images.GEARS;}
        else                                                                                                                                             { return Images.NULL; }
    }
    public float[] getSpecificArrayXYZ(Images which) {
        if(which == Images.WHEELS) {
            MatrixWheels = ((VuforiaTrackableDefaultListener) wheels.getListener()).getPose();
            if (MatrixWheels != null) {currentXYZ = MatrixWheels.getTranslation().getData();}
            else {currentXYZ[0] = 0; currentXYZ[1] = 0; currentXYZ[2] = 0;}
        }
        else if(which == Images.TOOLS) {
            MatrixTools = ((VuforiaTrackableDefaultListener) tools.getListener()).getPose();
            if (MatrixTools != null) {currentXYZ = MatrixTools.getTranslation().getData();}
            else {currentXYZ[0] = 0; currentXYZ[1] = 0; currentXYZ[2] = 0;}
        }
        else if(which == Images.LEGOS) {
            MatrixLegos = ((VuforiaTrackableDefaultListener) legos.getListener()).getPose();
            if (MatrixLegos != null) {currentXYZ = MatrixLegos.getTranslation().getData();}
            else {currentXYZ[0] = 0; currentXYZ[1] = 0; currentXYZ[2] = 0;}
        }
        else if(which == Images.GEARS) {
            MatrixGears = ((VuforiaTrackableDefaultListener) gears.getListener()).getPose();
            if (MatrixGears != null) {currentXYZ = MatrixGears.getTranslation().getData();}
            else {currentXYZ[0] = 0; currentXYZ[1] = 0; currentXYZ[2] = 0;}
        }
        else {currentXYZ[0] = 0; currentXYZ[1] = 0; currentXYZ[2] = 0;}
        float[] returnOfCurrentXYZ = {currentXYZ[0], currentXYZ[1], currentXYZ[2]};
        return returnOfCurrentXYZ;
    }
    public void activateOn(boolean onOff) {
        if(onOff) {  trackables.activate();}
        else      {trackables.deactivate();}
    }
    public enum Images {WHEELS, TOOLS, LEGOS, GEARS, NULL}
}
