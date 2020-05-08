package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *
 *  示例 2:
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 */
public class Q210CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            neighbors.get(arr[1]).add(arr[0]);
            indegrees[arr[0]]++;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                deque.add(i);
            }
        }
        List<Integer> seq = new ArrayList<>();
        while (!deque.isEmpty()) {
            int pop = deque.poll();
            seq.add(pop);
            numCourses--;
            for (int idx : neighbors.get(pop)) {
                indegrees[idx]--;
                if (indegrees[idx] == 0) {
                    deque.add(idx);
                }
            }
        }
        if (numCourses != 0) {
            return new int[0];
        } else {
            int[] res = new int[seq.size()];
            for (int i = 0; i < seq.size(); i++) {
                res[i] = seq.get(i);
            }
            return res;
        }
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] flags = new int[numCourses];
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            neighbors.get(arr[1]).add(arr[0]);
        }
        LinkedList<Integer> stack = new LinkedList<>();
        boolean isCyclic = false;
        for (int i = 0; i < numCourses; i++) {
            boolean b = dfsFindCycle(flags, neighbors, stack, i);
            if (b) {
                isCyclic = true;
                break;
            }
        }
        if (isCyclic) {
            return new int[0];
        } else {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = stack.get(i);
            }
            return res;
        }
    }

    private boolean dfsFindCycle(int[] flags, List<List<Integer>> neighbors, LinkedList<Integer> stack, int idx) {
        if (flags[idx] == 1) {
            return true;
        }
        if (flags[idx] == -1) {
            return false;
        }
        flags[idx] = 1;
        for (int i : neighbors.get(idx)) {
            if(dfsFindCycle(flags, neighbors, stack, i)) return false;
        }
        stack.addFirst(idx);
        flags[idx] = -1;
        return false;
    }

    @Test
    public void testCase01() {
        int[][] input = {{1, 0}};
        System.out.println(Arrays.toString(findOrder2(2, input)));
    }
}
