package com.surajexplains.dsa.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberOfIslandsTest {

    @Test
    void shouldReturnOneForOneIslandInGrid() {

        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Assertions.assertEquals(1, NumberOfIslands.numIslandsUsingBFS(grid));
    }

    @Test
    void shouldReturnThreeForThreeIslandsInGrid() {

        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Assertions.assertEquals(3, NumberOfIslands.numIslandsUsingBFS(grid));
    }

    @Test
    void shouldReturnZeroForZeroIslandsInGrid() {

        char[][] grid = new char[][]{
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Assertions.assertEquals(0, NumberOfIslands.numIslandsUsingBFS(grid));
    }
}