package pers.keafmd.accumulate.test;

import keafmd.accumulate.temp.Td02;

/**
 * Keafmd
 *
 * @ClassName: Td01
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-01 11:17
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行");
        System.out.println(Thread.currentThread().getState());
        System.out.println(Thread.currentThread());
    }

    public static void main(String[] args) {
       /* Td01 td01 = new Td01();
        td01.setName("线程1");
        td01.start();
        System.out.println("::"+Thread.currentThread());*/

        System.out.println(Thread.currentThread().getName());
        Thread t = new Thread(new Td02());
        t.setName("线程2");
        t.start();

    }
}
