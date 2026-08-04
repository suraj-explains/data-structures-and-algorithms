package com.surajexplains.dsa.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MinWindowSubstring{

    /*
    Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
     */
    public static String minWindow(String s, String t){

        if (s.length() < t.length()){
            return "";
        }

        Map<Character,Integer> targetMap = new HashMap<>();
        Map<Character,Integer> windowMap = new HashMap<>();

        for (char c: t.toCharArray()){
            targetMap.put(c, targetMap.getOrDefault(c,0)+1);
        }

        int left = 0;
        int right = 0;
        int formed = 0;
        int required = targetMap.size();
        int result[] = {-1,0,0};

        while (right < s.length()){

            char c = s.charAt(right);

            windowMap.put(c,windowMap.getOrDefault(c,0)+1);

            if (targetMap.containsKey(c) && targetMap.get(c).equals(windowMap.get(c))){
                formed++;
            }

            while (left<=right && formed==required){

                if (result[0]== -1 || (right -left + 1) < result[0] ){
                    result[0] = right -left +1;
                    result[1] = left;
                    result[2] = right;
                }


                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.getOrDefault(leftChar,1)-1);

                if (targetMap.containsKey(leftChar) && windowMap.get(leftChar) < targetMap.get(leftChar)){
                    formed--;
                }
                left ++;
            }
            right++;
        }

        return result[0] == -1 ? "" : s.substring(result[1], result[2]+1);
    }

    /*
    Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
     */

    public static String minWindow1(String s, String t) {

        int[] counts = new int[128];

        for (int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            counts[ch]++;
        }

        int right = 0;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int totalNeeded = t.length();

        while (right < s.length()){

            char rightChar = s.charAt(right);

            if (counts[rightChar] > 0){
                totalNeeded--;
            }

            counts[rightChar]--;

            while (totalNeeded==0){
                int winLen = right -left +1;
                if (winLen < minLen){
                    minLen = winLen;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                counts[leftChar]++;

                if (counts[leftChar] > 0){
                    totalNeeded ++;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "": s.substring(minStart,minStart + minLen);
    }
    public static void main(String[] args) {
        minWindow1("ADOBECODEBANC","ABC");
    }


}



















/*
class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Map to store the frequency of characters in string t
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // Map to store the frequency of characters in the current window of s
        Map<Character, Integer> windowMap = new HashMap<>();

        int left = 0;
        int right = 0;

        // 'required' is the number of unique characters in t that need to be present in the window
        int required = targetMap.size();
        // 'formed' tracks how many unique characters meet their target frequency in the current window
        int formed = 0;

        // Array to store the metrics of the best window: {window_length, left_index, right_index}
        int[] ans = {-1, 0, 0};

        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // If the current character's frequency matches its target frequency in t, increment 'formed'
            if (targetMap.containsKey(c) && windowMap.get(c).equals(targetMap.get(c))) {
                formed++;
            }

            // Try to contract the window from the left while it remains valid
            while (left <= right && formed == required) {
                c = s.charAt(left);

                // Update our minimal window tracking
                if (ans[0] == -1 || (right - left + 1) < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // The character at the 'left' pointer is no longer part of the window
                windowMap.put(c, windowMap.get(c) - 1);

                // If removing this character breaks the validity condition, decrement 'formed'
                if (targetMap.containsKey(c) && windowMap.get(c) < targetMap.get(c)) {
                    formed--;
                }

                left++; // Shrink the window
            }

            right++; // Expand the window
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
*/
