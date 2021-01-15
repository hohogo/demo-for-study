package cn.gaohaohao.jdk.reflection.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author haoh.gao
 * @version 1.0
 * @description
 * @date 2020/9/29 11:25
 **/
public interface MyInterface {

    static void main(String[] args) {
        InvocationHandler handler = new MyInvocationHandler();
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class[] { MyInterface.class },
                handler);


    }
}
