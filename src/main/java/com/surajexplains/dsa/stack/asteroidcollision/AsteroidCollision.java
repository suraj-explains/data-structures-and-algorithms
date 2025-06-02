package com.surajexplains.dsa.stack.asteroidcollision;

import java.util.Stack;

public class AsteroidCollision {


    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> result = new Stack<>();

        for (int a: asteroids){

            if (a > 0){
                result.push(a);
            }
            else {
                while(!result.isEmpty() && Math.abs(a) > result.peek() && result.peek() > 0){
                    result.pop();
                }

                if (result.isEmpty() || result.peek() < 0){
                    result.push(a);
                }

                if (!result.isEmpty()  && Math.abs(a) == result.peek()){
                    result.pop();
                }
            }
        }

        return result.stream().mapToInt(a->a).toArray();

    }
}
