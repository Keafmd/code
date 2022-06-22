package keafmd.accumulate.designmode.factorymode;

/**
 * Keafmd
 *
 * @ClassName: OperationFactory
 * @Description: 计算工厂
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:23
 */
public class OperationFactory {
    public static Operation createOperation(String operate) throws Exception {
        Operation operation = null;
        switch (operate){
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new OperationDiv();
                break;
            default:
                throw new Exception("输入运算符不合法！");
        }
        return operation;
    }
}
