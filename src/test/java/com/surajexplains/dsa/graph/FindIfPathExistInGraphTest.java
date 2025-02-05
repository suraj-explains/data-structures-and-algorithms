package com.surajexplains.dsa.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindIfPathExistInGraphTest {

    @Test
    void shouldReturnTrueForValidPath() {

        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        Assertions.assertTrue(FindIfPathExistInGraph.isValidPath(3, edges, 0, 2));
    }

    @Test
    void shouldReturnFalseForNonValidPath() {

        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        Assertions.assertFalse(FindIfPathExistInGraph.isValidPath(3, edges, 0, 3));
    }

    @Test
    void shouldReturnTrueForValidPathInDisconnectedGraph() {

        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        Assertions.assertTrue(FindIfPathExistInGraph.isValidPath(6, edges, 3, 5));
    }

    @Test
    void shouldReturnFalseForNonValidPathInDisconnectedGraph() {

        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        Assertions.assertFalse(FindIfPathExistInGraph.isValidPath(6, edges, 0, 5));
    }
}