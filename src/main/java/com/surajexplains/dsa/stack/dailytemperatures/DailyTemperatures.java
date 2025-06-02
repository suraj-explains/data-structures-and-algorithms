package com.surajexplains.dsa.stack.dailytemperatures;

import java.util.Stack;

public class DailyTemperatures {

    //Input: [73,74,75,71,69,72,76,73]

    public int[] dailyTemperatures(int[] temperatures) {

        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int currDay = 0; currDay < temperatures.length; currDay++){

            while (!stack.isEmpty() && temperatures[currDay] > temperatures[stack.peek()]){
                int prevDay = stack.pop();
                result[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }

        return result;

    }
}
