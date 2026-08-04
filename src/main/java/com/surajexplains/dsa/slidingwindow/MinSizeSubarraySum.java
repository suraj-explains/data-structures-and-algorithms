package com.surajexplains.dsa.slidingwindow;

import java.util.Arrays;
import java.util.Map;

public class MinSizeSubarraySum {


    /*
           L R
    [2,3,1,2,4,3]. 7

     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int sum=0;

        for (int right=0;right<nums.length;right++){

            sum = sum + nums[right];

            while (sum >= target){
                minLength = Math.min(minLength,right - left+1);
                sum = sum - nums[left];
                left--;


            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;

    }
}
