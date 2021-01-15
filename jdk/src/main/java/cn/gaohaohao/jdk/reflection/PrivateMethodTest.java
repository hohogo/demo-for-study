package cn.gaohaohao.jdk.reflection;

import java.lang.reflect.Method;

/**
 * @author haoh.gao
 * @version 1.0
 * @description
 * @date 2020/9/29 9:59
 **/
public class PrivateMethodTest {
    public static void main(String[] args)throws Exception{
        Class<PrivateMethod> privateMethodClass = PrivateMethod.class;

        Method privateStringMethod = privateMethodClass.getDeclaredMethod("accesPrivateMethod", (Class<?>[]) null);
        privateStringMethod.setAccessible(false);
        String returnValue = (String)privateStringMethod.invoke(new PrivateMethod(), (Object[]) null);

        System.out.println("returnValue = " + returnValue);
    }
}
