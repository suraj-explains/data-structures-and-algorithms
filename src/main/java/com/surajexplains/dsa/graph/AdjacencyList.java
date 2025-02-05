package com.surajexplains.dsa.graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AdjacencyList {

    public static void bfs(Map<Character, List<Character>> graph, Character src) {
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        queue.add(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            Character node = queue.poll();

            if (node != null) {
                System.out.println("Node is : " + node);

                List<Character> neighbours = graph.get(node);

                for (Character neighbour : neighbours) {
                    if (!visited.contains(neighbour)) {
                        queue.add(neighbour);
                        visited.add(neighbour);
                    }

                }

            }
        }

    }
}
