package pers.keafmd.accumulate.designmode.policymode;

/**
 * Keafmd
 *
 * @ClassName: CashStrategy
 * @Description: 收银策略抽象类
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 19:50
 */
public abstract class CashStrategy {
    public abstract double settlement(double money);
}
