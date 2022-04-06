package pers.keafmd.accumulate.test;

/**
 * Keafmd
 *
 * @ClassName: Td02
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-01 11:23
 */
public class Td02 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " + "我是线程Td02");
    }

}
