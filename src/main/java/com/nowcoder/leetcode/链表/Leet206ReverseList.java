package com.nowcoder.leetcode.链表;

/**
 * 反转链表
 * @link https://leetcode-cn.com/problems/reverse-linked-list/description/
 */
public class Leet206ReverseList {
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode h = new ListNode(0);
        ListNode cur = head;
        ListNode next;
        while(cur != null) {
            next = cur.next;
            cur.next = h.next;
            h.next = cur;
            cur = next;
        }

        return h.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}