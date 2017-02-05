package org.firstinspires.ftc.robotcontroller.GMRCode.Autonomous.Blue;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses.DriveTrain;
import org.firstinspires.ftc.robotcontroller.GMRCode.Robot.Robot;
import org.firstinspires.ftc.robotcontroller.SensorObjects.GMRColorSensor;
import org.firstinspires.ftc.robotcontroller.otherObjects.Continue;
import org.firstinspires.ftc.robotcontroller.otherObjects.CurrentStates;
@Autonomous(name="One Beacon Blue", group="Beacon Programs")
public class OneBeaconBlue extends OpMode {
    private Robot robot;
    private GMRColorSensor colorSensor;
    private CurrentStates state = CurrentStates.ENCODERFORWARD;
    private boolean isFinished = false;
    private boolean isStraight = false;
    private int launches = 0;
    private double startingOrientation;
    private Continue sleep = new Continue();
    private int launchNumber = 0;
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        colorSensor = new GMRColorSensor(hardwareMap, telemetry);
        telemetry.addData("Starting Robot", "");
        startingOrientation = robot.driveTrain.getYaw();
    }
    public void start() {telemetry.addData("Starting Servos", "");telemetry.update();}
    public void loop() {
        telemetry.addData("Program Start", "");
        if (state == CurrentStates.ENCODERFORWARD) {
            if (!isFinished)     {isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.FORWARD, 0.6, 9);robot.launch.launcherServoControl(false);}
            else                 {state = CurrentStates.STRAFELEFT;isFinished = false;}
        } else if (state == CurrentStates.LAUNCH) {
            //if (launches == 1 && isFinished) {
            //   robot.launch.launchControl(false);
                             state = CurrentStates.ENCODERBACKWARD;
            //} else if (!isFinished) {
            //   isFinished = robot.launch.launchControl(true);
            //   sleep.Sleep(1.5);
            //} else {
            // launches += 1;
                            isFinished = false;
            //}
        } else if (state == CurrentStates.ENCODERBACKWARD) {

            if (!isFinished)     {isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.5, 3);}
            else                 {state = CurrentStates.STRAFERIGHT;isFinished = false;}

        } else if (state == CurrentStates.STRAFERIGHT) {

            if (!isFinished)     {isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.STRAFERIGHT, 0.75, 18);}
            else if(!isStraight) {isStraight = robot.driveTrain.straighten(startingOrientation);}
            else                 {state = CurrentStates.COLORFORWARD; isFinished = false; isStraight = false;}

        } else if (state == CurrentStates.COLORFORWARD) {

            if (!isFinished)     {isFinished = robot.whiteDrive(DriveTrain.Direction.FORWARD, 0.1, GMRColorSensor.WhichGMRColorSensor.GROUNDLEFT);}
            else                 {state = CurrentStates.ENCODERBACKWARD2; isFinished = false;}
        
        } else if (state == CurrentStates.ENCODERBACKWARD2) {

            if (!isFinished)     {isFinished = robot.driveTrain.encoderDrive(DriveTrain.Direction.BACKWARD, 0.5, 0.2);}
            else                 {state = CurrentStates.TURNRIGHT; isFinished = false;}

        }else if(state == CurrentStates.TURNRIGHT) {isFinished = robot.driveTrain.gyroTurn(DriveTrain.Direction.TURNRIGHT, .1, 180);

            if(!isFinished)      {state = CurrentStates.STRAFELEFT2;isFinished = false;}

        }else if (state == CurrentStates.STRAFELEFT2) {

            if (!isFinished)     {isFinished = robot.ProxDrive(DriveTrain.Direction.STRAFELEFT, 0.6, .7);}
            else                 {state = CurrentStates.PUSHBEACON;isFinished = false;isStraight = false;}

        } else if(state == CurrentStates.PUSHBEACON) {

            if (!isFinished)     {isFinished = robot.beaconNav.pushRed();}
            else                 {state = CurrentStates.PROGRAMEND;isFinished = false;}

        } else if (state == CurrentStates.PROGRAMEND) {

            robot.driveTrain.stop();
            telemetry.addData("Program End", "");
        }
    }
}
