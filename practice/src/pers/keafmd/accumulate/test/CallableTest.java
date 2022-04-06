package pers.keafmd.accumulate.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Keafmd
 *
 * @ClassName: Td04
 * @Description: 带返回值的线程实现方式
 * @author: 牛哄哄的柯南
 * @date: 2022-04-01 13:39
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            System.out.println("线程任务开始执行了....");
            Thread.sleep(2000);
            return 1;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        System.out.println("这里可以为所欲为....");

        // 为所欲为完毕之后，拿到线程的执行结果
        Integer result = futureTask.get();
        System.out.println("主线程中拿到异步任务执行的结果为：" + result);
    }
}
