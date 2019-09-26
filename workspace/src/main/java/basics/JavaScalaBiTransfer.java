package basics;

import commons.Tuple2;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * From: https://blog.csdn.net/u013013024/article/details/80452019
 * 使用 scala.collection.JavaConverters 与Java集合交互。它有一系列的隐式转换，添加了asJava和asScala的转换方法。
 */
public class JavaScalaBiTransfer {

    /**
     * java 与 Scala的元组对比
     */
    @Test
    public void case01() {
        Tuple2<String, Integer> jTup = new Tuple2<>("Ace", 26);
        System.out.println(jTup);
        // java元组获取的是String
        String j = jTup.first;

        scala.Tuple2 sTup = new scala.Tuple2<>("Ace", 26);
        System.out.println(sTup);
        // scala元组获取的是Object
        Object s =  sTup._1;
        String s2 = (String) sTup._1;
    }

    /**
     * java 与 Scala的Map对比
     */
    @Test
    public void case02() {
        Map<String, Integer> jMap = new HashMap<>();
        jMap.put("Ace", 26);
        System.out.println(jMap);

        scala.collection.mutable.Map sMap = scala.collection.JavaConverters.mapAsScalaMap(jMap);
        sMap.put("Jack", 21);

        System.out.println(sMap);

        Map<String, Integer> jMap2 = scala.collection.JavaConverters.mutableMapAsJavaMap(sMap);
        System.out.println(jMap2);
    }

}
