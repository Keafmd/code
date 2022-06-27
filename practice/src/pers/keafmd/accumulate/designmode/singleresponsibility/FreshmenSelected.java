package pers.keafmd.accumulate.designmode.singleresponsibility;

/**
 * Keafmd
 *
 * @ClassName: FreshmenSelected
 * @Description: 新生入学
 * @author: 牛哄哄的柯南
 * @date: 2022-06-27 19:39
 */
public class FreshmenSelected {

    public void distributeBooks(){
        System.out.println("分发书籍。。。");
    }

    public void allocateDormitory(){
        System.out.println("分配宿舍。。。");
    }

    public static void main(String[] args) {
        System.out.println("开始安排新生入学工作：");
        FreshmenSelected freshmenSelected = new FreshmenSelected();
        freshmenSelected.distributeBooks();
        freshmenSelected.allocateDormitory();
        System.out.println("工作结束！");

    }
}
