package pers.keafmd.accumulate.codeinterviewguide.stacksorting;

import java.util.Stack;

/**
 * Keafmd
 *
 * @ClassName: StackSort
 * @Description: 用一个栈排序另外一个栈
 * @author: 牛哄哄的柯南
 * @date: 2022-06-24 16:06
 */
public class StackSort {

    public void sort(Stack<Integer> stackData){
        Stack<Integer> stackHelp = new Stack<>();
        while(!stackData.isEmpty()){
            int cur = stackData.pop();
            while(!stackHelp.isEmpty()&&stackHelp.peek()<cur){
                stackData.push(stackHelp.pop());
            }
            stackHelp.push(cur);
        }

        while(!stackHelp.isEmpty()){
            stackData.push(stackHelp.pop());
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(6);
        stack.push(5);
        stack.push(7);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(4);

        System.out.print("排序前（栈顶到栈底）：");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();

        stack.push(3);
        stack.push(6);
        stack.push(5);
        stack.push(7);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(4);

        StackSort stackSort = new StackSort();
        stackSort.sort(stack);
        System.out.print("排序后（栈顶到栈底）：");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();

    }

}
