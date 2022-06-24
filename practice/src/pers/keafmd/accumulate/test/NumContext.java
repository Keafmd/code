package pers.pers.keafmd.accumulate.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Keafmd
 *
 * @ClassName: NumContext
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-01 11:43
 */
public class NumContext {
    private static final ThreadLocal<Integer> context = new ThreadLocal<>();

    public static void set(Integer num) {
        context.set(num);
    }


    public static Integer get() {
        return context.get()==null?0:context.get();
    }


}
