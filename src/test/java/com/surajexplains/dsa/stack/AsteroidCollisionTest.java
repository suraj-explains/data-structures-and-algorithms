package com.surajexplains.dsa.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AsteroidCollisionTest {

    @Test
    void asteroidCollisionAllPositive() {
        int[] input = new int[]{5, 10, 5};
        int[] expectedResult = new int[]{5, 10, 5};
        int[] actualResult = AsteroidCollision.asteroidCollision(input);
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void asteroidCollisionOneSmallNegative() {
        int[] input = new int[]{5, 10, -5};
        int[] expectedResult = new int[]{5, 10};
        int[] actualResult = AsteroidCollision.asteroidCollision(input);
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void asteroidCollisionOneBigNegative() {
        int[] input = new int[]{5, 10, -15};
        int[] expectedResult = new int[]{-15};
        int[] actualResult = AsteroidCollision.asteroidCollision(input);
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void asteroidCollisionAllNegative() {
        int[] input = new int[]{-5, -10, -15};
        int[] expectedResult = new int[]{-5, -10, -15};
        int[] actualResult = AsteroidCollision.asteroidCollision(input);
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void asteroidCollisionSameMagnitude() {
        int[] input = new int[]{8, -8};
        int[] expectedResult = new int[]{};
        int[] actualResult = AsteroidCollision.asteroidCollision(input);
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

}