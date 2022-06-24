package pers.keafmd.accumulate.codeinterviewguide.twostacksformaqueue;

import java.util.Stack;

/**
 * Keafmd
 *
 * @ClassName: MyStack1
 * @Description: 两个栈实现的队列 add、poll、peek
 * @author: 牛哄哄的柯南
 * @date: 2022-06-21 17:38
 */
public class TwoStacksQueue {
    Stack<Integer> stackA;
    Stack<Integer> stackB;

    public TwoStacksQueue(){
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    //转移
    public void transfer(){
        if(stackB.isEmpty()){
            while(!stackA.isEmpty()){
                stackB.push(stackA.pop());
            }
        }
    }

    public void add(Integer val){
        stackA.push(val);
        transfer();
    }

    public Integer poll(){
        if(stackA.isEmpty()&&stackB.isEmpty()){
            throw new RuntimeException("Queue is empty!");
        }
        transfer();
        return stackB.pop();
    }

    public Integer peek(){
        if(stackA.isEmpty()&&stackB.isEmpty()){
            throw new RuntimeException("Queue is empty!");
        }
        transfer();
        return stackB.peek();
    }


}
