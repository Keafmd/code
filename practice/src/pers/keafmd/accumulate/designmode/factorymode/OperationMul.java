package pers.keafmd.accumulate.designmode.factorymode;

/**
 * Keafmd
 *
 * @ClassName: OperationMul
 * @Description: 乘运算
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:19
 */
public class OperationMul extends Operation{

    @Override
    public double getResult() {
        return getNumA() * getNumB();
    }
}
