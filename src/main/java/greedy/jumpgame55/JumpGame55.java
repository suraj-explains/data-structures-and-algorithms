package greedy.jumpgame55;

public class JumpGame55 {

    public boolean canJump(int[] nums) {

        int maxReach = 0;
        int target = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            // If current position is beyond our maximum reach, we cannot proceed
            if (i > maxReach) {
                return false;
            }

            // Update maximum reachable index from current position
            maxReach = Math.max(maxReach, i + nums[i]);

            // Early exit if we can already reach or pass the final index
            if (maxReach >= target) {
                return true;
            }
        }
        // If loop completes without being stuck, we can reach the end
        return true;
    }
}
