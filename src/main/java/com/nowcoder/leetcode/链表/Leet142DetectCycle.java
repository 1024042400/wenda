package com.nowcoder.leetcode.链表;

/**
 * 给一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 说明：不应修改给定的链表。
 *
 * 补充：
 * 你是否可以不用额外空间解决此题？
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/description/
 */
public class Leet142DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode node = getOneNodeOfCycle(head);
        if(node == null)
            return null;
        if(node == head)
            return head;// 如果head在圆内, 返回head

        ListNode cur = head;
        while(cur != null) {
            cur = cur.next;
            node = node.next;
            if(cur == node) {
                return cur;
            }
        }
        return null;
    }

    private ListNode getOneNodeOfCycle(ListNode head) {
        if(head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
            if(slow == fast && slow != null)
                return slow;
        }

        return null;
    }
}
