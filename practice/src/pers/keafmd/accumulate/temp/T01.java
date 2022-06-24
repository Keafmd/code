package pers.keafmd.accumulate.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Keafmd
 *
 * @ClassName: T01
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-03-31 11:19
 */
public class T01 {
    public static int add(int a, int b) {
        try {
            System.out.println("开始计算");
            return a + b;
        } catch (Exception e) {
            System.out.println("catch 语句块");
        } finally {
            System.out.println("finally 语句块");
        }
        return 0;
    }

    public static void main(String[] args) {
        //创建一个线程
        List<Integer> list = new CopyOnWriteArrayList<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add(i);
                }
                System.out.println(list);
            }
        });

        //启动线程
        for (int i = 0; i < 10; i++) {

            t1.start();
            System.out.println(t1.getId());
            System.out.println(t1.getState());
        }

    }


}
