package org.firstinspires.ftc.robotcontroller.ProxSensorsTestCode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcontroller.SensorObjects.Continue;
import org.firstinspires.ftc.robotcontroller.GMRDriveCode.Hardwaresetup;
import org.firstinspires.ftc.robotcontroller.SensorObjects.ProxSensors;

@Autonomous(name="Prox Light", group ="Tests")
@Disabled
public class TestProxSensorsLight extends LinearOpMode{
    Continue c;
    Hardwaresetup hardwaresetup;
    ProxSensors proxSensors;
    @Override
    public void runOpMode() throws InterruptedException {
        c = new Continue();
        hardwaresetup = new Hardwaresetup();
        hardwaresetup.init(hardwareMap);
        proxSensors = new ProxSensors(hardwaresetup.proxSensor);
        proxSensors.turnOnLight(false);
        waitForStart();
        while(true) {
            telemetry.addData("raw light", proxSensors.rawLight() * 8);
            telemetry.update();
            c.Sleep(100);
        }
    }
}
