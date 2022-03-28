/**
//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 ) 
//
// 
//
// 示例 1： 
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
// 
//
// 示例 2： 
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],[],[5],[2],[],[]]
//输出：[null,-1,null,null,5,2]
// 
//
// 提示： 
//
// 
// 1 <= values <= 10000 
// 最多会对 appendTail、deleteHead 进行 10000 次调用 
// 
// Related Topics 栈 设计 队列 
// 👍 482 👎 0

**/

// 用两个栈实现队列
// 做题时间：2022-03-21 11:34:43
package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class YongLiangGeZhanShiXianDuiLieLcof{
    public static void main(String[] args) {
//        Solution solution = new YongLiangGeZhanShiXianDuiLieLcof().new Solution();
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class CQueue {
        Deque<Integer> stka;
        Deque<Integer> stkb;

    public CQueue() {
        stka = new ArrayDeque<>();
        stkb = new ArrayDeque<>();

    }
    
    public void appendTail(int value) {
        stka.push(value);
    }
    
    public int deleteHead() {
        //判断出队栈 是不是空的，是空的话，需要把入队栈的数，全挪到出队栈中
        if(stkb.isEmpty()){
            while(!stka.isEmpty()){
                stkb.push(stka.pop());
            }
        }
        //如果出队栈不为空，弹出栈顶
        if(!stkb.isEmpty()){
            return stkb.pop();
        }
        //出队栈为空，输出-1
        return -1;

    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
//leetcode submit region end(Prohibit modification and deletion)


}


