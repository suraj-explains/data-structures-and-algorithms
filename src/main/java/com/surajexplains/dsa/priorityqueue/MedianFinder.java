package com.surajexplains.dsa.priorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class MedianFinder {

    //Max heap
    PriorityQueue<Integer> lower;
    //min heap
    PriorityQueue<Integer> upper;

    public MedianFinder() {

         lower = new PriorityQueue<>(Collections.reverseOrder());
         upper = new PriorityQueue<>();
    }

    public void addNum(int num) {
            lower.offer(num);

            upper.offer(lower.poll());

            if (lower.size() < upper.size()){
                lower.offer(upper.poll());
            }
    }

    public double findMedian() {
        if (lower.size() > upper.size()){
            return (double)lower.poll();
        }

            return (double )(lower.poll() + upper.poll())/2;
    }
}