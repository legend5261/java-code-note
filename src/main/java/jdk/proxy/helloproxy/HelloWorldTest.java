package jdk.proxy.helloproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class HelloWorldTest {
    public static void main(String[] args) {
        HelloWorld hw = new HelloWorldImpl();
        System.out.println(hw.getClass().getInterfaces());
        InvocationHandler handler = new HelloWorldHandler(hw);
        HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(hw.getClass().getClassLoader(), hw.getClass().getInterfaces(), handler);
        proxy.sayHelloWorld();
    }
}
