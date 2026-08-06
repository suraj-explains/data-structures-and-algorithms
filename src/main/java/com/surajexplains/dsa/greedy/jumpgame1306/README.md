# LeetCode 1306: Jump Game III

## 📌 Problem Overview
Given an array of non-negative integers `arr` and a starting index `start`. When you are positioned at index `i`, you can jump to `i + arr[i]` or `i - arr[i]`.

Return `true` if you can reach **any index with value `0`**, or `false` otherwise.

> **Note:** You cannot jump outside the boundaries of the array at any time.

---

## 💡 Key Intuition: Graph Traversal (BFS)

Unlike Jump Game I (which moves strictly left-to-right), Jump Game III allows moving **both left and right**. This transforms the array into an **unweighted directed graph** where:
* **Nodes:** Each index $i$ in the array ($0 \le i < N$).
* **Edges:** Outgoing directed edges from node $i$ to $(i + \text{arr}[i])$ and $(i - \text{arr}[i])$ (if within array boundaries).

Because we want to explore reachable nodes level-by-level starting from `start`, **Breadth-First Search (BFS)** using a Queue is an intuitive and optimal approach.

---

## 🌐 Graph Representation

Consider the array `arr = [4, 2, 3, 0, 3, 1, 2]` with `start = 5`:

### 1. Indexed Array View
```text
Index:   0    1    2    3    4    5    6
       ┌───┬────┬────┬────┬────┬────┬────┐
Val:   │ 4 │ 2  │ 3  │ 0  │ 3  │ 1  │ 2  │
       └───┴────┴────┴────┴────┴────┴────┘

Node [0] (val: 4)  ──►  [4]                         (0 - 4 = -1 is Out of Bounds)
Node [1] (val: 2)  ──►  [3]                         (1 - 2 = -1 is Out of Bounds)
Node [2] (val: 3)  ──►  [5]                         (2 - 3 = -1 is Out of Bounds)
Node [3] (val: 0)  ──►  [TARGET 🎯]
Node [4] (val: 3)  ──►  [1]                         (4 + 3 =  7 is Out of Bounds)
Node [5] (val: 1)  ──►  [6], [4]                    (Start Node)
Node [6] (val: 2)  ──►  [4]                         (6 + 2 =  8 is Out of Bounds)

 

            [5] (Start)
                 /   \
        +1      /     \     -1
               ▼       ▼
             [6]       [4]
              │         │
           -2 │         │ -3
              ▼         ▼
             [4]*      [1]
                      /   \
             +2      /     \     -2
                    ▼       ▼
          (TARGET) [3]     [-1] (Out of Bounds)


```

## ⚙️ BFS Algorithm Step-by-Step

1. **Initialize Queue & Tracking:**
    * Push the `start` index into a queue.
    * Maintain a mechanism to track visited indices to avoid infinite loops (either a `visited` set or in-place array mutation by negating values `arr[i] = -arr[i]`).

2. **Level-by-Level Processing:**
    * While the queue is not empty:
        1. Dequeue the current index `curr`.
        2. **Success Condition:** If `arr[curr] == 0`, return `true`.
        3. **Explore Neighbors:** Calculate two potential moves:
            * **Forward Jump:** `next_right = curr + arr[curr]`
            * **Backward Jump:** `next_left = curr - arr[curr]`
        4. **Validation:** For each move, check if it is **within array bounds** and **not yet visited**.
        5. If valid, mark the index as visited and push it into the queue.

3. **Termination:**
    * If the queue becomes empty and no zero value was reached, return `false`.

---

## 🎨 Visual Walkthrough (BFS Execution)

### Example: Input: `arr = [4, 2, 3, 0, 3, 1, 2]`, `start = 5`

