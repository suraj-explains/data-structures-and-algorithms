package com.surajexplains.dsa.google;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public static boolean isHappyNumberUsingHashSet(int n) {
        Set<Integer> occurred = new HashSet<>();

        while (true) {

            n = next(n);
            if (n == 1)
                return true;

            if (occurred.contains(n)) {
                return false;
            } else {
                occurred.add(n);
            }
        }

    }
    /*
    19 =
    1^2 + 9^2 = 1 + 81 = 82
    64 + 4 = 68
    36 + 64 = 100
    1

     */

    public static boolean isHappyNumberWithoutUsingSet(int n) {
        int slowPointer = n;
        int fastPointer = next(n);

        while (slowPointer != fastPointer) {
            slowPointer = next(slowPointer);
            fastPointer = next(next(fastPointer));
        }
        return slowPointer == 1;
    }

    private static int next(int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + ((n % 10) * (n % 10));
            n = n / 10;
        }
        return sum;
    }
}
