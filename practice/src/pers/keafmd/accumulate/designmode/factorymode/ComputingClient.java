package pers.keafmd.accumulate.designmode.factorymode;

import java.util.Scanner;

/**
 * Keafmd
 *
 * @ClassName: ComputingClient
 * @Description: 计算客户端
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:29
 */
public class ComputingClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入两个数：");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        System.out.println("请输入运算符（+，-，*，/）：");
        String s = sc.next();
        Operation operation = OperationFactory.createOperation(s);
        operation.setNumA(a);
        operation.setNumB(b);
        System.out.println("运算结果为：" + operation.getResult());

    }
}
