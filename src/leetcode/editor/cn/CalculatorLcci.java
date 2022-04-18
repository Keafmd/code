/**
//给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。 
//
// 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。 
//
// 示例 1: 
//
// 输入: "3+2*2"
//输出: 7
// 
//
// 示例 2: 
//
// 输入: " 3/2 "
//输出: 1 
//
// 示例 3: 
//
// 输入: " 3+5 / 2 "
//输出: 5
// 
//
// 说明： 
//
// 
// 你可以假设所给定的表达式都是有效的。 
// 请不要使用内置的库函数 eval。 
// 
// Related Topics 栈 数学 字符串 
// 👍 68 👎 0

**/

// 计算器
// 做题时间：2022-04-14 17:59:50
package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class CalculatorLcci{
    public static void main(String[] args) {
        Solution solution = new CalculatorLcci().new Solution();
        System.out.println(solution.calculate(" 3/2 "));
        
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int calculate(String s) {
        s =s.replaceAll(" ","");
        Deque<Integer> stack = new ArrayDeque<>();
        char flag = '+';
        int num = 0;
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if(!Character.isDigit(s.charAt(i)) || i==s.length()-1){
                if(flag=='+'){
                    stack.push(num);
                }else if(flag=='-'){
                    stack.push(-num);
                }else if(flag=='*'){
                    stack.push(stack.pop()*num);
                }else if(flag=='/'){
                    stack.push(stack.pop()/num);
                }
                flag = s.charAt(i);
                num =0;
            }

        }
        int res =0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }

        return res;


    }
}
//leetcode submit region end(Prohibit modification and deletion)


}


