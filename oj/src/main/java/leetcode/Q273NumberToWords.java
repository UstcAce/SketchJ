package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 * 示例 2:
 *
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 * 示例 3:
 *
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4:
 *
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 */
public class Q273NumberToWords {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        return largeNumber2Words(num);
    }

    /**
     * 0 <= num <= 999
     * num = 0, 返回空字符串
     */
    private String largeNumber2Words(int num) {
        if (num == 0) return "";
        Map<Integer, String> map = initWordsMap();
        StringBuilder sb = new StringBuilder();
        if (num >= 1 && num <= 20) {
            return map.get(num);
        } else if (num >= 21 && num <= 99) {
            int lastDigit = num % 10;
            int tenDigit = num - lastDigit;
            sb.append(map.get(tenDigit));
            if (lastDigit > 0) sb.append(" ").append(map.get(lastDigit));
        } else {
            int[][] digitTable = {{100, 999}, {1000, 999999}, {1000000, 999999999}, {1000000000, Integer.MAX_VALUE}};
            String[] strings = {" Hundred", " Thousand", " Million", " Billion"};
            for (int i = 0; i < digitTable.length; i++) {
                if (num >= digitTable[i][0] && num <= digitTable[i][1]) {
                    int mainDigit = num / digitTable[i][0];
                    int remaining = num % digitTable[i][0];
                    sb.append(largeNumber2Words(mainDigit)).append(strings[i]);
                    if (remaining > 0) sb.append(" ").append(largeNumber2Words(remaining));
                }
            }
        }
        return sb.toString();
    }

    private Map<Integer, String> initWordsMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");

        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");

        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");

        return map;
    }

    @Test
    public void testCase01() {
        int num = 30;
        System.out.println(largeNumber2Words(num));
    }

}
