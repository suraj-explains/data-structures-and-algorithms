# LeetCode 1091: Shortest Path in Binary Matrix

## 1. Problem Statement

Given an $n \times n$ binary matrix `grid`, return the **length of the shortest clear path** in the matrix. If no clear path exists, return `-1`.

A **clear path** is a path from the top-left cell `(0, 0)` to the bottom-right cell `(n - 1, n - 1)` such that:
- All visited cells in the path have a value of `0`.
- All adjacent cells in the path are **8-directionally connected** (i.e., horizontally, vertically, or diagonally adjacent).
- The **length** of a clear path is the total number of cells visited along the path (including the starting and ending cells).

---

## 2. Key Insights & Approach Selection

### Why Breadth-First Search (BFS)?
1. **Unweighted Shortest Path Guarantee**: The grid can be modeled as an unweighted graph where every valid movement between adjacent 0-cells has a edge weight of `1`.
2. **Breadth-First Search (BFS)** naturally explores the grid layer-by-layer (radiating outward from the source cell `(0, 0)`). The first time the algorithm reaches the target cell `(n - 1, n - 1)`, the distance traversed is guaranteed to be the absolute minimum.
3. **Depth-First Search (DFS)** would explore paths to completion down deep branches before backtracking, which requires searching all paths to prove minimality, leading to exponential time complexity ($O(8^{n^2})$) without heavy memoization/Dijkstra logic.

### Movement Rules (8 Directions)
Unlike standard grid problems with 4-directional movement (Up, Down, Left, Right), this problem allows **8-directional movement**:
- **Horizontal/Vertical**: `(-1, 0)`, `(1, 0)`, `(0, -1)`, `(0, 1)`
- **Diagonal**: `(-1, -1)`, `(-1, 1)`, `(1, -1)`, `(1, 1)`

---

## 2. 🚀 Step-by-Step Algorithm Explanation

### Step 1: Input Validation & Base Case Checks
1. **Grid Dimension Extraction:** Get the grid size `n = grid.length`.
2. **Start / Destination Obstacle Check:** Check if the start cell `grid[0][0]` or the destination cell `grid[n - 1][n - 1]` is blocked (`!= 0`). If either cell is `1`, immediately return `-1` (no valid path exists).
3. **Single Cell Grid Check:** If $n = 1$ and `grid[0][0] == 0`, the start is already the destination, so return `1`.

---

### Step 2: BFS Initialization
1. **Define 8 Directional Vectors:**
   Create a list of coordinate offsets representing all 8 possible moves from any cell `(r, c)`:
    - Horizontal & Vertical: `{-1, 0}`, `{1, 0}`, `{0, -1}`, `{0, 1}`
    - Diagonals: `{-1, -1}`, `{-1, 1}`, `{1, -1}`, `{1, 1}`
2. **Queue Setup:** Initialize a First-In-First-Out (FIFO) queue (`ArrayDeque`) to hold coordinate pairs `[r, c]`.
3. **Enqueue Starting Cell:**
    - Push `[0, 0]` into the queue.
    - Mark `grid[0][0] = 1` immediately to prevent re-visiting the start node.
4. **Path Counter:** Initialize `pathLength = 1`.

---

### Step 3: Level-by-Level BFS Traversal
While the queue is **not empty**:

1. **Capture Level Size:** Determine `levelSize = queue.size()`. This represents the exact number of nodes at the current distance layer.
2. **Process Current Level Nodes:** Loop `levelSize` times:
    - **Dequeue:** Poll the front element `[r, c]` from the queue.
    - **Destination Target Check:** If `r == n - 1` and `c == n - 1`, return `pathLength` immediately. (Guaranteed to be the shortest path because BFS explores node-by-node layerwise).
    - **Explore 8 Directions:** For each directional offset `[dr, dc]`:
        - Calculate neighbor coordinates: `nextR = r + dr`, `nextC = c + dc`.
        - **Validity Check:**
            - `0 <= nextR < n` (within row bounds)
            - `0 <= nextC < n` (within column bounds)
            - `grid[nextR][nextC] == 0` (unvisited open cell)
        - **Enqueue & Mark Visited:**
            - If valid, offer `[nextR, nextC]` to the queue.
            - Set `grid[nextR][nextC] = 1` immediately upon enqueueing to prevent duplicate additions.
3. **Increment Path Length:** After processing all nodes at the current level, increment `pathLength++`.

---

### Step 4: Return Unreachable Result
If the queue becomes empty and the destination target `(n - 1, n - 1)` was never reached, return `-1`.

---


## 4. Step-by-Step Visualization

### Example Grid
Consider the following $3 \times 3$ grid:

```text
Grid Indexing:
 (0,0) (0,1) (0,2)
 (1,0) (1,1) (1,2)
 (2,0) (2,1) (2,2)

Grid Values:
 [ 0,  1,  0 ]
 [ 1,  0,  0 ]
 [ 1,  1,  0 ]
```

### Trace Walkthrough

#### **Initial Checks**
- Start cell `grid[0][0]` is `0` $\rightarrow$ Valid start.
- End cell `grid[2][2]` is `0` $\rightarrow$ Valid goal.

#### **Step 1: Initialization**
- Mark `grid[0][0]` as visited by changing its value to `1` (or using a separate `visited` array).
- Push `(0, 0)` to the queue with path length `1`.
- **Queue**: `[ (0, 0, pathLength = 1) ]`

```text
Distance Visualization:
 [ 1,  . ,  . ]
 [ . , . ,  . ]
 [ . , . ,  . ]
```

---

