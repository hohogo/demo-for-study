package cn.gaohaohao.jdk;

import cn.gaohaohao.jdk.covariance.Apple;
import cn.gaohaohao.jdk.covariance.Fruit;
import cn.gaohaohao.jdk.covariance.GreenApple;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FruitTest {

    @Test
    public void test1() {
        eatFruitMeat(new Fruit());//输出eat generic fruit meat
        eatFruitMeat(new Apple());//输出eat apple meat
        eatFruitMeat(new GreenApple());//输出eat green apple meat
    }

    public void eatFruitMeat(Fruit fruit) {
        System.out.println("eat "+fruit.returnMeat());
    }

    @Test
    public void test2() {
        List<GreenApple> greenApples = Lists.newArrayList(new GreenApple());
        List<Fruit> fruits = Lists.newArrayList(new Fruit());
        List<Apple> apples = Lists.newArrayList(new Apple());
        eatFruitMeats(greenApples);
//        eatFruitMeats(fruits);//编译错误1
        eatFruitMeats(apples);
    }

    public void eatFruitMeats(List<? extends Apple> fruits) {
        fruits.forEach(fruit->System.out.println("eat "+fruit.returnMeat()));
//        fruits.add(new Apple());//编译错误2
//        fruits.add(new Fruit());//编译错误3
//        fruits.add(new Object());//编译错误4
    }

    @Test
    public void test3() {
        List<Fruit> fruits = Lists.newArrayList();
        List<Apple> apples = Lists.newArrayList();
        List<GreenApple> greenAppleLists = Lists.newArrayList();

        collectFruits(fruits);
        collectFruits(apples);
//        collectFruits(greenAppleLists);//编译错误1
    }

    public void collectFruits(List<? super Apple> fruits) {
//        fruits.add(new Fruit());//编译错误2
        fruits.add(new Apple());
        fruits.add(new GreenApple());
    }
}
