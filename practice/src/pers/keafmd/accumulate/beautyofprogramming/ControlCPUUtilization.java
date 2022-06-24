package pers.keafmd.accumulate.beautyofprogramming;


import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * Keafmd
 *
 * @ClassName: ControlCPUUtilization
 * @Description: 控制cpu占用率
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 16:41
 */
public class ControlCPUUtilization {



    public static void main(String[] args) throws InterruptedException {

        long busyTime = 5;
        Thread th = Thread.currentThread();
        long nowSys = System.currentTimeMillis();
        while (true) {
            nowSys = System.currentTimeMillis();
            while ((System.currentTimeMillis() - nowSys) <= busyTime) {

            }
            th.sleep(5);
        }
    }

//    public static void achieve1(){
//        int i = 0;
//        boolean flag = true;
//        while (true){
//            if(flag){
//                i++;
//                flag = false;
//            }else{
//                flag = true;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        achieve1();
//    }

    /*public static void main(String[] args) {
        OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Thread th = Thread.currentThread();
        System.out.println(th);
        System.out.println(bean.getProcessCpuLoad());
        System.out.println(bean.getSystemCpuLoad());
        while (true) {
            long nowSys = System.currentTimeMillis();
            while ((System.currentTimeMillis() - nowSys) <= 8 && bean.getSystemCpuLoad() > 0.5) {
                System.out.println("超过额定cpu使用率50%，执行该进程");
            }
            try {
                th.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		/*
		//控制cpu范围在30%--70%
		while (true) {
			long nowSys = System.currentTimeMillis();
			while((System.currentTimeMillis()-nowSys)<=8 && bean.getSystemCpuLoad()>0.3&& bean.getSystemCpuLoad()<0.7){
				System.out.println(bean.getSystemCpuLoad());
			}
			System.out.println(bean.getProcessCpuLoad());
			//判断当前cpu的使用率是否低于30，如果低于30，则启动线程
			if (bean.getSystemCpuLoad() < 0.3) {
				while (bean.getSystemCpuLoad() < 0.3) {
					new Thread(){
						public void run(){
							System.out.println("Thread Running");
						}
					}.start();
				}
			}else if(bean.getSystemCpuLoad() > 0.7) {
				//判断当前cpu的使用率是否高于70，如果高于70，则释放资源
				try {
					th.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}*/
    //}


}
