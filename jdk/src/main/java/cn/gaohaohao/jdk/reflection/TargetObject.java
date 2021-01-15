package cn.gaohaohao.jdk.reflection;

/**
 * @author haoh.gao
 * @version 1.0
 * @description
 * @date 2020/9/28 16:09
 **/
public class TargetObject {
    private String value;

    public TargetObject() {
        value = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
