package leetcode;

import org.junit.Test;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */

/**
 * 滑动窗口算法的思路是这样：
 *
 * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。
 *
 * 2、我们先不断地增加 right 指针扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
 *
 * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
 *
 * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
 *
 */
public class Q76MinWindow {
    //滑动窗口的应用
    public String minWindow2(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        //题目里没有说只是大写 在提交的时候踩了不少坑..
        int[] auxT = new int[128]; //auxT用来存目标字符串
        for (int i = 0; i < t.length(); i++) {
            auxT[t.charAt(i)]++;
        }
        int[] auxS = new int[128]; //auxS用来存滑动窗口里的内容
        for (int i = 0; i < t.length(); i++) {
            auxS[s.charAt(i)]++;
        }

        //start end左右指针 min记录滑动窗口最小长度 startIndex记录最小长度的起始位置
        int start = 0, end = t.length(), min = Integer.MAX_VALUE, startIndex = 0;

        while (end <= s.length()) {
            while (check(auxS, auxT)) {//如果包含 就移动左指针缩小范围
                if (end - start < min) {
                    min = end - start;
                    startIndex = start;
                }
                auxS[s.charAt(start++)]--;
            }
            if (end <= s.length() - 1) { //这里判断一下end的越界问题
                auxS[s.charAt(end++)]++;
            } else {
                break;
            }
        }
        return min != Integer.MAX_VALUE ? s.substring(startIndex, startIndex + min) : "";
    }

    private boolean check(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] < nums2[i]) {
                return false;
            }
        }
        return true;
    }


    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0) return "";
        if (tLen == 0) return s.substring(0, 1);

        int[] auxT = new int[128]; //auxT用来存目标字符串
        for (int i = 0; i < t.length(); i++) {
            auxT[t.charAt(i)]++;
        }
        int minLen = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        int[] auxS = new int[128]; //auxS用来存滑动窗口里的内容

        int i = 0;
        while (i < sLen) {
            auxS[s.charAt(i)]++;

            int j = i;
            while (j < sLen) {
                if (j != i) {
                    auxS[s.charAt(j)]++;
                }
                int localLen = j - i + 1;
                if (localLen >= tLen && localLen < minLen && check(auxS, auxT)) {
                    minLen = localLen;
                    start = i;
                    end = j;
                }
                j++;
            }
            for (int idx = i; idx < sLen; idx++) {
                auxS[s.charAt(idx)]--;
            }

            i++;
        }

        return end == -1 ? "" : s.substring(start, end + 1);
    }

    @Test
    public void testCase01() {
        String s = "ADOBECODEBANC";
        String t = "ABCA";

        String res = minWindow(s, t);
        System.out.println(res);
    }

    @Test
    public void testCase02() {
        String s = "a";
        String t = "a";

        String res = minWindow(s, t);
        System.out.println(res);
    }
}
