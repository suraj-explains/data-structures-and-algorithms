package com.surajexplains.dsa.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FindIfPathExistInGraph{

    public static  boolean isValidPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge: edges){
            graph.computeIfAbsent(edge[0],k->new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1],k->new ArrayList<>()).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()){
            Integer vertex = queue.poll();

            if (vertex == destination){
                return true;
            }

            List<Integer> neighbours = graph.get(vertex);

            for (Integer neighbour: neighbours){
                if (!visited.contains(neighbour)){
                    queue.add(neighbour);
                    visited.add(neighbour);
                }

            }
        }

        return false;
    }
}
