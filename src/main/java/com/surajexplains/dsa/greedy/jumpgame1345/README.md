# 1345. Jump Game IV

[![LeetCode](https://img.shields.io/badge/LeetCode-1345._Jump_Game_IV-FFA116?style=for-the-badge&logo=leetcode)](https://leetcode.com/problems/jump-game-iv/)
[![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red?style=for-the-badge)](#)

A comprehensive guide and breakdown for **LeetCode 1345: Jump Game IV**, featuring detailed problem analysis, step-by-step visual execution flowcharts, edge-case analysis, and an optimal linear time complexity BFS solution.

---

## 📋 Table of Contents
- [Problem Statement](#-problem-statement)
- [Key Insights & Intuition](#-key-insights--intuition)
- [Visual Explanations](#-visual-explanations)
    - [1. Allowed Jump Types](#1-allowed-jump-types)
    - [2. Detailed Walkthrough](#2-detailed-walkthrough)
    - [3. BFS Level Expansion Tree](#3-bfs-level-expansion-tree)
    - [4. Execution Trace Table](#4-execution-trace-table)
- [Code Implementation](#-code-implementation)
    - [Python](#python)
    - [C++](#c)
    - [Java](#java)
- [Complexity Analysis](#-complexity-analysis)
- [Edge Cases & Crucial Optimizations](#-edge-cases--crucial-optimizations)

---

## 📝 Problem Statement

Given an array of integers `arr`, you are initially positioned at the first index of the array (`index 0`).

In one step, you can jump from index `i` to index:
1. **`i + 1`** (where `i + 1 < arr.length`)
2. **`i - 1`** (where `i - 1 >= 0`)
3. **`j`** (where `arr[i] == arr[j]` and `i != j`)

Return the **minimum number of steps** required to reach the last index of the array (`index arr.length - 1`).

---

## 💡 Key Insights & Intuition

1. **Shortest Path on an Unweighted Graph:**
    - Every jump (forward, backward, or same-value teleportation) costs exactly **1 step**.
    - The problem is equivalent to finding the shortest path from start node `0` to target node `n - 1` in an unweighted graph.
    - **Breadth-First Search (BFS)** is the optimal algorithm for finding shortest paths in unweighted graphs.

2. **Graph Modeling:**
    - **Nodes:** Indices $0, 1, 2, \dots, n-1$.
    - **Edges from index $i$:**
        - Left step: $i - 1$
        - Right step: $i + 1$
        - Value teleportation: all $j$ where $	ext{arr}[j] == 	ext{arr}[i]$.

3. **Critical TLE Optimization (Clearing Same-Value Links):**
    - Consider an array where many elements share the same value (e.g., `arr = [7, 7, 7, ..., 7]`).
    - The first time we process index $i$ with value $V$, we explore **all** other indices with value $V$.
    - All these matching indices are added to the BFS queue at level `current_step + 1`.
    - **Crucial Step:** Once processed, we must **delete $V$ from our adjacency map** (`del value_to_indices[V]`).
    - Why? Because all indices containing value $V$ have already been visited or queued with the minimum steps. Re-traversing them later will yield no shorter path and will blow up the time complexity to $\mathcal{O}(N^2)$ (resulting in Time Limit Exceeded).

---

## 🎨 Visual Explanations

### 1. Allowed Jump Types

```
                        ┌──────────────────────────────────────────────┐
                        │          Same-Value Teleportation            │
                        │   (Jump directly to any j where arr[i]==arr[j])
                        ▼                                              │
          ┌─────────────────────────┐                                  │
Indices:  │   0  │   1  │   2  │  3 │   4  │  5   │  6   │  7   │  8   │  9  │
Values:   │  100 │ -23  │ -23  │404 │  100 │  23  │  23  │  23  │  3   │ 404 │
          └─────────────────────────┘                                  │
              │     ▲      ▲                                           │
              └─────┼──────┘                                           │
         Left/Right Step (i - 1, i + 1)                                │
              └────────────────────────────────────────────────────────┘
```

---

### 2. Detailed Walkthrough

Let's walk through Example 1 step-by-step:

```python
arr = [100, -23, -23, 404, 100, 23, 23, 23, 3, 404]
Target Index = 9 (value = 404)
```

#### Index-to-Value Adjacency Map:
```text
  Value  100 ──► Indices {0, 4}
  Value -23  ──► Indices {1, 2}
  Value  404 ──► Indices {3, 9}
  Value   23 ──► Indices {5, 6, 7}
  Value    3 ──► Indices {8}
```

---

### 3. BFS Level Expansion Tree

```
Level 0 (Start):
                    [ Index 0 ] (val: 100)
                         │
        ┌────────────────┴────────────────┐
        │ (Same Value)                    │ (i + 1)
        ▼                                 ▼
   [ Index 4 ] (val: 100)            [ Index 1 ] (val: -23)
        │                                 │
Level 1:│                                 │
   ┌────┴───────────────┐                 └──────────────┐
   │ (i - 1)            │ (i + 1)                        │ (Same Value)
   ▼                    ▼                                ▼
[ Index 3 ] (404)   [ Index 5 ] (23)               [ Index 2 ] (-23)
   │
Level 2:
   │ (Same Value)
   ▼
[ Index 9 ] (404) 🎯 TARGET REACHED! (Total Steps = 3)
```

---

### 4. Execution Trace Table

| Step | Current Node | Array Value | Visited Set | Nodes Added to Queue | Explanation |
| :---: | :---: | :---: | :--- | :--- | :--- |
| **0** | `0` | `100` | `{0}` | `[0]` | Start at index 0. |
| **1** | `0` | `100` | `{0, 4, 1}` | `[4, 1]` | Jump to `4` (same value `100`) and `1` (`0+1`). Delete `map[100]`. |
| **2** | `4` | `100` | `{0, 4, 1, 3, 5}` | `[1, 3, 5]` | From `4`, jump backward to `3` (`4-1`) and forward to `5` (`4+1`). |
| **2** | `1` | `-23` | `{0, 4, 1, 3, 5, 2}` | `[3, 5, 2]` | From `1`, jump to `2` (same value `-23`). Delete `map[-23]`. |
| **3** | `3` | `404` | `{0, 4, 1, 3, 5, 2, 9}` | `[5, 2, 9]` | From `3`, jump to `9` (same value `404`). **Index 9 reached!** |

**Output:** `3` steps (`0` ➔ `4` ➔ `3` ➔ `9`).

---

## 💻 Code Implementation

### Python

```python
from collections import deque, defaultdict

class Solution:
    def minJumps(self, arr: list[int]) -> int:
        n = len(arr)
        if n <= 1:
            return 0
        
        # Build mapping from value to all indices sharing that value
        value_to_indices = defaultdict(list)
        for i, val in enumerate(arr):
            value_to_indices[val].append(i)
            
        queue = deque([0])
        visited = {0}
        steps = 0
        
        while queue:
            for _ in range(len(queue)):
                curr = queue.popleft()
                
                # Check if we reached the target index
                if curr == n - 1:
                    return steps
                
                curr_val = arr[curr]
                
                # Candidate next moves
                # 1. Same-value teleportation
                if curr_val in value_to_indices:
                    for neighbor in value_to_indices[curr_val]:
                        if neighbor not in visited:
                            visited.add(neighbor)
                            queue.append(neighbor)
                    # CRITICAL: Clear map entry to prevent O(N^2) TLE
                    del value_to_indices[curr_val]
                
                # 2. Right step (i + 1)
                if curr + 1 < n and (curr + 1) not in visited:
                    visited.add(curr + 1)
                    queue.append(curr + 1)
                    
                # 3. Left step (i - 1)
                if curr - 1 >= 0 and (curr - 1) not in visited:
                    visited.add(curr - 1)
                    queue.append(curr - 1)
                    
            steps += 1
            
        return -1
```

### C++

```cpp
#include <vector>
#include <unordered_map>
#include <queue>
#include <unordered_set>

using namespace std;

class Solution {
public:
    int minJumps(vector<int>& arr) {
        int n = arr.size();
        if (n <= 1) return 0;

        unordered_map<int, vector<int>> valueToIndices;
        for (int i = 0; i < n; ++i) {
            valueToIndices[arr[i]].push_back(i);
        }

        queue<int> q;
        vector<bool> visited(n, false);

        q.push(0);
        visited[0] = true;
        int steps = 0;

        while (!q.empty()) {
            int size = q.size();
            while (size--) {
                int curr = q.front();
                q.pop();

                if (curr == n - 1) return steps;

                // 1. Same value jumps
                int currVal = arr[curr];
                if (valueToIndices.count(currVal)) {
                    for (int neighbor : valueToIndices[currVal]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            q.push(neighbor);
                        }
                    }
                    // CRITICAL: Prevent re-visiting the same value group
                    valueToIndices.erase(currVal);
                }

                // 2. Right neighbor (i + 1)
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    q.push(curr + 1);
                }

                // 3. Left neighbor (i - 1)
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    q.push(curr - 1);
                }
            }
            steps++;
        }

        return -1;
    }
};
```

### Java

```java
import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();

                if (curr == n - 1) return steps;

                int currVal = arr[curr];

                // 1. Same value jumps
                if (valueToIndices.containsKey(currVal)) {
                    for (int neighbor : valueToIndices.get(currVal)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                    // CRITICAL optimization
                    valueToIndices.remove(currVal);
                }

                // 2. Right neighbor (i + 1)
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }

                // 3. Left neighbor (i - 1)
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }
            }
            steps++;
        }

        return -1;
    }
}
```

---

## 📊 Complexity Analysis

| Measure | Complexity | Reason |
| :--- | :---: | :--- |
| **Time Complexity** | $\mathcal{O}(N)$ | Building the index map takes $\mathcal{O}(N)$. Each vertex is queued at most once, and clearing `valueToIndices[currVal]` ensures edge traversals for identical values happen only once. |
| **Space Complexity** | $\mathcal{O}(N)$ | Storing the adjacency map requires $\mathcal{O}(N)$ memory. The BFS queue and visited vector take up to $\mathcal{O}(N)$ memory. |

---

## ⚠️ Edge Cases & Crucial Optimizations

1. **Single Element Array (`n == 1`):**
    - Result is `0` because we are already at the target index `0`.
2. **All Identical Elements (`arr = [7, 7, 7, 7, 7]`):**
    - Handled in **1 step** because index `0` teleports directly to index `n - 1`.
3. **Map Deletion (`del value_to_indices[curr_val]`):**
    - Skipping map deletion causes repetitive checks over large matching value buckets, degrading performance from linear $\mathcal{O}(N)$ to quadratic $\mathcal{O}(N^2)$ and triggering TLE.
