# 🚀 Jump Game II — Detailed Solution & Visual Walkthrough

Welcome to the comprehensive guide for solving **Jump Game II** (LeetCode 45)! This document provides an intuitive explanation, step-by-step visual execution trace, code analysis, and complexity breakdown.

---

## 📌 Problem Overview

Given an array of non-negative integers `nums`, you are initially positioned at the **first index** (`nums[0]`).

Each element in the array represents your **maximum jump length** at that position.

> **Goal:** Return the **minimum number of jumps** required to reach the last index (`nums.length - 1`). You are guaranteed that you can always reach the last index.

---

## 💡 Key Intuition: The Greedy / BFS Level Strategy

Think of this problem like a **Breadth-First Search (BFS)**, where each jump level defines a range (window) of reachable indices:

1. **Current Jump Range (`currentEnd`):** The farthest index you can reach with your *current* number of jumps.
2. **Farthest Reach (`farthest`):** The maximum index you can reach in the *next* jump by considering all positions within the current range.
3. **Triggering a Jump:** As you iterate through the array, you constantly update `farthest`. When your scanner `i` hits `currentEnd`, it means you have explored all options for the current jump level. You **must jump**, incrementing `jumps`, and update `currentEnd = farthest`.

---


## 🔍 Step-by-Step Visual Execution Walkthrough

Let's trace the algorithm on the example array:
$$\text{nums} = [2, 3, 1, 1, 4]$$

* **Array Length ($N$):** $5$
* **Target Index:** $4$ (Value: $4$)
* **Loop Boundary:** `i` runs from `0` to `3` (excluding last index $4$).

---

### 🟢 Initial State
* `jumps = 0`
* `currentEnd = 0`
* `farthest = 0`

```
  Index:     0    1    2    3    4
  Values:  [ 2,   3,   1,   1,   4 ]
             ▲
          Start (i=0)
          currentEnd = 0
```

---

### 📍 Step 1: `i = 0` (Value = `2`)
* **Calculate potential reach:** `i + nums[i] = 0 + 2 = 2`
* `farthest = max(0, 2) = 2`
* **Condition check (`i == currentEnd`):** `0 == 0` → **TRUE**
    * `jumps = jumps + 1 = 1`
    * `currentEnd = farthest = 2`
    * **Early exit check:** `currentEnd (2) >= 4`? **No.**

```
  Index:     0    1    2    3    4
  Values:  [ 2,   3,   1,   1,   4 ]
            |--------|
             Jump 1 Window (Reach up to index 2)

  State after Step 1:
  - jumps: 1
  - currentEnd: 2
  - farthest: 2
```

---

### 📍 Step 2: `i = 1` (Value = `3`)
* **Calculate potential reach:** `i + nums[i] = 1 + 3 = 4`
* `farthest = max(2, 4) = 4`
* **Condition check (`i == currentEnd`):** `1 == 2` → **FALSE**
    * (We are still exploring within Jump 1's boundary.)

```
  Index:     0    1    2    3    4
  Values:  [ 2,   3,   1,   1,   4 ]
                  ▲
                i = 1
  Potential Jump 2 can reach index 4!

  State after Step 2:
  - jumps: 1
  - currentEnd: 2
  - farthest: 4
```

---

### 📍 Step 3: `i = 2` (Value = `1`)
* **Calculate potential reach:** `i + nums[i] = 2 + 1 = 3`
* `farthest = max(4, 3) = 4`
* **Condition check (`i == currentEnd`):** `2 == 2` → **TRUE**
    * We've reached the boundary of Jump 1. We MUST make Jump 2!
    * `jumps = jumps + 1 = 2`
    * `currentEnd = farthest = 4`
    * **Early exit check:** `currentEnd (4) >= 4`? **YES!** 🎉
    * **BREAK LOOP!**

```
  Index:     0    1    2    3    4
  Values:  [ 2,   3,   1,   1,   4 ]
            |--------|-------------|
             Jump 1    Jump 2 (Reached Target!)

  Final Result: jumps = 2
```

## 🛠️ Java Implementation

```java
public class JumpGame2 {

    public int jump(int[] nums) {
        // Handle base case: array with single element requires 0 jumps
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // We don't need to process the last element (nums.length - 1)
        // because once we reach or pass it, we are already at the destination.
        for (int i = 0; i < nums.length - 1; i++) {
            // Track the maximum reachable index from current position
            farthest = Math.max(farthest, i + nums[i]);

            // Reached the end of the current jump level
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit: destination is reachable
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        return jumps;
    }
}
```
---

## 📊 Summary Execution Table

| `i` | `nums[i]` | `i + nums[i]` | `farthest` | `currentEnd` | `i == currentEnd`? | `jumps` | Action / Notes |
|:---:|:---------:|:-------------:|:----------:|:------------:|:------------------:|:-------:|:---|
| **Init** | - | - | `0` | `0` | - | `0` | Initial state |
| **0** | `2` | $0+2 = 2$ | `2` | `2` | **Yes** | **1** | Expanded range to index 2 |
| **1** | `3` | $1+3 = 4$ | `4` | `2` | No | `1` | Discovered path to index 4 |
| **2** | `1` | $2+1 = 3$ | `4` | `4` | **Yes** | **2** | Target reachable $
ightarrow$ **Break** |

---

## ⚡ Key Code Nuances Explained

1. **Why loop up to `nums.length - 1` instead of `nums.length`?**
    * If we processed the last element, reaching `i == currentEnd` at the final index would trigger an unnecessary additional jump counter increment even though we are already at the destination.

2. **Why the Early Exit check?**
    * As soon as `currentEnd >= nums.length - 1`, we know that the current jump level reaches or exceeds the destination. Exiting early avoids redundant calculations for remaining indices.

3. **Greedy Choice Property:**
    * By deferred decision-making (collecting `farthest` across the entire `currentEnd` interval before committing to the next jump boundary), we guarantee finding the global minimum number of jumps.

---

## ⏱️ Complexity Analysis

| Metric | Complexity | Explanation |
|:---|:---:|:---|
| **Time Complexity** | $\mathcal{O}(N)$ | We perform a single pass over the array of size $N$. |
| **Space Complexity** | $\mathcal{O}(1)$ | Uses a constant amount of auxiliary variables (`jumps`, `currentEnd`, `farthest`). |

---

## 🧪 Edge Cases Handled

* **Single Element Array (`[0]` or `[5]`):** Returns `0` jumps immediately because loop condition `i < nums.length - 1` ($0 < 0$) is false.
* **Large Jump at Start (`[5, 1, 1, 1, 1]`):** `currentEnd` immediately covers the whole array on step `0`, breaking early with `jumps = 1`.
* **Step-by-step Jumps (`[1, 1, 1, 1]`):** Incrementally advances `currentEnd` by 1 unit per iteration.