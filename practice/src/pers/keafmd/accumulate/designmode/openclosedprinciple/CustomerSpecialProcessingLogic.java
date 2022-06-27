package pers.keafmd.accumulate.designmode.openclosedprinciple;

/**
 * Keafmd
 *
 * @ClassName: CustomerSpecialProcessingLogic
 * @Description: 客户处理逻辑
 * @author: 牛哄哄的柯南
 * @date: 2022-06-27 19:21
 */
public class CustomerSpecialProcessingLogic extends BasicProcessingLogic{

    @Override
    public void handle(){
        super.handle();
    }

    public void specialHandel(){
        super.handle();
        System.out.println("处理步骤d。。。");
    }

}
