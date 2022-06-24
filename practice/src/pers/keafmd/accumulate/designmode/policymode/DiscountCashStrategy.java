package pers.keafmd.accumulate.designmode.policymode;

/**
 * Keafmd
 *
 * @ClassName: DiscountCashStrategy
 * @Description: 打折策略
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 19:57
 */
public class DiscountCashStrategy extends CashStrategy{

    private double discount = 1d;

    public DiscountCashStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public double settlement(double money) {
        return money * discount;
    }
}
