package name.lamanov.intelijplugins.kutezh;


import com.intellij.openapi.components.ApplicationComponent;
        import org.jetbrains.annotations.NotNull;

        import java.awt.AWTException;
        import java.awt.Robot;
        import java.awt.event.KeyEvent;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.Timer;
        import java.util.TimerTask;
        import java.util.logging.Logger;

public class KutezhRegistration implements ApplicationComponent {

    private Date startDate;
    private Date finishDate;
//    show Kutezh every 30 min
    private final long PERIOD = 30;

    @Override
    public void initComponent() {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                Calendar.DAY_OF_WEEK,
                Calendar.FRIDAY
        );
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        startDate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        finishDate = calendar.getTime();


        timer.schedule(
                new KutezhRunner(),
                0,
                1000 * 60 * PERIOD //30 sec
        );
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
        return startDate.getTime() <= curTime && curTime <= finishDate.getTime();
    }

    private class KutezhRunner extends TimerTask{
        @Override
        public void run() {
            if (isTimeToKutezh()) {
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
