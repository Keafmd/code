package pers.keafmd.accumulate.designmode.policymode;

/**
 * Keafmd
 *
 * @ClassName: CashContext
 * @Description: 收银上下文
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 20:05
 */
public class CashContext {
    CashStrategy cashStrategy;

    public void selectPreferential(String strategy){
        switch (strategy){
            case "原价":
                cashStrategy = new OriginalPriceCashStrategy();
                break;
            case "满300减50":
                cashStrategy = new FullReductionCashStrategy(300,50);
                break;
            case "打八折":
                cashStrategy = new DiscountCashStrategy(0.8);
                break;
            default:
                throw new RuntimeException("优惠不合法！");
        }
    }

    public double getPayFee(double money){
        return cashStrategy.settlement(money);
    }

}
