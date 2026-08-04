package com.surajexplains.dsa.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximalSquareTest {

    @Test
    void maximalSquare() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1'}
        };
        int result = MaximalSquare.maximalSquare(grid);

        Assertions.assertEquals(16,result);

    }
}