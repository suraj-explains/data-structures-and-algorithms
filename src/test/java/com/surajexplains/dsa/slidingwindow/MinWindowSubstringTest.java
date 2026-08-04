package com.surajexplains.dsa.slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinWindowSubstringTest {

    @Test
    void minWindow() {

        String s = "a";
        String t = "a";

        String result = MinWindowSubstring.minWindow(s, t);
        Assertions.assertEquals("a",result);
    }

    @Test
    void testMinWindowsPositive(){
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String expected = "BANC";

        String result = MinWindowSubstring.minWindow(s, t);

        Assertions.assertEquals(expected,result);
    }
}