package pers.keafmd.accumulate.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Keafmd
 *
 * @ClassName: Timer
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-01 11:35
 */
public class TimerTest {
    private static final SimpleDateFormat format =
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    //指定时间点执行
    public static void main(String[] args) throws ParseException {





        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务执行了...");
            }
        }, format.parse("2022-04-01 11:41:00"));*/

        // 创建定时器
        Timer timer = new Timer();

        // 提交计划任务
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               NumContext.set(NumContext.get() + 1);
                               System.out.println(NumContext.get());
                               System.out.println("定时任务执行了...");
                           }
                       },
                new Date(), 1000);
    }

}
