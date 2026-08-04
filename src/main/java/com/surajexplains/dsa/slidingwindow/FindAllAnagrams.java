package com.surajexplains.dsa.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindAllAnagrams {


    public static List<Integer> findAnagramsSet(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        Set<Integer[]> set = new HashSet<>();

        Integer pCount[] = new Integer[26];
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
        }

        set.add(pCount);
        int right = p.length() - 1;
        int left = 0;

        while(right < s.length()){

            Integer count[] = new Integer[26];

            while (left <= right){
                count[s.charAt(left) - 'a']++;
            }

            if (set.contains(count)){
                result.add(left);
            }


            right++;
            left++;
        }

        return result;
    }




    /*

    Input: s = "cbaebabacd", p = "abc"
    Output: [0,6]
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s.length() < p.length() ){
            return result;
        }
        int pFreq[] = new int[26];
        int sFreq[] = new int[26];

        for (int i=0;i<p.length();i++){
            pFreq[p.charAt(i) - 'a']++;
            sFreq[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(pFreq, sFreq)){
            result.add(0);
        }

        /*
                 l. r
    Input: s = "cbaebabacd", p = "abc"
                0123456789
    Output: [0,6]
     */
        for (int right = p.length(); right < s.length();right++){
            int left = right - p.length();

            sFreq[s.charAt(left) - 'a']--;
            sFreq[s.charAt(right) - 'a']++;

            if (Arrays.equals(pFreq, sFreq)){
                result.add(left +1);
            }


        }

        return result;

    }
}
