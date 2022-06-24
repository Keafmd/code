package pers.keafmd.accumulate.codeinterviewguide.catdogqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Keafmd
 *
 * @ClassName: CatDogQueue
 * @Description: 猫狗队列
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:44
 */
public class CatDogQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;

    public CatDogQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    //将Cat类或Dog类的实例放入队列中
    public void add(Pet pet){
        if(pet.getType().contains("dog")){
            dogQ.add(new PetEnterQueue(pet,this.count++));
        }else if(pet.getType().contains("cat")){
            catQ.add(new PetEnterQueue(pet,this.count++));
        }else{
            throw new RuntimeException("错误，宠物类型既不是猫也不是狗");
        }
    }

    //队列中所有的实例按照进队列的先后顺序依次弹出
    public Pet pollAll(){
        if(!dogQ.isEmpty()&&!catQ.isEmpty()){
            if(dogQ.peek().getCount()<catQ.peek().getCount()){
                return dogQ.poll().getPet();
            }else{
                return catQ.poll().getPet();
            }
        }else if(!catQ.isEmpty()){
            return catQ.poll().getPet();
        }else if(!dogQ.isEmpty()){
            return dogQ.poll().getPet();
        }else{
            throw new RuntimeException("错误，队列是空的！");
        }
    }

    //队列中Dog类的实例按照进队列的先后顺序依次弹出
    public Dog pollDog(){
        if(!dogQ.isEmpty()){
            return (Dog)dogQ.poll().getPet();
        }else{
            throw new RuntimeException("错误，队列是空的！");
        }
    }

    //队列中Cat类的实例按照进队列的先后顺序依次弹出
    public Cat pollCat(){
        if(!catQ.isEmpty()){
            return (Cat)catQ.poll().getPet();
        }else{
            throw new RuntimeException("错误，队列是空的！");
        }
    }

    //检查队列中是否还有Dog或Cat的实例
    public boolean isEmpty(){
        return dogQ.isEmpty()&&catQ.isEmpty();
    }

    //检查队列中是否有Dog类的实例
    public boolean isDogEmpty(){
        return dogQ.isEmpty();
    }

    //检查队列中是否有Cat类的实例
    public boolean isCatEmpty(){
        return catQ.isEmpty();
    }


}
