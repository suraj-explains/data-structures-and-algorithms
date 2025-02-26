package com.surajexplains.dsa.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class NumbersWithSameConsecutiveDiff {

    public static int[] numsSameConsecDiff(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();

        IntStream.range(1, 10)
                .forEach(queue::add);

        for (int i = 1; i < n ; i++) {

            int size = queue.size();

            for (int j = 0; j < size; j++) {
                Integer number = queue.poll();
                int lastDigit = number % 10;

                if (lastDigit + k <= 9) {
                    queue.add(number * 10 + lastDigit + k);
                }
                if (k != 0 && lastDigit - k >= 0) {
                    queue.add(number * 10 + lastDigit - k);
                }
            }

        }

        return queue.stream().mapToInt(Integer::intValue).toArray();

    }
}
