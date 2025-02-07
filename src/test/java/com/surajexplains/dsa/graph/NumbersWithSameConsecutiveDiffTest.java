package com.surajexplains.dsa.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class NumbersWithSameConsecutiveDiffTest {

    @Test
    void shouldReturnNumbersWithSameConsecutiveDiffOfOne() {

        int[] expected = new int[]{10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98};

        int[] result = NumbersWithSameConsecutiveDiff.numsSameConsecDiff(2, 1);

        List<Integer> resultList = Arrays.stream(result).boxed().toList();

        Assertions.assertEquals(expected.length, result.length);

        Arrays.stream(expected).forEach(r ->
                Assertions.assertTrue(resultList.contains(r))
        );
    }

    @Test
    void shouldReturnNumbersWithSameConsecutiveDiffOfSeven() {

        int[] expected = new int[]{181, 292, 707, 818, 929};

        int[] result = NumbersWithSameConsecutiveDiff.numsSameConsecDiff(3, 7);
        List<Integer> resultList = Arrays.stream(result).boxed().toList();

        Assertions.assertEquals(expected.length, result.length);

        Arrays.stream(expected).forEach(r ->
                Assertions.assertTrue(resultList.contains(r))
        );
    }

    @Test
    void shouldReturnNumbersWithSameConsecutiveDiffOfZero() {

        int[] expected = new int[]{11, 22, 33, 44, 55, 66, 77, 88, 99};

        int[] result = NumbersWithSameConsecutiveDiff.numsSameConsecDiff(2, 0);

        List<Integer> resultList = Arrays.stream(result).boxed().toList();

        Assertions.assertEquals(expected.length, result.length);

        Arrays.stream(expected).forEach(r ->
                Assertions.assertTrue(resultList.contains(r))
        );
    }
}