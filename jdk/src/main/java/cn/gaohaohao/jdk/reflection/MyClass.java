package cn.gaohaohao.jdk.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haoh.gao
 * @version 1.0
 * @description
 * @date 2020/9/29 10:43
 **/
public class MyClass {

    public List<String> stringList = new ArrayList<>(0);

    public List<String> getStringList(){
        return this.stringList;
    }

    public void setStringList(List<String> list){
        this.stringList = list;
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, ClassNotFoundException {

////        泛型返回值
//        Method method = MyClass.class.getMethod("getStringList", (Class<?>[]) null);
//
//        Type returnType = method.getGenericReturnType();
//
//        if(returnType instanceof ParameterizedType){
//            ParameterizedType type = (ParameterizedType) returnType;
//            Type[] typeArguments = type.getActualTypeArguments();
//            for(Type typeArgument : typeArguments){
//                Class typeArgClass = (Class) typeArgument;
//                System.out.println("typeArgClass = " + typeArgClass);
//            }
//        }
//
////        泛型参数
//        method = MyClass.class.getMethod("setStringList", List.class);
//
//        Type[] genericParameterTypes = method.getGenericParameterTypes();
//
//        for(Type genericParameterType : genericParameterTypes){
//            if(genericParameterType instanceof ParameterizedType){
//                ParameterizedType aType = (ParameterizedType) genericParameterType;
//                Type[] parameterArgTypes = aType.getActualTypeArguments();
//                for(Type parameterArgType : parameterArgTypes){
//                    Class parameterArgClass = (Class) parameterArgType;
//                    System.out.println("parameterArgClass = " + parameterArgClass);
//                }
//            }
//        }
//
////        泛型成员变量
//        Field field = MyClass.class.getField("stringList");
//
//        Type genericFieldType = field.getGenericType();
//
//        if(genericFieldType instanceof ParameterizedType){
//            ParameterizedType aType = (ParameterizedType) genericFieldType;
//            Type[] fieldArgTypes = aType.getActualTypeArguments();
//            for(Type fieldArgType : fieldArgTypes){
//                Class fieldArgClass = (Class) fieldArgType;
//                System.out.println("fieldArgClass = " + fieldArgClass);
//            }
//        }
        System.out.println(MyClass.class == Class.forName("cn.gaohaohao.jdk.reflection.MyClass"));

    }
}
