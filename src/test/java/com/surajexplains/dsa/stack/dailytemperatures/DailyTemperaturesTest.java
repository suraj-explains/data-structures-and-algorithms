package com.surajexplains.dsa.stack.dailytemperatures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DailyTemperaturesTest {

    @Test
    void dailyTemperatures() {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] expectedResult = new int[]{4,2,1,1,1,0,0,0};
        int[] actualResult = dailyTemperatures.dailyTemperatures(new int[]{40, 30, 20, 35, 45, 50, 40, 30});

        Assertions.assertTrue(actualResult.length == 8 );
        Assertions.assertArrayEquals(expectedResult,actualResult);

    }
}