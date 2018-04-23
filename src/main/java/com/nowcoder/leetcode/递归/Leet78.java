package com.nowcoder.leetcode.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @link https://leetcode-cn.com/problems/subsets/description/
 */
public class Leet78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list =  new ArrayList();
        ret.add(list);
        for(int i=0;i<nums.length;i++) {
            addSub(ret,list,nums,i);
        }

        return ret;
    }

    private void addSub(List ret,List cur,int[] nums,int index) {
        if(index == nums.length)
            return;
        Integer data = nums[index];
        cur.add(data);
        ret.add(myClone(cur));
        for(int i=index+1;i<nums.length;i++) {
            addSub(ret,cur,nums,i);
        }
        cur.remove(data);

    }

    private List myClone(List<Integer> l) {
        List<Integer> newList = new ArrayList<>(l.size());
        for(Integer i : l) {
            newList.add(i);
        }
        return newList;
    }

    public static void main(String[] args) {
        List<List<Integer>> t = new Leet78().subsets(new int[]{1,2,3});

        for(List<Integer> l : t) {
            System.out.println(Arrays.toString(l.toArray()));
        }

    }
}
