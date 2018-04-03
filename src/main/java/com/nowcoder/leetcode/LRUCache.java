package com.nowcoder.leetcode;

import java.util.HashMap;

public class LRUCache{
    LRUCacheInner cache;
    public LRUCache(int capacity) {
        cache = new LRUCacheInner(capacity);
    }

    public int get(int key) {
        Integer  ret = (Integer) cache.get(key);
        return ret == null ? -1 : ret;
    }

    public void put(int key, int value) {
        cache.put(key,value);
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(2,2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
//        cache.put(3, 3);    // 该操作，会将 key 2 作废
//        cache.get(2);
//        System.out.println(cache.get(2));
////        System.out.println(cache.get(1));
//        cache.put(4, 4);    // 该操作，会将 key 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (结果不存在)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4
    }
}

class LRUCacheInner<K,V> {
        int capacity;
        LRUCacheInner(int capacity) {
            this.capacity = capacity;
        }

        HashMap<Object,Node<K,V>> map = new HashMap<>();
        private static class Node<K,V> {
            K key;
            V value;
            Node<K,V> pre;
            Node<K,V> next;
            Node(K key,V v){
                this.key = key;
                this.value = v;
            }
        }
        private Node<K,V> head;
        private Node<K,V> tail;

        public V getFirst() {
            return head == null ? null : head.value;
        }

        public V getLast() {
            return tail == null ? null : tail.value;
        }

        public V get(K key) {
            Node<K,V> node = map.get(key);
            if(node == null) {
                return null;
            }
            addAtHead(node);

            return node.value;
        }

        public void put(K key,V value) {
            Node node;
            if(map.size() == 0) {
                node = new Node(key,value);
                head = tail = node;
            } else {
                node = map.get(key);
                popNode(node);
                if(node == null)
                    node = new Node(key,value);
                else
                    node.value = value;
                addAtHead(node);
            }

            map.put(key,node);
        }

        private void addAtHead(Node node) {
            if(node != head) {
                popNode(node);
                // 容量不够, 删掉队尾
                if(map.size() + 1 > capacity) {
                    popNode(tail);
                }

                node.next = head;
                if(head != null)
                    head.pre = node;
                head = node;
                if(tail == null)
                    tail = node;

                map.put(node.key,node);
            }
        }
        private void popNode(Node node) {
            if(node == null || !map.containsKey(node.key)) return;
            map.remove(node.key);
            if(head == node)
                head = node.next;
            if(tail == node)
                tail = node.pre;
            if(node.pre != null) {
                node.pre.next = node.next;
            }
            if(node.next != null) {
                node.next.pre = node.pre;
            }

            node.pre = node.next = null;
        }

}


