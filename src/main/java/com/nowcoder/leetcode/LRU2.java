package com.nowcoder.leetcode;

import java.util.HashMap;

public class LRU2<K,V> {
    private class Node<K,V> {
        K key;
        V value;
        Node(K k,V v) {
            key = k;
            value = v;
        }
        Node<K,V> pre,next;
    }

    private HashMap<K,Node<K,V>> map = new HashMap<>();
    private Node<K,V> head,tail; // 头 尾
    private int capacity;

    public LRU2(int capacity) {
        this.capacity = capacity;
    }

    public void addNode(Node node) {
        if(node == null)
            return;
        if(head == null) {
            head = tail = node;
        } else {
            // 与尾指针相连
            node.pre = tail;
            tail.next = node;
            tail = node;
        }
    }

    /**
     *
     * 要处理三个事情, 1. 维护头节点, 2. 维护尾节点, 3. 维护本身节点
     * 只有一个节点, 只有2个节点, 多个节点
     * @param node
     */
    public void moveNodeToTail(Node node) {
        // 满足 0值, 1 个值
        if(node == null || tail == node) {
            return;
        }
        if(node == head) { //维护头节点
            head.pre = null;
            head = head.next;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        // 与尾部相连
        tail.next = node;
        node.pre = tail;
        tail = node;

        node.next = null; //node.next有可能不为空
    }

    public void put(K k,V v) {

    }
}
