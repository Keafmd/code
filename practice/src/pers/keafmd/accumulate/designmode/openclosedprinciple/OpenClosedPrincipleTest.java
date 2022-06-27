package pers.keafmd.accumulate.designmode.openclosedprinciple;

import java.util.Random;

/**
 * Keafmd
 *
 * @ClassName: OpenClosedPrincipleTest
 * @Description: 开闭原则
 * @author: 牛哄哄的柯南
 * @date: 2022-06-27 19:26
 */
public class OpenClosedPrincipleTest {
    public static void main(String[] args) {
        BasicProcessing  basicProcessing = new BasicProcessingLogic();
        boolean flag = new Random().nextBoolean();;
        if(flag){
            basicProcessing.handle();
        }else{
            CustomerSpecialProcessingLogic processingLogic = new CustomerSpecialProcessingLogic();
            processingLogic.specialHandel();
        }
    }
}
