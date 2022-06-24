package pers.keafmd.accumulate.codeinterviewguide.stackwithgetmin;

import java.util.Stack;

/**
 * Keafmd
 *
 * @ClassName: getMinStack
 * @Description: 有最小值的栈
 * @author: 牛哄哄的柯南
 * @date: 2022-06-20 14:29
 */
public class MyStack1 {
    private Stack<Integer> stacka;
    private Stack<Integer> stackb;

    public MyStack1(){
        stacka = new Stack<>();
        stackb = new Stack<>();
    }
    //入栈
    public void push(Integer val){
        stacka.push(val);
        if(stackb.isEmpty()||val<=getMin()){
            stackb.push(val);
        }
    }

    //出栈
    public Integer pop(){
        if(stacka.isEmpty()){
            throw new RuntimeException("Stack is empty!");
        }
        Integer val = stacka.pop();
        if(val.equals(getMin())){
            stackb.pop();
        }
        return val;
    }

    //获取最小值
    public Integer getMin(){
        if(stackb.isEmpty()){
            throw new RuntimeException("Stack is empty!");
        }
        return stackb.peek();
    }

}
