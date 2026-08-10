package com.surajexplains.dsa.lrucache146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Internal node representation
    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    // Custom Doubly Linked List with dummy head and tail
    private static class DoublyLL {
        private final Node head;
        private final Node tail;

        DoublyLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public Node getLRU() {
            return tail.prev;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final DoublyLL dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLL();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        // Move accessed node to the head (Most Recently Used)
        dll.remove(node);
        dll.addFirst(node);

        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Key exists: update value and move node to head
            Node node = map.get(key);
            node.val = value;
            dll.remove(node);
            dll.addFirst(node);
        } else {
            // Key does not exist: create and insert new node
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            dll.addFirst(newNode);

            // Evict Least Recently Used (LRU) node if capacity exceeded
            if (map.size() > capacity) {
                Node lruNode = dll.getLRU();
                map.remove(lruNode.key);
                dll.remove(lruNode);
            }
        }
    }
}