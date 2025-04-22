package com.surajexplains.dsa.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GroupAnagramsTest {

    @Test
    void shouldReturnValidResultForGroupAnagramsUsingSort1() {
        String[] input = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        //Expected output : ["bat"],["nat","tan"],["ate","eat","tea"]

        List<List<String>> actualResult = GroupAnagrams.groupAnagramsUsingSort(input);

        Assertions.assertEquals(3, actualResult.size());
        Assertions.assertTrue(actualResult.contains(List.of("bat")));

    }

    @Test
    void shouldReturnValidResultForGroupAnagramsUsingSort2() {

        String[] input = new String[]{"bdddddddddd", "bbbbbbbbbbc"};
        // Expected output: [["bdddddddddd"],["bbbbbbbbbbc"]]

        List<List<String>> actualResult = GroupAnagrams.groupAnagramsUsingSort(input);

        Assertions.assertEquals(2, actualResult.size());
        Assertions.assertTrue(actualResult.contains(List.of("bdddddddddd")));
        Assertions.assertTrue(actualResult.contains(List.of("bbbbbbbbbbc")));

    }

    @Test
    void shouldReturnValidResultForGroupAnagramsUsingArray1() {
        String[] input = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        //Expected output : ["bat"],["nat","tan"],["ate","eat","tea"]

        List<List<String>> actualResult = GroupAnagrams.groupAnagramsUsingArray(input);

        Assertions.assertEquals(3, actualResult.size());
        Assertions.assertTrue(actualResult.contains(List.of("bat")));

    }

    @Test
    void shouldReturnValidResultForGroupAnagramsUsingArray2() {

        String[] input = new String[]{"bdddddddddd", "bbbbbbbbbbc"};
        // Expected output: [["bdddddddddd"],["bbbbbbbbbbc"]]

        List<List<String>> actualResult = GroupAnagrams.groupAnagramsUsingArray(input);

        Assertions.assertEquals(2, actualResult.size());
        Assertions.assertTrue(actualResult.contains(List.of("bdddddddddd")));
        Assertions.assertTrue(actualResult.contains(List.of("bbbbbbbbbbc")));

    }
}