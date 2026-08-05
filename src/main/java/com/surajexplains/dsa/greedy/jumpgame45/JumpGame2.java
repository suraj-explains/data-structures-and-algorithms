package com.surajexplains.dsa.greedy.jumpgame45;

public class JumpGame2 {

    public int jump(int[] nums) {
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
