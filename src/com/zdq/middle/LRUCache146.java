package com.zdq.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache146 {

    public static void main(String[] args) {
        LRUCache146 cache = new LRUCache146(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.nodeMap.keySet());
        cache.put(4, 1);
        System.out.println(cache.nodeMap.keySet());
        cache.put(3, 1);
        System.out.println(cache.nodeMap.keySet());
        cache.put(3, 1);
        System.out.println(cache.nodeMap.keySet());
        cache.put(3, 1);
        System.out.println(cache.nodeMap.keySet());
        cache.put(5, 1);
        System.out.println(cache.nodeMap.keySet());

    }

    private final int capacity;

    private final Map<Integer, Node<Integer, Integer>> nodeMap;

    private final DoubleLinkedList<Integer, Integer> doubleLinkedList;


    public LRUCache146(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key) {
        if (nodeMap.containsKey(key)) {
            Node<Integer, Integer> integerNode = nodeMap.get(key);
            doubleLinkedList.removeNode(integerNode);
            doubleLinkedList.addHead(integerNode);
            return integerNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        //如果已经存在，则更新
        if (nodeMap.containsKey(key)) {
            Node<Integer, Integer> node = nodeMap.get(key);
            node.value = value;
            nodeMap.put(key, node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            //不存在时新增，此时需要判断容量是否超过限制
            if (nodeMap.size() == capacity) {
                //删除
                Node<Integer, Integer> last = doubleLinkedList.getLast();
                nodeMap.remove(last.key);
                doubleLinkedList.removeNode(last);
            }
            //新增node
            Node<Integer, Integer> newNode = new Node<>(key, value);
            doubleLinkedList.addHead(newNode);
            nodeMap.put(key, newNode);
        }

    }


    //节点数据
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node() {

        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //操作节点
    static class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.pre = head;
        }

        /**
         * 插入真实头结点
         *
         * @param node 待插入节点
         */
        public void addHead(Node<K, V> node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        public void removeNode(Node<K, V> node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = null;
            node.pre = null;
        }

        public Node<K, V> getLast() {
            return tail.pre;
        }
    }


}
