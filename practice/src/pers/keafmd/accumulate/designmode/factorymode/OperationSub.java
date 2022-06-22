package keafmd.accumulate.designmode.factorymode;

/**
 * Keafmd
 *
 * @ClassName: OperationSub
 * @Description: 减运算
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:18
 */
public class OperationSub extends Operation{

    @Override
    public double getResult() {
        return getNumA() - getNumB();
    }
}
