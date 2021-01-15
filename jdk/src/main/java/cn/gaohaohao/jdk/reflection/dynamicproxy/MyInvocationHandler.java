package cn.gaohaohao.jdk.reflection.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author haoh.gao
 * @version 1.0
 * @description
 * @date 2020/9/29 11:28
 **/
public class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //do something "dynamic"
        return null;
    }
}
