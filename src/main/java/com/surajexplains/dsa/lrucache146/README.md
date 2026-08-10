# LRU Cache Implementation in Java

A clean, clear, and high-performance **Least Recently Used (LRU) Cache** implementation in Java using a **HashMap** combined with a **Doubly Linked List** to achieve **$O(1)$ time complexity** for both `get` and `put` operations.

---

## 📌 What is an LRU Cache?

An **LRU (Least Recently Used) Cache** is an eviction policy-based memory cache that retains a fixed number of items. When the cache reaches its maximum capacity and a new item needs to be added, it automatically discards the **least recently accessed** item.

### 💡 Real-World Analogy: Web Browser Tabs
Think of an LRU cache like your browser's recently used tabs:
* Whenever you **open** or **click on** a tab, it moves to the front of your attention (Most Recently Used).
* If your browser forces a maximum tab limit, opening a new tab closes the tab you **haven't looked at for the longest time** (Least Recently Used).

---

## 🏗️ Architecture & Data Structures

To achieve **$O(1)$ time complexity** for lookups, insertions, and deletions, two data structures are combined:

```
                  +-------------------------------------------------------+
                  |                      HashMap                          |
                  |   Key   --->   Node Pointer                           |
                  +-------------------------------------------------------+
                                        |
                                        v
                 +------+     +---+---+---+     +---+---+---+     +------+
[ Dummy Head ] ->| prev |<--->| K | V |   |<--->| K | V |   |<--->| prev |<- [ Dummy Tail ]
 (Most Recent)   | next |     |   |   |   |     |   |   |   |     | next |   (Least Recent)
                 +------+     +---+---+---+     +---+---+---+     +------+
                                Most Recently     Least Recently
                                 Used (MRU)        Used (LRU)
```

1. **HashMap (`Map<Integer, Node>`)**: Provides **$O(1)$ access** to search for any key and directly retrieve its node pointer in memory.
2. **Doubly Linked List (`DoublyLL`)**: Provides **$O(1)$ updates** to reposition nodes.
    * **Dummy `head`**: The node right after `head` (`head.next`) is always the **Most Recently Used (MRU)** item.
    * **Dummy `tail`**: The node right before `tail` (`tail.prev`) is always the **Least Recently Used (LRU)** item.

---

## ⚙️ How Operations Work

### 1. `get(key)`
```
1. Does key exist in HashMap?
   ├── NO  ---> Return -1
   └── YES ---> Move node to the front (right after Head)
                Return node value
```

* **Step 1:** Look up the key in the HashMap.
* **Step 2:** If found, detach the node from its current position in the Doubly Linked List.
* **Step 3:** Insert the node right after the dummy `head` (marking it as Most Recently Used).
* **Step 4:** Return the node's value.

---

### 2. `put(key, value)`
```
1. Does key already exist in HashMap?
   ├── YES ---> Remove old node from Doubly Linked List
   │            Update HashMap & insert new node at Head (MRU)
   └── NO  ---> Insert new node at Head (MRU) & HashMap
                Is cache size > capacity?
                ├── YES ---> Evict node at Tail.prev (LRU)
                │            Remove key from HashMap
                └── NO  ---> Done
```

* **Updating Existing Key:** Remove the old node from the list, update the key in the HashMap with the new node, and place it at the front (`head.next`).
* **Inserting New Key:** Add the new node at the front (`head.next`) and put it in the HashMap.
* **Eviction Check:** If `map.size() > capacity`, fetch the node at `tail.prev` (LRU), remove it from both the Doubly Linked List and the HashMap.

---

## 💻 Java Code Implementation

```java
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
```

---

## ⏱️ Complexity Analysis

| Operation | Time Complexity | Space Complexity | Explanation |
| :--- | :---: | :---: | :--- |
| **`get(key)`** | $O(1)$ | $O(1)$ | HashMap lookup is $O(1)$; unlinking/linking pointers in a Doubly LL is $O(1)$. |
| **`put(key, value)`** | $O(1)$ | $O(1)$ | HashMap insertion/deletion and list reordering occur in constant time. |
| **Overall Space** | — | $O(C)$ | Where $C$ is the maximum capacity of the cache. |

---

## 🚀 Example Walkthrough

Assuming `capacity = 2`:

```java
LRUCache cache = new LRUCache(2);

cache.put(1, 10); // Cache: [1:10]
cache.put(2, 20); // Cache: [2:20, 1:10] (2 is MRU, 1 is LRU)

cache.get(1);     // Returns 10. Cache reordered: [1:10, 2:20] (1 becomes MRU)

cache.put(3, 30); // Evicts key 2 (LRU)! Cache: [3:30, 1:10]

cache.get(2);     // Returns -1 (not found)
```
