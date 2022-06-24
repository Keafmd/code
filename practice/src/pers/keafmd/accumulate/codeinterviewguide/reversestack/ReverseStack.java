package pers.keafmd.accumulate.codeinterviewguide.reversestack;

import java.util.Stack;

/**
 * Keafmd
 *
 * @ClassName: ReverseStack
 * @Description: 使用函数逆序栈
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 11:01
 */
public class ReverseStack {
    //返回栈底元素并移除
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int res = stack.pop();
        if(stack.isEmpty()){
            return res;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(res);
            return last;
        }
    }

    //逆序栈
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return ;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for(int i=1;i<=5;i++){
            stack.push(i);
        }
        System.out.print("逆序前：");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();
        for(int i=1;i<=5;i++){
            stack.push(i);
        }
        reverse(stack);
        System.out.print("逆序后：");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();
    }
}
