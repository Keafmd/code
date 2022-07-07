package pers.keafmd.accumulate.codeinterviewguide.hanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Keafmd
 *
 * @ClassName: ClassicTowerOfHanoi
 * @Description: 经典汉诺塔
 * @author: 牛哄哄的柯南
 * @date: 2022-06-27 19:08
 */
public class ClassicTowerOfHanoi {
    public int step = 1;

    public void hanoi(List<Integer> a,List<Integer> b,List<Integer> c){
        int n = a.size();
        move(n,a,b,c);
    }

    public void move(int n,List<Integer> a,List<Integer> b,List<Integer> c){
        if(n == 1){
            System.out.println("a:"+a+",b:"+b+",c:"+c);
            System.out.println("第"+step+++"步，把"+a.get(0)+"号从"+a+"移动到"+c);
            c.add(a.remove(a.size()-1));
            return;
        }
        // 最初 a是满的,所有盘子都在a上,b是空的,c是空的

        //三步走战略：
        //第一步：把a上面的n-1个盘子从a借助c移动到b上
        move(n-1,a,c,b);
        System.out.println("a:"+a+",b:"+b+",c:"+c);
        System.out.println("第"+step+++"步，把"+a.get(a.size()-1)+"号从"+a+"移动到"+c);
        //第二步：把a上面的最下面的那个盘子从a移动到c
        c.add(a.remove(a.size()-1));
        //第三步：把b上面的n-1个盘子借助a移动到c上
        move(n-1,b,a,c);
    }

    public static void main(String[] args) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        List<Integer> cList = new ArrayList<>();

        aList.add(1);
        aList.add(2);
        aList.add(3);
        aList.add(4);
//        A.add(5);
//        A.add(6);

        ClassicTowerOfHanoi classicTowerOfHanoi = new ClassicTowerOfHanoi();
        classicTowerOfHanoi.hanoi(aList,bList,cList);

    }



}
