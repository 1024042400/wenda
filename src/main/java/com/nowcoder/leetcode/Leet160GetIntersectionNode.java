package com.nowcoder.leetcode;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 *   例如，下面的两个链表：
 *
 *   A:          a1 → a2
 *                       ↘
 *                       c1 → c2 → c3
 *                       ↗
 *   B:     b1 → b2 → b3
 *
 *   注意：

 *   如果两个链表没有交点，返回 null.
 *   在返回结果后，两个链表仍须保持原有的结构。
 *   可假定整个链表结构中没有循环。
 *   程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/description/
 *
 */
public class Leet160GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        int c1=0,c2=0,leftNum;

        while(curA != null) {
            c1++;
            curA = curA.next;
        }

        ListNode curB = headB;
        while(curB != null) {
            c2++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;

        leftNum = Math.abs(c1-c2);
        if(c1 > c2) {
            while(leftNum-- > 0) {
                curA = curA.next;
            }
        } else {
            while(leftNum-- > 0) {
                curB = curB.next;
            }
        }

        while(curA != null && curB != null) {
            if(curA == curB) return curA;
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode c2 = new ListNode(3);
        ListNode c3 = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);

        d.next = e;e.next = f;
        c.next = c2;c2.next=c3;c3.next = d;
        a.next = b;b.next = d;
        ListNode n = new Leet160GetIntersectionNode().getIntersectionNode(a,c);
        System.out.println(n.val);
    }
}
