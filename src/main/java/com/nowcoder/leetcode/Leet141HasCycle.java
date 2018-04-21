package com.nowcoder.leetcode;

/**
 * 给定一个链表，判断链表中是否有环。
 * https://leetcode-cn.com/problems/linked-list-cycle/description/
 */
public class Leet141HasCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode n1=head,n2=head;
        while(n1 != null && n2 != null) {
            n2 = n2.next;
            if(n1 == n2)
                return true;
            if(n2!= null){
                n2 = n2.next;
                if(n1 == n2)
                    return true;
            }
            n1 = n1.next;
            if(n1 == n2 && n1 != null)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Leet141HasCycle().hasCycle(new ListNode(1)));
    }
}
