package com.surajexplains.dsa.Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsAnagram {

    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()){
            return false;
        }

        Map<Character,Integer> freq = new HashMap<>();
        for (char ch: s.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch,0)+1);
        }

        for (int i=0;i<t.length();i++){
            char c = t.charAt(i);
            if (!freq.containsKey(c)){
                return false;
            }

            if (freq.get(c) < 0){
                return false;
            }
            freq.put(c,freq.get(c) -1);

        }

        return false;

    }






    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String,List<String>> map = new HashMap<>();

        for (String str: strs){

            String key = getBitMap(str);

            map.computeIfAbsent(key,(k)->new ArrayList<>()).add(str);


        }

        return new ArrayList<>(map.values());
    }

    private String getBitMap(String word){
        int[] freq = new int[26];

        for (int i=0;i<word.length();i++){
            int index = word.charAt(i) - 'a';
            freq[index]++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i =0;i< freq.length;i++){
            sb.append(freq[i]).append("#");
        }

        return sb.toString();
    }
}
