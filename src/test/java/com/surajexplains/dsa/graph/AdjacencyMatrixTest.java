package com.surajexplains.dsa.graph;

import org.junit.jupiter.api.Test;

class AdjacencyMatrixTest {

    @Test
    void bfs() {
        int[][] graph = {
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };
        AdjacencyMatrix.bfs(graph);
    }
}
