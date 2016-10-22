package org.firstinspires.ftc.robotcontroller.internal.testcode;
import static java.lang.Thread.sleep;;
public class Continue {
    public Continue() {
    }
    /**
     * StopFor method takes a int
     * @param StopTime a int that stops the program for a given time, can not be less then 0 and one second is equivalent to 1000, must be an integer.
     */
    public void StopFor(int StopTime) {
        if(StopTime < 0) {
            throw new IndexOutOfBoundsException("time can not be less then 0");
        }
        try {
            sleep(StopTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * StopFor method takes a double
     * @param StopTime a double that stops the program for a given time, can not be less then 0 and one second is equivalent to 1000, must be an integer.
     */
    public void StopFor(double StopTime) {
    if(StopTime < 0) {
        throw new IndexOutOfBoundsException("time can not be less then 0");
    }
        StopFor((int) StopTime);
    }

    /**
     * StopFor method takes a float
     * @param StopTime a float that stops the program for a given time, can not be less then 0 and one second is equivalent to 1000, must be an integer.
     */
    public void StopFor(float StopTime) {
        if(StopTime < 0) {
            throw new IndexOutOfBoundsException("time can not be less then 0");
        }
        StopFor((int) StopTime);
    }
}
