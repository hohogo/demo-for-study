package cn.gaohaohao.jnidemo;

public class HelloJNI {
    static {
        // hello.dll (Windows) or libhello.so (Unixes)
        System.loadLibrary("hello");
    }

    private native void sayHello();

    public static void main(String[] args) {

        new HelloJNI().sayHello();  // invoke the native method

        String.valueOf()
    }

}