package pers.keafmd.accumulate.designmode.openclosedprinciple;

/**
 * Keafmd
 *
 * @ClassName: BasicProcessingLogic
 * @Description: 基本处理逻辑
 * @author: 牛哄哄的柯南
 * @date: 2022-06-27 19:19
 */
public class BasicProcessingLogic implements BasicProcessing{

    public int num;

    public BasicProcessingLogic() {
        num = 10;
    }

    @Override
    public void handle(){
        System.out.println("处理步骤a。。。");
        System.out.println("处理步骤b。。。");
        System.out.println("处理步骤c。。。");
        System.out.println(num);
    }
}
