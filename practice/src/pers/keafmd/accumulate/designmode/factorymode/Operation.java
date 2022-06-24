package pers.keafmd.accumulate.designmode.factorymode;

/**
 * Keafmd
 *
 * @ClassName: Operation
 * @Description: 运算父类
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:10
 */
public class Operation {
    private double numA;
    private double numB;

    public double getNumA() {
        return numA;
    }

    public void setNumA(double numA) {
        this.numA = numA;
    }

    public double getNumB() {
        return numB;
    }

    public void setNumB(double numB) {
        this.numB = numB;
    }

    public double getResult() throws Exception {
        double res = 0;
        return res;
    }
}
