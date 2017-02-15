package org.firstinspires.ftc.robotcontroller.GMRCode.Robot.BaseClasses;
import static java.lang.Thread.sleep;
public class WaitFor {
    public void Sleep(int StopTime) {
        if(StopTime < 0) {throw new IndexOutOfBoundsException("time can not be less then 0");}
        Sleep((long) StopTime);
    }
    public void Sleep(float StopTime) {
        if(StopTime < 0) {throw new IndexOutOfBoundsException("time can not be less then 0");}
        Sleep((long) StopTime);
    }
    public void Sleep(double StopTime) {
        if(StopTime < 0) {throw new IndexOutOfBoundsException("time can not be less then 0");}
        Sleep((long)StopTime);
    }
    public void Sleep(long StopTime) {
        if(StopTime < 0) {throw new IndexOutOfBoundsException("time can not be less then 0");}
        StopTime = (StopTime * 1000);
        try {sleep(StopTime);
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}
