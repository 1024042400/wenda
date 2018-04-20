package com.nowcoder.leetcode;

/**
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @link https://leetcode-cn.com/problems/reverse-linked-list-ii/description/
 */
public class Leet92ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || m<=0 || n<=0 || m>=n) {
            return head;
        }
        ListNode mNode = null;
        ListNode next;
        int count = 1;
        ListNode cur = head; // 当前节点

        ListNode mPreNode = new ListNode(0); // 有可能前一个节点为空
        while(cur != null){
            next = cur.next;

            if(count == m-1){
                mPreNode = cur;
                mPreNode.next = null;
            } else if(count == m)
                mNode = cur;

            if(count >= m && count <= n ){
                cur.next = mPreNode.next;
                mPreNode.next = cur;
            } else if(count > n) {
                if(mNode != null)
                    mNode.next = cur;
                break;
            }

            count ++;
            cur = next;
        }

        return m == 1 ? mPreNode.next : head;
    }
}