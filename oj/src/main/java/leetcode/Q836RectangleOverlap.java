package leetcode;

/**
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 说明：
 *
 * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
 * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
 */
public class Q836RectangleOverlap {

    /**
     * 反推，如果不相交有哪些可能
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0]// rec1 在 rec2的左边
                || rec1[0] >= rec2[2]  // rec1在rec2的右边
                || rec1[1] >= rec2[3]  // rec1在rec2的上面
                || rec1[3] <= rec2[1]);  // rec1在rec2的下面
    }
}
