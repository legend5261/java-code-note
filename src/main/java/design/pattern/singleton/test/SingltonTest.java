package design.pattern.singleton.test;

import design.pattern.singleton.SingletonDoubleLock;

public class SingltonTest {

    public static void main(String[] args) {
        SingletonDoubleLock singleton1 = SingletonDoubleLock.getInstance();
        SingletonDoubleLock singleton2 = SingletonDoubleLock.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton1.count());
        System.out.println(singleton2.count());
    }
}
