package com.surajexplains.dsa.greedy.jumpgame1306;

import java.util.ArrayDeque;
import java.util.Deque;

public class JumpGame3 {

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
}
