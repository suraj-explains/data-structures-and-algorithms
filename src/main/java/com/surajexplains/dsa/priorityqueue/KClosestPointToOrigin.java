package com.surajexplains.dsa.priorityqueue;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KClosestPointToOrigin {

    class Solution {
        public int[][] kClosest(int[][] points, int k) {

            Map<Double,Integer[]> map = new HashMap<>();
            PriorityQueue<Double> pq = new PriorityQueue<>(
                    (a,b)-> Double.compare(b,a));
            for (int[] point : points){
                System.out.println(point[0] +":" + point[1]);
                Double distance = Math.sqrt( (point[0] * point[0]) + (point[1] * point[1]));
                map.put(distance,new Integer[]{point[0],point[1]});

                pq.offer(distance);

                if (pq.size() > k){
                    pq.poll();
                }


            }

            int[][]result = new int[pq.size()][2];
            int i=0;
            while (!pq.isEmpty()){
                Double distance = pq.poll();
                Integer[] point = map.get(distance);
                System.out.println(point[0] +":" + point[1]);
                result[i][0] = point[0];
                result[i][1] = point[1];
                i++;
            }

            return result;

        }
    }
}
