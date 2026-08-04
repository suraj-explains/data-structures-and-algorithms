package com.surajexplains.dsa.priorityqueue;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder1 {

   int[] bucket = new int[101];
   int count = 0;

    public MedianFinder1() {


    }

    public void addNum(int num) {
        bucket[num]++;
        count++;
    }

    public double findMedian() {
        int target1 =0;
        int target2 = 0;
        if (count % 2 == 1){
            target1 = count/2+1;
        }
        if (count%2==0){
            target1 = count/2;
            target2 = target1 +1;
        }

        int countSoFar=0;
        int firstVal = -1;
        int secondVal = -1;
        for (int i=1;i< bucket.length;i++){
            countSoFar = countSoFar + bucket[i];
            if (firstVal != -1 && countSoFar >= target1){
                firstVal = i;
            }
            if (secondVal !=-1 && countSoFar >= target2){
                secondVal = i;
            }
        }

        return (firstVal + secondVal) /2.0;
    }
}