package leetcode;

import org.junit.Test;

import java.util.Random;

public class PickOneOJ {
    @Test
    public void testCase01() {
        String[] ojs = {"206. 反转链表", "142. 环形链表II", "445. 两数相加 II", "124. 二叉树中的最大路径和",
                "5. 最长回文子串", "10. 正则表达式匹配", "44. 通配符匹配", "4. 寻找两个正序数组的中位数", "39,40. 组合总和",
                "227. 基本计算器 II", "48. 旋转图像", "535. TinyURL 的加密与解密", "200. 岛屿数量"};
        int[] weights = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        int totalWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            totalWeight += weights[i];
        }

        Random random = new Random();
        int num = random.nextInt(totalWeight);

        int w = 0;
        for (int i = 0; i < weights.length; i++) {
            int tempEnd = w + weights[i];
            if (num >= w && num < tempEnd) {
                System.out.println(ojs[i]);
                break;
            }
            w = tempEnd;
        }
    }
}
