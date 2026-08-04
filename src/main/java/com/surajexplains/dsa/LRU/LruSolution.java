package com.surajexplains.dsa.LRU;


import static com.surajexplains.dsa.LRU.DoublyLL.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LruSolution {
    private int capacity;
    List<Integer> container = new ArrayList<>();
    Map<Integer, Node> cache = new HashMap();
    DoublyLL dll = new DoublyLL();

    public LruSolution(int capacity){
        this.capacity = capacity;
    }

    public void put(int n){


        Node node = new Node(n);
        cache.put(n,node);
        dll.addFirst(node);

        if (cache.size() > capacity){
            dll.removeLast();;
        }


    }

    public int get(int n){

        if (cache.containsKey(n)){
            Node node = cache.get(n);

            dll.remove(node);
            dll.addFirst(node);
            return node.getData();

        }else {
            return -1;
        }

    }



}
