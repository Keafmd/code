package pers.keafmd.accumulate.designmode.policymode;

/**
 * Keafmd
 *
 * @ClassName: FullReductionCashStrategy
 * @Description: 满减策略
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 19:59
 */
public class FullReductionCashStrategy extends CashStrategy{

    private double fullDeduction = 0.0d;
    private double moneyReturn = 0.0d;

    public FullReductionCashStrategy(double fullDeduction, double moneyReturn) {
        this.fullDeduction = fullDeduction;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double settlement(double money) {
        double res = money;
        if(money >=fullDeduction){
            res = money - (int)Math.floor(money/fullDeduction) * moneyReturn;
        }
        return res;
    }
}
