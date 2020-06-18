package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * 请返回所有可行解 s 中最长长度。
 *
 * 示例 1：
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 *
 * 示例 2：
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 *
 * 示例 3：
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *  
 *
 * 提示：
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 */
public class Q1239MaxLength {
    int max = 0;
    public int maxLength(List<String> arr) {
        List<String> validList = arr.stream().filter(str -> containsDuplicate(str)).collect(Collectors.toList());
        traceBackCalc(new HashSet<>(), validList, 0);
        return max;
    }

    /**
     * 回溯计算
     */
    private void traceBackCalc(Set<Character> temp, List<String> validList, int pos) {
        if (pos == validList.size()) {
            max = Math.max(max, temp.size());
            return;
        }
        Set<Character> addSet = getCharSet(validList.get(pos));
        if (canAddStr(temp, addSet)) {
            temp.addAll(addSet);
            traceBackCalc(temp, validList, pos + 1);
            temp.removeAll(addSet);
        }
        traceBackCalc(temp, validList, pos + 1);
    }

    /**
     * 判断是否可以加入字符集合
     */
    private boolean canAddStr(Set<Character> temp, Set<Character> addSet) {
        Set<Character> total = new HashSet<>(temp);
        total.addAll(addSet);
        return total.size() == addSet.size() + temp.size();
    }

    /**
     * 将字符串转化为字符集合
     */
    private Set<Character> getCharSet(String str) {
        Set<Character> result = new HashSet<>();
        for (char c : str.toCharArray()) {
            result.add(c);
        }
        return result;
    }

    /**
     * 过滤出不含重复字符的字符串
     */
    private boolean containsDuplicate(String str) {
        Set<Character> set = new HashSet<>();

        int count = 0;
        for (char c : str.toCharArray()) {
            set.add(c);
            count++;
            if (count != set.size()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCase01() {
        List<String> input = new ArrayList<>(Arrays.asList("un","iq","ue"));
        System.out.println(maxLength(input));
    }
}
