package pers.keafmd.accumulate.codeinterviewguide.catdogqueue;

/**
 * Keafmd
 *
 * @ClassName: QueueTest
 * @Description: 测试
 * @author: 牛哄哄的柯南
 * @date: 2022-06-22 19:04
 */
public class QueueTest {
    public static void main(String[] args) {
        CatDogQueue queue = new CatDogQueue();
        Dog dog1 = new Dog("dog1");
        Dog dog2 = new Dog("dog2");
        Cat cat1 = new Cat("cat1");
        Cat cat2 = new Cat("cat2");
        Cat cat3 = new Cat("cat3");
        queue.add(dog1);
        queue.add(cat1);
        queue.add(cat2);
        queue.add(cat3);
        queue.add(dog2);

        System.out.println(queue.pollAll().getType()); //dog1
        System.out.println(queue.pollAll().getType()); //cat1
        System.out.println(queue.pollDog().getType()); //dog2
        System.out.println(queue.isDogEmpty()); //true
        System.out.println(queue.isCatEmpty()); //false
        System.out.println(queue.isEmpty()); //false
        System.out.println(queue.pollAll().getType()); //cat2
        System.out.println(queue.pollDog().getType()); //报错

    }
}
