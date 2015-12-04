package name.lamanov.intelijplugins.kutezh;


import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class KutezhRegistration implements ApplicationComponent {

    private Calendar startDate = Calendar.getInstance();
    private Calendar finishDate = Calendar.getInstance();


    @Override
    public void initComponent() {
        ActionManager am = ActionManager.getInstance();
        Timer timer = new Timer();
        startDate.set(
                Calendar.DAY_OF_WEEK,
                Calendar.FRIDAY
        );
        startDate.set(Calendar.HOUR, 16);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);

        finishDate.set(
                Calendar.DAY_OF_WEEK,
                Calendar.FRIDAY
        );
        finishDate.set(Calendar.HOUR, 23);
        finishDate.set(Calendar.MINUTE, 59);
        finishDate.set(Calendar.SECOND, 59);
        finishDate.set(Calendar.MILLISECOND, 0);


        // Schedule to run at Friday every 30 min after 16:00
        if (isTimeToKutezh()) {
            timer.schedule(
                    new KutezhRunner(),
                    0,
                    1000 * 60 * 20
            );
        }
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Kutezh";
    }

    private boolean isTimeToKutezh() {
        long curTime = System.currentTimeMillis();
        Calendar curDate = Calendar.getInstance();
        curDate.setTimeInMillis(curTime);
        return  curDate.get(Calendar.DAY_OF_WEEK) == startDate.get(Calendar.DAY_OF_WEEK)
                && startDate.get(Calendar.HOUR) <= curDate.get(Calendar.HOUR)
                && curDate.get(Calendar.HOUR) <= finishDate.get(Calendar.HOUR);
    }

    private class KutezhRunner extends TimerTask{
        @Override
        public void run() {
            if (!isTimeToKutezh()) {
                cancel();
            } else {
                try{
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_K);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_K);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
