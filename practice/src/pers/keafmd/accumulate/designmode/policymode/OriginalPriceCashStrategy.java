package pers.keafmd.accumulate.designmode.policymode;

/**
 * Keafmd
 *
 * @ClassName: OriginalPriceCashStrategy
 * @Description: 原价策略
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 19:56
 */
public class OriginalPriceCashStrategy extends CashStrategy{
    @Override
    public double settlement(double money) {
        return money;
    }
}
