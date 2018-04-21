package com.nowcoder.leetcode.链表;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * @link https://leetcode-cn.com/problems/partition-list/description/
 */
public class Leet86PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null)
            return null;

        ListNode a = new ListNode(0);
        ListNode b = new ListNode(0);

        ListNode curA = a;
        ListNode curB = b;
        ListNode cur = head;
        ListNode next;

        while(cur != null) {
            next = cur.next;
            cur.next = null;
            if(cur.val < x) {
                curA.next = cur;
                curA = curA.next;
            } else {
                curB.next = cur;
                curB = curB.next;
            }

            cur = next;
        }

        if(a.next != null) {
            curA.next = b.next;
            return a.next;
        } else {
            return b.next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(2);
        a.next=b; b.next=c;c.next=d;d.next=e;e.next=f;
        System.out.print("begin : ");
        print(a);
        System.out.print("after : ");
        print(new Leet86PartitionList().partition(a,5));
    }

    public static void print(ListNode node) {
        if(node == null) {
            System.out.println("null");
            return;
        }
        System.out.print(node.val);
        node =node.next;
        while(node!=null) {
            System.out.print(" -> " + node.val);
            node=node.next;
        }
        System.out.println();
    }
}
