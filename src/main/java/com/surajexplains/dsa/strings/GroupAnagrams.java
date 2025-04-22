package com.surajexplains.dsa.strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAnagrams {

    private static String getBitMap(String str) {
        int[] count = new int[26];
        for (char ch : str.toCharArray()) {
            int index = ch - 'a';
            count[index]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int c : count) {
            sb.append(c).append("#");
        }
        return sb.toString();
    }

    public static List<List<String>> groupAnagramsUsingArray(String[] strs) {
        return Arrays.stream(strs)
                .collect(Collectors.groupingBy(
                        GroupAnagrams::getBitMap
                ))
                .values()
                .stream()
                .toList();
    }

    private static String sort(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static List<List<String>> groupAnagramsUsingSort(String[] strs) {

        return Arrays.stream(strs)
                .collect(Collectors.groupingBy(
                        GroupAnagrams::sort
                ))
                .values()
                .stream()
                .toList();
    }

}
