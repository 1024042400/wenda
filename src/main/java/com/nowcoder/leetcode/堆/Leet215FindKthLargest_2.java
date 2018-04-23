package com.nowcoder.leetcode.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，它是数组有序排列后的第 k 个最大元素，而不是第 k 个不同元素。
 *
 * 例如，
 * 给出 [3,2,1,5,6,4] 和 k = 2，返回 5。
 *
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 数组的长度。
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/
 */
public class Leet215FindKthLargest_2 {
    public int findKthLargest(int[] nums, int k) {
        if(k > nums.length || k < 1)
            return Integer.MIN_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(int i=0;i<k;i++) {
            pq.add(nums[i]);
        }

        for(int i=k;i<nums.length;i++) {
            if(nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        Leet215FindKthLargest_2 clazz =  new Leet215FindKthLargest_2();
        int[] a = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};

        System.out.println(clazz.findKthLargest(a,20));
    }
}
