package com.surajexplains.dsa.greedy.jumpgame1345;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class JumpGame4 {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        // Map values to all their indices for same-value teleports
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        // Start BFS from index 0
        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                // Target reached!
                if (curr == n - 1) {
                    return steps;
                }

                List<Integer> neighbors = map.get(arr[curr]);

                // Option 1 & 2: Adjacent jumps (i + 1, i - 1)
                if (curr + 1 < n) neighbors.add(curr + 1);
                if (curr - 1 >= 0) neighbors.add(curr - 1);

                // Explore all valid neighbors
                for (int next : neighbors) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }

                // CRITICAL OPTIMIZATION:
                // Clear the same-value list to avoid redundant loops
                neighbors.clear();
            }
            steps++;
        }

        return -1;
    }
}
