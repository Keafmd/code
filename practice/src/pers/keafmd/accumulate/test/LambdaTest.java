package pers.pers.keafmd.accumulate.test;

/**
 * Keafmd
 *
 * @ClassName: Td03
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-01 11:30
 */
public class LambdaTest {
    public static void main(String[] args) {
        /*Runnable task = () ->{
            System.out.println("Hello");
        };
        Thread thread = new Thread(task);
        thread.start();*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        }).start();

        new Thread(() -> {
            System.out.println("Hello");
        }).start();


    }
}
