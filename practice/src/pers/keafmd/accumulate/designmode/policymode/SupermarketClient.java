package pers.keafmd.accumulate.designmode.policymode;

import java.util.Scanner;

/**
 * Keafmd
 *
 * @ClassName: SupermarketClient
 * @Description: 超市客户端
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 20:15
 */
public class SupermarketClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double lumpSum = 400d;
        System.out.println("所有商品优惠前总费用："+ lumpSum);
        System.out.println("请选择优惠：原价,满300减50,打八折");
        String preferential = sc.next();
        CashContext cc = new CashContext();
        cc.selectPreferential(preferential);

        System.out.println("优惠后总费用：" + cc.getPayFee(lumpSum));

    }
}
