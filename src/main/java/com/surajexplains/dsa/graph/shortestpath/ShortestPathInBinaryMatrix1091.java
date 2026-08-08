package com.surajexplains.dsa.graph.shortestpath;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix1091 {
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
}