```text
Index:     0    1    2    3    4    5    6
Array:   [ 4    2    3    0    3    1    2 ]
                                    ^
                             Start at Index 5

────────────────────────────────────────────────────────────────────────────────
Step 0: Pop Node 5 | Val = 1
Index:     0    1    2    3    4    5    6
Array:   [ 4    2    3    0    3   (1)*  2 ]
Queue:   [ 5 ]
Visited: { 5 }

Next Jumps from Index 5:
  • Right: 5 + 1 = 6  (Valid ──> Push 6)
  • Left:  5 - 1 = 4  (Valid ──> Push 4)

────────────────────────────────────────────────────────────────────────────────
Step 1: Pop Node 6 | Val = 2
Index:     0    1    2    3    4    5    6
Array:   [ 4    2    3    0    3   (1)* (2)*]
Queue:   [ 6, 4 ]
Visited: { 5, 6 }

Next Jumps from Index 6:
  • Right: 6 + 2 = 8  (Out of Bounds ❌)
  • Left:  6 - 2 = 4  (Valid ──> Push 4)

────────────────────────────────────────────────────────────────────────────────
Step 2: Pop Node 4 | Val = 3
Index:     0    1    2    3    4    5    6
Array:   [ 4    2    3    0   (3)* (1)* (2)*]
Queue:   [ 4 ]
Visited: { 5, 6, 4 }

Next Jumps from Index 4:
  • Right: 4 + 3 = 7  (Out of Bounds ❌)
  • Left:  4 - 3 = 1  (Valid ──> Push 1)

────────────────────────────────────────────────────────────────────────────────
Step 3: Pop Node 1 | Val = 2
Index:     0    1    2    3    4    5    6
Array:   [ 4   (2)*  3    0   (3)* (1)* (2)*]
Queue:   [ 1 ]
Visited: { 5, 6, 4, 1 }

Next Jumps from Index 1:
  • Right: 1 + 2 = 3  (Valid ──> Push 3)
  • Left:  1 - 2 = -1 (Out of Bounds ❌)

────────────────────────────────────────────────────────────────────────────────
Step 4: Pop Node 3 | Val = 0
Index:     0    1    2    3    4    5    6
Array:   [ 4   (2)*  3   (0)🎯 (3)* (1)* (2)*]
Queue:   [ 3 ]
Visited: { 5, 6, 4, 1, 3 }

arr[3] == 0  ==>  🎯 TARGET REACHED! Return true.
```

## 🛠️ Java Implementation

```java
    public boolean canReach(int[] arr, int start) {

        int n = arr.length;
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        deque.offer(start);
        visited[start] = true;

        while (!deque.isEmpty()) {
            int currIndex = deque.poll();

            // Reached goal condition
            if (arr[currIndex] == 0) {
                return true;
            }

            int forwardJump = currIndex + arr[currIndex];
            int backwardJump = currIndex - arr[currIndex];

            if (forwardJump >= 0 && forwardJump < n && !visited[forwardJump]) {
                deque.offer(forwardJump);
                visited[forwardJump] = true;
            }

            if (backwardJump >= 0 && backwardJump < n && !visited[backwardJump]) {
                deque.offer(backwardJump);
                visited[backwardJump] = true;
            }
        }
        return false;
    }

```

## ⏱️ Detailed Complexity Analysis

### 1. Time Complexity: $\mathcal{O}(N)$
* **Node Processing:** In an array of size $N$, there are at most $N$ distinct indices (nodes). Because of the `visited` array, each index is inserted into the queue and processed **at most once**.
* **Edge Evaluations:** For every index dequeued, we perform a constant number of operations:
    * Computing 2 potential jump destinations (`curr + arr[curr]` and `curr - arr[curr]`).
    * Checking array boundary limits ($0 \le \text{nextIdx} < N$).
    * Looking up and updating the `visited` array.
* **Total Time:** $\mathcal{O}(V + E) = \mathcal{O}(N + 2N) = \mathcal{O}(N)$, where $V$ is the number of vertices (indices) and $E$ is the maximum number of outgoing edges (jumps per index).

---

### 2. Space Complexity: $\mathcal{O}(N)$
* **Queue Storage:** In the worst-case scenario (such as a tree-like branching graph), the BFS queue can store up to $N$ elements simultaneously.
* **Visited Tracking:** The boolean `visited` array of size $N$ takes $\mathcal{O}(N)$ extra space.
* **Total Auxiliary Space:** $\mathcal{O}(N)$.

---

## 💡 Space Optimization Note (In-Place Mutation)

If mutating the input array is permitted, we can eliminate the `boolean[] visited` array to save memory overhead.

### Strategy:
Mark visited elements in-place by negating their value (`arr[i] = -arr[i]`).
* An index is **unvisited** if `arr[i] >= 0`.
* An index is **visited** if `arr[i] < 0`.

### Summary Comparison Table

| Approach | Time Complexity | Auxiliary Space | Mutates Input? |
| :--- | :--- | :--- | :--- |
| **Standard BFS (with `visited` array)** | $\mathcal{O}(N)$ | $\mathcal{O}(N)$ | ❌ No |
| **In-Place BFS (modifying `arr`)** | $\mathcal{O}(N)$ | $\mathcal{O}(N)$ *(Queue only)* | ✅ Yes |