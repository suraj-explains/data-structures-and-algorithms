# LeetCode 55: Jump Game

## 📌 Problem Overview
Given an array of non-negative integers `nums`, you are initially positioned at the **first index** (index `0`). Each element in the array represents your **maximum jump length** at that position.

Return `true` if you can reach the last index, or `false` otherwise.

---

## 💡 Key Intuition: Greedy Pattern (`maxReach`)

Instead of using Backtracking ($O(2^N)$) or Dynamic Programming ($O(N^2)$), we solve this using a **Greedy Approach** in $\mathcal{O}(N)$ time and $\mathcal{O}(1)$ space.

### Core Logic:
* Maintain a variable `maxReach` to track the **furthest reachable index** seen so far.
* Loop through the array from left to right:
    1. **Barrier Check:** If current index `i > maxReach`, you've hit an unreachable index (stuck at a `0`). Return `false`.
    2. **Expand Boundary:** Greedily update `maxReach = max(maxReach, i + nums[i])`.
    3. **Early Exit Guard:** If `maxReach >= target`, you can reach the last index. Return `true` immediately.

---

## 🎨 Visual Walkthrough

### Example 1: Success Case `nums = [2, 3, 1, 1, 4]`
* `target = 4` (last index)

```text
Step 0: Start at Index 0
  [ 2 ]   3     1     1     4
    ^
  i = 0 | nums[0] = 2  ==>  maxReach = max(0, 0 + 2) = 2

Step 1: Move to Index 1
    2   [ 3 ]   1     1     4
          ^
  i = 1 | nums[1] = 3  ==>  maxReach = max(2, 1 + 3) = 4
                           4 >= target (4)  ==>  SUCCESS! Return true

```
### Example 2: Failure Case `nums = [3, 2, 1, 0, 4]`
* `target = 4` (last index)

```text
Step 0: Start at Index 0
  [ 3 ]   2     1     0     4
    ^
  i = 0 | nums[0] = 3  ==>  maxReach = max(0, 0 + 3) = 3

Step 1: Move to Index 1
    3   [ 2 ]   1     0     4
          ^
  i = 1 | nums[1] = 2  ==>  maxReach = max(3, 1 + 2) = 3

Step 2: Move to Index 2
    3     2   [ 1 ]   0     4
                ^
  i = 2 | nums[2] = 1  ==>  maxReach = max(3, 2 + 1) = 3

Step 3: Move to Index 3
    3     2     1   [ 0 ]   4
                      ^
  i = 3 | nums[3] = 0  ==>  maxReach = max(3, 3 + 0) = 3

Step 4: Attempt Index 4
    3     2     1     0   [ 4 ]
                            ^
  i = 4 | maxReach = 3  ==>  i > maxReach (4 > 3)  ==>  TRAPPED! Return false.