#### **Step 2: Process Cell (0, 0)**
- Pop `(0, 0)` (Path Length = 1).
- Explore 8 neighbors from `(0, 0)`:
    - `(-1, -1)` $\rightarrow$ Out of bounds
    - `(-1, 0)`  $\rightarrow$ Out of bounds
    - `(-1, 1)`  $\rightarrow$ Out of bounds
    - `(0, -1)`  $\rightarrow$ Out of bounds
    - `(0, 1)`   $\rightarrow$ Value is `1` (Blocked)
    - `(1, -1)`  $\rightarrow$ Out of bounds
    - `(1, 0)`   $\rightarrow$ Value is `1` (Blocked)
    - **`(1, 1)`**  $\rightarrow$ Value is `0` (Valid diagonal step!)
- Action: Mark `grid[1][1] = 1`, enqueue `(1, 1)` with `pathLength = 2`.
- **Queue**: `[ (1, 1, pathLength = 2) ]`

```text
Distance Visualization:
 [ 1,  X ,  . ]
 [ X , 2 ,  . ]
 [ . , . ,  . ]
```

---

#### **Step 3: Process Cell (1, 1)**
- Pop `(1, 1)` (Path Length = 2).
- Explore 8 neighbors from `(1, 1)`:
    - `(0, 0)` $\rightarrow$ Already visited (`1`)
    - `(0, 1)` $\rightarrow$ Blocked (`1`)
    - **`(0, 2)`** $\rightarrow$ Value is `0` $\rightarrow$ Enqueue `(0, 2)` with `pathLength = 3`
    - `(1, 0)` $\rightarrow$ Blocked (`1`)
    - **`(1, 2)`** $\rightarrow$ Value is `0` $\rightarrow$ Enqueue `(1, 2)` with `pathLength = 3`
    - `(2, 0)` $\rightarrow$ Blocked (`1`)
    - `(2, 1)` $\rightarrow$ Blocked (`1`)
    - **`(2, 2)`** $\rightarrow$ Value is `0` $\rightarrow$ Enqueue `(2, 2)` with `pathLength = 3`
- Action: Mark visited cells and add to Queue.
- **Queue**: `[ (0, 2, len=3), (1, 2, len=3), (2, 2, len=3) ]`

```text
Distance Visualization:
 [ 1,  X ,  3 ]
 [ X , 2 ,  3 ]
 [ X , X ,  3 ]
```

---

#### **Step 4: Target Reached!**
- Next popped element is `(0, 2)` (or checking neighbors at insertion).
- When target `(2, 2)` was discovered/popped at `pathLength = 3`, we immediately return **`3`**.

---

## 5. Java Implementation

```java
public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;

    // Base case: Start or destination is blocked
    if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
        return -1;
    }

    // Special base case: 1x1 grid
    if (n == 1) {
        return 1;
    }

    // 8 possible directional moves (rowOffset, colOffset)
    int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    Queue<int[]> queue = new ArrayDeque<>();

    // Add start node and mark visited
    queue.offer(new int[]{0, 0});
    grid[0][0] = 1;

    int pathLength = 1;

    // Level-by-level BFS loop
    while (!queue.isEmpty()) {
        int levelSize = queue.size();

        for (int i = 0; i < levelSize; i++) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            // Reach destination
            if (r == n - 1 && c == n - 1) {
                return pathLength;
            }

            // Explore 8 directions
            for (int[] dir : directions) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];

                // Check boundaries and valid open cell
                if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n && grid[nextR][nextC] == 0) {
                    queue.offer(new int[]{nextR, nextC});
                    grid[nextR][nextC] = 1; // Mark as visited upon enqueue
                }
            }
        }
        pathLength++;
    }

    return -1; // Path not found
}
```

---

## 6. Complexity Analysis

### Time Complexity: $\mathcal{O}(N^2)$
- **Vertices ($V$)**: In an $N \times N$ matrix, there are $V = N^2$ total cells.
- **Edges ($E$)**: Each cell has at most 8 directions, so total edges $E \le 8 N^2 = \mathcal{O}(N^2)$.
- **BFS Processing**: Every cell is added to the queue at most **once** because it is marked as visited immediately upon insertion.
- **Total Time**: $\mathcal{O}(V + E) = \mathcal{O}(N^2)$.

### Space Complexity: $\mathcal{O}(N^2)$
- **Queue Memory**: In the worst-case scenario (e.g., spiral or wide open grid), the BFS queue can hold up to one entire boundary/layer of cells, which is proportional to the number of cells in the perimeter/layer $\mathcal{O}(N^2)$.
- **Auxiliary Space**: By modifying the input `grid` in-place to track visited cells (`grid[r][c] = 1`), we avoid allocating a separate `boolean[][] visited` array. If input mutation is not allowed, an explicit $N \times N$ boolean array would add $\mathcal{O}(N^2)$ space.
- **Total Extra Space**: $\mathcal{O}(N^2)$.

---

## 7. Edge Cases & Corner Cases

| Scenario | Grid Example | Expected Output | Explanation |
| :--- | :--- | :---: | :--- |
| **Start Blocked** | `[[1, 0], [0, 0]]` | `-1` | Cannot leave starting cell `(0,0)`. |
| **End Blocked** | `[[0, 0], [0, 1]]` | `-1` | Cannot enter destination cell `(n-1, n-1)`. |
| **$1 \times 1$ Matrix (Valid)** | `[[0]]` | `1` | Start cell is the goal cell; path length is 1. |
| **$1 \times 1$ Matrix (Blocked)** | `[[1]]` | `-1` | Goal cell is blocked. |
| **No Path / Island** | `[[0, 1], [1, 0]]` | `-1` | No 8-directional zero path connecting start to end. |
| **Diagonal Obstacles** | `[[0, 1], [0, 0]]` | `2` | Path `(0,0) -> (1,1)` length is 2. |
