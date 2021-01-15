package cn.gaohaohao.jdk.reflection;

import java.lang.reflect.Field;

/**
 * @author haoh.gao
 * @version 1.0
 * @description
 * @date 2020/9/29 9:54
 **/
public class PrivateFieldTest {
    public static void main(String[] args)throws Exception{
        Class<PrivateField> privateFieldClass = PrivateField.class;
        Field privateName = privateFieldClass.getDeclaredField("name");
        privateName.setAccessible(true);
        PrivateField privateField = new PrivateField("Alunbar");
        String privateFieldValue = (String) privateName.get(privateField);
        System.out.println("私有变量值：" + privateFieldValue);
    }
}
