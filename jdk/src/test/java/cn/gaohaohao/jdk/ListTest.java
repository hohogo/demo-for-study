package cn.gaohaohao.jdk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    @Test
    public void listTest(){
        List<String> list = new ArrayList<>(2);
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("1".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println();
    }
}
