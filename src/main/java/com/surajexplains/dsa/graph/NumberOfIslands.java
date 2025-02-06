package com.surajexplains.dsa.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    public static int numIslands(char[][] grid) {
        int numOfIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    bfs(grid, i, j, rows, cols);
                }
            }
        }

        return numOfIslands;
    }

    private static void bfs(char[][] grid, int row, int col, int rows, int cols) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{row, col});

        grid[row][col] = '0';

        while (!queue.isEmpty()) {

            int[] currentPosition = queue.poll();
            int currentRow = currentPosition[0];
            int currentCol = currentPosition[1];


            int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int[] dimension : directions) {
                int newRow = currentRow + dimension[0];
                int newCol = currentCol + dimension[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] != '0') {
                    queue.add(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0';
                }

            }

        }

    }
}
