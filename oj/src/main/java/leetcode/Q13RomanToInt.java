package leetcode;

import org.junit.Test;

public class Q13RomanToInt {
    public int romanToInt(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // 4
        String s1 = "IV";
        // 9
        String s2 = "IX";
        // 40
        String s3 = "XL";
        // 90
        String s4 = "XC";
        // 400
        String s5 = "CD";
        // 900
        String s6 = "CM";

        s = s.replaceAll(s1, "1");
        s = s.replaceAll(s2, "2");
        s = s.replaceAll(s3, "3");
        s = s.replaceAll(s4, "4");
        s = s.replaceAll(s5, "5");
        s = s.replaceAll(s6, "6");

        int len = s.length();
        int sum = 0;
        for (int i=0; i<len; i++) {
            char c = s.charAt(i);
            if (c=='1') {
                sum += 4;
            } else if (c=='2') {
                sum += 9;
            } else if (c=='3') {
                sum += 40;
            } else if (c=='4') {
                sum += 90;
            } else if (c=='5') {
                sum += 400;
            } else if (c=='6') {
                sum += 900;
            } else if (c=='I') {
                sum += 1;
            } else if (c=='V') {
                sum += 5;
            } else if (c=='X') {
                sum += 10;
            } else if (c=='L') {
                sum += 50;
            } else if (c=='C') {
                sum += 100;
            } else if (c=='D') {
                sum += 500;
            } else if (c=='M') {
                sum += 1000;
            }
        }

        return sum;
    }

    /**
     * 把一个小值放在大值的左边，就是做减法，否则为加法
     */
    public int romanToInt2(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    @Test
    public void testCase01() {
        System.out.println(romanToInt("III"));
    }

    @Test
    public void testCase02() {
        System.out.println(romanToInt("IV"));
    }

    @Test
    public void testCase03() {
        System.out.println(romanToInt("IX"));
    }

    @Test
    public void testCase04() {
        System.out.println(romanToInt("LVIII"));
    }

    @Test
    public void testCase05() {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
