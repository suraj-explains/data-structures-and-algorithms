package com.surajexplains.dsa.google;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HappyNumberTest {

    @Test
    void shouldReturnTrueForHappyNumber() {

        Assertions.assertTrue(HappyNumber.isHappyNumberUsingHashSet(19));
    }

    @Test
    void shouldReturnFalseForNonHappyNumber() {

        Assertions.assertFalse(HappyNumber.isHappyNumberUsingHashSet(20));
    }

    @Test
    void shouldReturnTrueForHappyNumberWithoutUsingSet() {

        Assertions.assertTrue(HappyNumber.isHappyNumberWithoutUsingSet(19));
    }

    @Test
    void shouldReturnFalseForNonHappyNumberWithoutUsingSet() {

        Assertions.assertFalse(HappyNumber.isHappyNumberWithoutUsingSet(20));
    }
}