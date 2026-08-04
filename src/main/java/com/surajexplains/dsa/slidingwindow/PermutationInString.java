package com.surajexplains.dsa.slidingwindow;

import java.util.Arrays;

public class PermutationInString {
  /*
                           j
  Input: s1 = "ab", s2 = "eidbaooo"
Output: true.             01234567
Explanation: s2 contains one permutation of s1 ("ba").

   */
    public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()){
                return false;
            }

            int[] s1Count = new int[26];
            int[] s2Count = new int[26];

            for (int i=0;i<s1.length();i++){
                int index = s1.charAt(i) - 'a';
                s1Count[index]++;
                s2Count[s2.charAt(i)-'a']++;
            }

            for (int j=0;j< s2.length() - s1.length();j++){
                if (Arrays.equals(s1Count,s2Count)){
                    return true;
                }
                s2Count[s2.charAt(j + s2.length()) - 'a']++;
                s2Count[s2.charAt(j) -'a']--;
            }

        if (Arrays.equals(s1Count,s2Count)){
            return true;
        }

        return false;
    }
}
