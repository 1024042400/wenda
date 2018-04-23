package com.nowcoder.leetcode.堆;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 中位数是排序后列表的中间值。如果列表的大小是偶数，则没有中间值，此时中位数是中间两个数的平均值。
 *
 * 示例:
 *
 * [2,3,4] , 中位数是 3
 *
 * [2,3], 中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中增加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 例如：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * https://leetcode-cn.com/problems/find-median-from-data-stream/description/
 */
public class Leet295MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>(1000,new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });
    private PriorityQueue<Integer> q2 = new PriorityQueue<>(1000);
    private int count = 0;

    public void addNum(int num) {
        count++;
        if(count < 3) {
            if(count == 1)
                q1.add(num);
            if(count == 2){
                if(num < q1.peek()) {
                    q2.add(q1.poll());
                    q1.add(num);
                } else {
                    q2.add(num);
                }
            }

        } else if((count&1)==1) {
            if(num > q2.peek()) { // 什么时候必然加到最小堆呢?
                q1.add(q2.poll());
                q2.add(num);
            } else {
                q1.add(num);
            }
        } else {
            if(num < q1.peek()) { // 什么时候必然加到最大堆呢?
                q2.add(q1.poll());
                q1.add(num);
            } else {
                q2.add(num);
            }

        }
    }

    public double findMedian() {
        if(count == 0) {
            return 0;
        }

        return ((count&1)==1) ? q1.peek() : (q1.peek() + q2.peek() ) / 2.0;
    }

    public static void main(String[] args) {
        Leet295MedianFinder clazz = new Leet295MedianFinder();
        clazz.addNum(-1);
        clazz.addNum(-2);
        System.out.println(clazz.findMedian());
        clazz.addNum(-3);
        System.out.println(clazz.findMedian());
        clazz.addNum(-4);
        System.out.println(clazz.findMedian());
    }
}
