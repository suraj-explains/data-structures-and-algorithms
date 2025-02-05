package com.surajexplains.dsa.graph;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AdjacencyListTest {

    @Test
    void bfs() {


          /*

                a
            b   c    f

            d

            e

     */

        Map<Character, List<Character>> graph = new HashMap<>();

        graph.put('a', List.of('b', 'c', 'f'));
        graph.put('b', List.of('a', 'd'));
        graph.put('c', List.of('b'));
        graph.put('d', List.of('b', 'e'));
        graph.put('e', List.of('d'));
        graph.put('f', List.of('a'));


        AdjacencyList.bfs(graph, 'a');
    }
}