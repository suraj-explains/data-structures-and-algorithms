package com.surajexplains.dsa.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {


    public int lengthOfLongestSubstringBruteForce(String s) {
        int maxLength = 0;

        for (int i=0;i < s.length();i++ ){
            Set<Character> set = new HashSet<>();

            for (int j=i; j< s.length();j++){
                if (set.contains(s.charAt(j))){
                    break;
                }
                set.add(s.charAt(j));
                int lenOfCurrSubString = set.size();
                maxLength = Math.max(maxLength,lenOfCurrSubString);
            }
        }
        return maxLength;
    }


    public int lengthOfLongestSubstringOptimal(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (charIndexMap.containsKey(currentChar)) {
             left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }
            charIndexMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
