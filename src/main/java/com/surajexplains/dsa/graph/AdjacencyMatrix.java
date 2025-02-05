package com.surajexplains.dsa.graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AdjacencyMatrix {
    /*
        0   1   2   3
    0   0   1   1   0
    1   1   0   0   1
    2   1   0   0   0
    3   0   1   0   0

                0
            1       2

            3

     */

    public static void bfs(int[][] graph) {

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            Integer value = queue.poll();
            if (value != null) {

                System.out.println("Node: " + value);

                for (int i = 0; i < graph[value].length; i++) {
                    if (graph[value][i] == 1 && !visited.contains(i)) {
                        queue.add(i);
                        visited.add(i);
                    }
                }

            }
        }
    }
}
