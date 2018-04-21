package com.nowcoder.leetcode.链表;

/**
 *
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深度拷贝。
 *
 * @link https://leetcode-cn.com/problems/copy-list-with-random-pointer/description/
 */
public class Leet138CopyRandomList {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode cur = head;
        RandomListNode next,tmp,newHead;

        // 创建节点
        while(cur != null) {
            next = cur.next;
            tmp = new RandomListNode(cur.val);
            cur.next = tmp;
            tmp.next = next;
            cur = next;
        }
        newHead = head.next;

        // 赋值random
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        // 分离
        cur = head;
        while(cur != null) {
            next = cur.next.next;
            cur.next.next = next == null ? null : next.next;
            cur.next = next;
            cur = next;
        }

        return newHead;
    }
}

class RandomListNode {
    int val;
    RandomListNode next, random;
    RandomListNode(int x) { this.val = x; }
}