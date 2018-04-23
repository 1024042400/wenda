package com.nowcoder.leetcode.堆;

import java.util.Arrays;

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
public class Leet215FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        if(k > nums.length || k < 1)
            return Integer.MIN_VALUE;
        int[] minHeap = new int[k+1];
        for(int i=1;i<=k;i++) {
            minHeap[i] = nums[i-1];
            adjustHeap(minHeap,i);
        }

        for(int i=k+1;i<=nums.length;i++) {
            int cur = nums[i-1];
            if(cur > minHeap[1]) {
                minHeap[1] = cur;
                adjustHeapAtHead(minHeap);
            }
        }

        return minHeap[1];
    }

    private void adjustHeap(int[] arr,int index){
        while(index > 1) {
            if(arr[index] < arr[index/2]) {
                swap(arr,index,index/2);
            } else {
                break;
            }
            index /= 2;
        }
    }

    private void adjustHeapAtHead(int[] arr) {
        int index = 1,tmp;
        while(index <= arr.length-1) {
            if(index*2 >= arr.length-1) {
                if(index*2 == arr.length-1 && arr[index] > arr[index*2]) {
                    swap(arr,index,index*2);
                }
                break;
            } else {
                tmp = arr[index*2] < arr[index*2 +1] ? index*2 : index*2+1;
                if(arr[tmp] > arr[index]) break;
                swap(arr,index,tmp);
                index = tmp;
            }
        }
    }

    private void swap(int[] arr,int i,int j) {
        if(i==j)
            return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        Leet215FindKthLargest clazz =  new Leet215FindKthLargest();
        int[] a = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};

        clazz.findKthLargest(a,20);
        System.out.println(Arrays.toString(a));
    }
}
