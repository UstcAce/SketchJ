package basics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicType {
    @Test
    public void testCase01() {
        Integer one1 = 1;
        Integer one2 = 1;

        Integer two = 2;
        Integer three = 3;

        Integer integer1 = 321;
        Integer integer2 = 321;
        int int1 = 321;

        Long long2 = 2l;

        // == 比较包装类地址，但是Integer常量池 -128 ~ 127
        System.out.println(one1 == one2);  // true
        // a.equal(b)，1. 保证b是和a一样的包装类 2.比较数值
        System.out.println(one1.equals(one2)); // true

        // == 一边有表达式或者基本类型时，会先进行拆箱，然后比较具体数值
        System.out.println(three == (one1 + two)); // true

        System.out.println(integer1 == int1); // true

        // 超出Integer常量池缓存范围，地址不相同
        System.out.println(integer1 == integer2); // false

        System.out.println(integer1.equals(integer2)); // true

        // 自动装箱
        System.out.println(integer1.equals(int1)); // true

        System.out.println(two.equals(long2)); // false

        System.out.println(one1 + one1 == long2);
    }

    @Test
    public void testCase02() {
        Short one1 = 1;
        Short one2 = 1;

        Short two = 2;
        Short three = 3;

        Short num1 = 321;
        Short num2 = 321;
        short num3 = 321;

        // == 比较包装类地址，但是Integer常量池 -128 ~ 127
        System.out.println(one1 == one2);  // true
        // a.equal(b)，1. 保证b是和a一样的包装类 2.比较数值
        System.out.println(one1.equals(one2)); // true

        // == 一边有表达式或者基本类型时，会先进行拆箱，然后比较具体数值
        System.out.println(three == (one1 + two)); // true

        System.out.println(num1 == num3); // true

        // 超出Integer常量池缓存范围，地址不相同
        System.out.println(num1 == num2); // false

        System.out.println(num1.equals(num2)); // true

        // 自动装箱
        System.out.println(num1.equals(num3)); // true
    }

    @Test
    public void testCase03() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println(list);
    }
}
