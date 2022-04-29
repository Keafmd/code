package keafmd.accumulate.temp;

/**
 * Keafmd
 *
 * @ClassName: Test01
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2022-04-06 20:13
 */
public class Test01 {
    public static void main(String[] args) {
//        long m = m("1000000000000000001");
//        System.out.println(m);

    }
    class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }

    }

    public Node m(Node a,Node b){
        if(a==null){
            return b;
        }
        if(b==null){
            return a;
        }
        Node head = null;
        if(a.val<=b.val){
            head = a;
        }else{
            head = b;
        }
        if(head==a){
            a = a.next;
        }else{
            b = b.next;
        }
        while(a!=null&&b!=null){
            if(a.val<=b.val){
                head.next = a;
                a= a.next;
            }else{
                head.next = b;
                b = b.next;
            }
            head = head.next;
        }
        if(a==null){
            head.next = b;
        }else{
            head.next = a;
        }
        return head;
    }



    public static long m(String s){
        long res = 0L;
        int a = 1;
        for (int i = s.length()-1; i >=0; i--) {
            res += (long) (s.charAt(i) - '0') *a;
            a*=2;
        }

        return res;
    }
}
