package keafmd.accumulate.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Keafmd
 *
 * @ClassName: AtomicIntegerTest
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-28 15:01
 */
public class AtomicIntegerTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }
    private static void test1() throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    counter.addCount();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("test1 count = " + counter.getCount());
    }

    private static void test2() throws InterruptedException {
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    count.incrementAndGet();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("test2 count = " + count.get());
    }
}

class Counter {
    private volatile static int count = 0;

    public void addCount(){
        count++;
//        count = count + 1;
//        count += 1;
    }
    public int getCount(){
        return count;
    }
}