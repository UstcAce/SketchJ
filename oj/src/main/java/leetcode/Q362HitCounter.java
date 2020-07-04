package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设计一个敲击计数器，使它可以统计在过去5分钟内被敲击次数。
 * 每个函数会接收一个时间戳参数（以秒为单位），你可以假设最早的时间戳从1开始，且都是按照时间顺序对系统进行调用（即时间戳是单调递增）。
 * 在同一时刻有可能会有多次敲击。
 *
 * 示例:
 * HitCounter counter = new HitCounter();
 *
 * <p>// 在时刻 1 敲击一次。 counter.hit(1);
 *
 * <p>// 在时刻 2 敲击一次。 counter.hit(2);
 *
 * <p>// 在时刻 3 敲击一次。 counter.hit(3);
 *
 * <p>// 在时刻 4 统计过去 5 分钟内的敲击次数, 函数返回 3 。 counter.getHits(4);
 *
 * <p>// 在时刻 300 敲击一次。 counter.hit(300);
 *
 * <p>// 在时刻 300 统计过去 5 分钟内的敲击次数，函数返回 4 。 counter.getHits(300);
 *
 * <p>// 在时刻 301 统计过去 5 分钟内的敲击次数，函数返回 3 。 counter.getHits(301);
 *
 * 进阶:
 * <p>如果每秒的敲击次数是一个很大的数字，你的计数器可以应对吗？
 *
 * 1. 统计在过去5分钟内被敲击次数。
 * 2. 最早的时间戳从1开始，且都是按照时间顺序对系统进行调用（即时间戳是单调递增）。
 * 3. 在同一时刻有可能会有多次敲击。
 *
 * 思路：
 * 1. 每次查询的是近300秒内的敲击次数
 * 2. 内部需要有更新保存区间的功能
 */
public class Q362HitCounter {
    /**
     * 记录严格单调递增时间戳
     */
    private List<Integer> list;

    /**
     * 记录时间戳对应的敲击次数
     */
    private Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public Q362HitCounter() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if (!map.containsKey(timestamp)) {
            list.add(timestamp);
        }
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
        deleteOutRangeRecord(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        deleteOutRangeRecord(timestamp);
        int totalCount = 0;
        for (int count : map.values()) {
            totalCount += count;
        }
        return totalCount;
    }

    /**
     * 删除不在缓存范围内的记录
     */
    private void deleteOutRangeRecord(int timestamp) {
        // 小于这个数的时间戳被清除
        int endTimeStamp = timestamp - 300 + 1;

        if (list.isEmpty() || list.get(0) > endTimeStamp) {
            return;
        }

        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < endTimeStamp) {
                idx++;
            } else {
                break;
            }
        }
        for (int i = 0; i < idx; i++) {
            map.remove(list.get(i));
        }

        list = list.subList(idx, list.size());
    }

    @Test
    public void testCase01() {
        Q362HitCounter counter = new Q362HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4));
        counter.hit(300);
        System.out.println(counter.getHits(300));
        System.out.println(counter.getHits(301));
    }
}
