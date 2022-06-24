package pers.keafmd.accumulate.codeinterviewguide.stackwithgetmin;

import java.util.Stack;

/**
 * Keafmd
 *
 * @ClassName: MyStack2
 * @Description: 有最小值的栈
 * @author: 牛哄哄的柯南
 * @date: 2022-06-20 15:07
 */
public class MyStack2 {
    private Stack<Integer> stacka;
    private Stack<Integer> stackb;

    public MyStack2(){
        stacka = new Stack<>();
        stackb = new Stack<>();
    }

    //入栈
    public void push(Integer val){
        stacka.push(val);
        if(stacka.isEmpty()||val<getMin()){
            stackb.push(val);
        }else{
            stackb.push(getMin());
        }
    }

    //出栈
    public Integer pop(){
        if(stacka.isEmpty()){
            throw new RuntimeException("Stack is empty!");
        }
        Integer val = stacka.pop();
        stackb.pop();
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
