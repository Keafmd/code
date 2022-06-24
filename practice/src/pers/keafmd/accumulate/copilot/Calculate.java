package pers.pers.keafmd.accumulate.copilot;

/**
 * Keafmd
 *
 * @ClassName: Calculate
 * @Description: 计算
 * @author: 牛哄哄的柯南
 * @date: 2022-03-31 10:11
 */
public class Calculate {

    /**
     * @Title: add
     * @Description: 加法
     * @param a 加数
     * @param b 加数
     * @return int
     *
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * @Title: sub
     * @Description: 减法
     * @param a 减数
     * @param b 减数
     * @return int
     *
     */
    public int sub(int a, int b) {
        return a - b;
    }

    /**
     * @Title: mul
     * @Description: 乘法
     * @param a 乘数
     * @param b 乘数
     * @return int
     *
     */
    public int mul(int a, int b) {
        return a * b;
    }

    /**
     * @Title: div
     * @Description: 除法
     * @param a 除数
     * @param b 除数
     * @return int
     *
     */
    public int div(int a, int b) {
        if(b == 0) {
            throw new RuntimeException("除数不能为0");
        }
        return a / b;
    }
    //求正弦
    public double sin(double a) {
        return Math.sin(a);
    }
    //判断是不是素数
    public boolean isPrime(int a) {
        if(a == 1) {
            return false;
        }
        for(int i = 2; i <= Math.sqrt(a); i++) {
            if(a % i == 0) {
                return false;
            }
        }
        return true;
    }
    //求1000以内的所有素数
    public int[] getPrime() {
        int[] prime = new int[1000];
        int count = 0;
        for(int i = 2; i < 1000; i++) {
            if(isPrime(i)) {
                prime[count++] = i;
            }
        }
        return prime;
    }
    //找出字符串的所有子串
    public String[] getSubString(String str) {
        String[] subString = new String[str.length()];
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j <= str.length(); j++) {
                subString[i] = str.substring(i, j);
            }
        }
        return subString;
    }
}
