package basics;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Array2Collection {

    /**
     * 基础数据类型数组(int double float long)转集合
     */
    @Test
    public void testcase01() {
        int[] intArr = {1, 2, 3, 4, 5};
        List<Integer> intList = Arrays.stream(intArr).boxed().collect(Collectors.toList());

        double[] doubleArr = {1d, 2d, 3d, 4d, 5d};
        List<Double> doubleList = Arrays.stream(doubleArr).boxed().collect(Collectors.toList());

        long[] longArr = {1L, 2L, 3L, 4L, 5L};
        List<Long> longList = Arrays.stream(longArr).boxed().collect(Collectors.toList());
        longList.set(0, 11L);
        System.out.println(longList);
        System.out.println(Arrays.toString(longArr));

        float[] floatArr = {1f, 2f, 3f, 4f, 5f};
        Float[] lFloatArr = new Float[floatArr.length];
        for (int i=0; i<floatArr.length; i++) {
            lFloatArr[i] = floatArr[i];
        }

        // Notice: Fixed Size List
        List<Float> floatList = Arrays.asList(lFloatArr);
        // (1) 修改值会引起原数组的值的变动，内存一样
        floatList.set(0, 11f);
        System.out.println(floatList);
        System.out.println(Arrays.toString(lFloatArr));
        // (2) 不支持很多List操作
        // floatList.add(11f);
    }

    /**
     * 引用类型数组转集合
     */
    @Test
    public void testcase02() {
        String[] stringArr = {"A", "C", "E"};
        List<String> stringList = new ArrayList<>(Arrays.asList(stringArr));

        Point[] pointArr = {new Point(1,1), new Point(2, 2), new Point(3, 3)};
        List<Point> pointList = new ArrayList<>(Arrays.asList(pointArr));
    }

    /**
     * 集合转数组
     */
    @Test
    public void testcase03() {
        int[] intArr = {1, 2, 3, 4, 5};
        List<Integer> intList = Arrays.stream(intArr).boxed().collect(Collectors.toList());

        Integer[] intArr2 = intList.toArray(new Integer[]{});
        System.out.println(Arrays.toString(intArr2));
    }
}
