package pers.keafmd.accumulate.designmode.factorymode;

/**
 * Keafmd
 *
 * @ClassName: OperationAdd
 * @Description: 加运算
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:13
 */
public class OperationAdd extends Operation{

    @Override
    public double getResult() {
        return getNumA() + getNumB();
    }
}
