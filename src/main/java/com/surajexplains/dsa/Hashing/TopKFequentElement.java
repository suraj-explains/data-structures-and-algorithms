package com.surajexplains.dsa.Hashing;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFequentElement {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> frequency = new HashMap<>();
        for(int num: nums){
            frequency.put(num,frequency.getOrDefault(num,0) + 1);

        }

        List<Integer> list = new ArrayList<>(frequency.values());

        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(
                (a,b) -> Integer.compare(a.getValue(),b.getValue()) );

        for (Map.Entry<Integer,Integer> e: frequency.entrySet()){
            pq.add(e);
            if (pq.size() > k){
                pq.poll();
            }
        }

        int[] res = new int[pq.size()];
        int count =0;
        while (!pq.isEmpty()){
            res[count] = pq.poll().getKey();
            count++;
        }

        return res;

    }


        /*

        Input: nums = [1,1,1,2,2,3], k = 2
        1,2,1,2,1,2,3,1,3,2
        element frequency
        1.       4
        2.       4
        3.       2

  freq       0 1 2 3 4 5 6
  element.     3 2 1

        Output: [1,2]
         */

    public static int[] topKFrequentBucketSort(int[] nums, int k) {

        Map<Integer,Integer> frequency = new HashMap<>();
        for(int num: nums){
            frequency.put(num,frequency.getOrDefault(num,0) + 1);

        }


        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer,Integer> entry: frequency.entrySet()){
            int freq = entry.getValue();
            int element = entry.getKey();

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(element);
        }

        int[] result = new int[k];
        int count  = 0;
        for(int i = bucket.length -1; i>=0 && count < k;i-- ){
            if (bucket[i] != null){

                for (int a = 0;a<bucket[i].size();a++){
                    result[count] = bucket[i].get(i);
                    count++;
                    if (count == k){
                        return result;
                    }
                }


            }
        }

        return result;

    }

    public static void main(String[] args) {
        //topKFrequentBucketSort(new int[]{1,1,1,2,2,3},2);

        Deque<Integer> dequeu = new ArrayDeque<>();

        dequeu.add(1);
        dequeu.add(2);
        dequeu.add(3);
        dequeu.offerFirst(0);

        System.out.println(dequeu.pollLast());

        /*

        2, 1, 5, 2, 2, 5, 1

        1 1 2 2 2 5 5
         */

    }
}
