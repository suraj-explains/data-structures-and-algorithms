package com.surajexplains.dsa.stack;

import java.util.Stack;

public class AsteroidCollision {


    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {

            if (asteroid > 0) {
                stack.push(asteroid);
            } else {

                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)) {
                    stack.pop();
                }

                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                }

                if (stack.peek() == Math.abs(asteroid)) {
                    stack.pop();
                }
            }
        }
        return stack.stream().mapToInt(n -> n).toArray();
    }
}
