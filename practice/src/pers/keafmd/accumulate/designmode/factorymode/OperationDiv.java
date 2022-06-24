package pers.keafmd.accumulate.designmode.factorymode;

/**
 * Keafmd
 *
 * @ClassName: OperationDiv
 * @Description: 除运算
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:20
 */
public class OperationDiv extends Operation{

    @Override
    public double getResult() throws ArithmeticException {
        if(getNumB()==0){
            throw new ArithmeticException("除数不能为0！");
        }
        return getNumA() / getNumB();
    }
}
