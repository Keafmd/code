package pers.keafmd.accumulate.designmode.singleresponsibility;

/**
 * Keafmd
 *
 * @ClassName: ClassMonitor
 * @Description: 班长
 * @author: 牛哄哄的柯南
 * @date: 2022-06-27 19:46
 */
public class ClassMonitor {
    private StudyMembers studyMembers;
    private LifeCommittee lifeCommittee;

    public ClassMonitor() {
        studyMembers = new StudyMembers();
        lifeCommittee = new LifeCommittee();
    }

    public void assignWork(){
        System.out.println("班长开始给不同的班干部分配工作，共同完成新生入学工作：");
        studyMembers.distributeBooks();
        lifeCommittee.allocateDormitory();
        System.out.println("工作结束！");
    }

    public static void main(String[] args) {
        System.out.println("开始安排新生入学工作：");
        ClassMonitor classMonitor = new ClassMonitor();
        classMonitor.assignWork();
    }
}
