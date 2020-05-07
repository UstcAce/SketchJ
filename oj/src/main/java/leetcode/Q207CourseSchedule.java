package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 *
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 */
public class Q207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 没访问：0，当前轮访问：1，之前轮访问：-1
        int[] flags = new int[numCourses];
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            neighbors.get(arr[1]).add(arr[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfsFindCycle(flags, neighbors, i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 发现环返回true
     */
    private boolean dfsFindCycle(int[] flags, List<List<Integer>> neighbors, int idx) {
        if (flags[idx] == 1) return true;
        if (flags[idx] == -1) return false;
        flags[idx] = 1;
        for (int nbr : neighbors.get(idx)) {
            if (dfsFindCycle(flags, neighbors, nbr)) return true;
        }
        flags[idx] = - 1;
        return false;
    }

    /**
     * 入度表+BFS
     * 从入度为0的点开始出发，如果有环，一定有节点的入度始终不为 0，则出队列次数肯定不会为节点数。
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> neighbors = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            neighbors.add(new ArrayList<>());
        }
        // 构建邻接表和入度表
        for (int[] arr : prerequisites) {
            indegrees[arr[0]]++;
            neighbors.get(arr[1]).add(arr[0]);
        }
        // 将入度为0的课程入队列
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        // BFS 拓扑排序
        while (!queue.isEmpty()) {
            int pop = queue.poll();
            numCourses--;
            for (int nbr : neighbors.get(pop)) {
                indegrees[nbr]--;
                if (indegrees[nbr] == 0) {
                    queue.add(nbr);
                }
            }
        }
        return numCourses == 0;
    }

    @Test
    public void testCase01() {
        int[][] input = {{1, 0}};
        System.out.println(canFinish(2, input));
    }
}
