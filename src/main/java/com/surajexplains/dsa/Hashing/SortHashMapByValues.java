package com.surajexplains.dsa.Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortHashMapByValues {

    public static void sortMapByValues(){
        Map<Integer,Integer> map = new HashMap<>();

        map.put(1,3);
        map.put(2,1);
        map.put(3,2);

        System.out.println("before Sorting");
        display(map);

        List<Integer> arrayList = new ArrayList<>(map.values());

        int[] intArr = new int[map.size()];
        int j =0;
        for (Integer i : map.values()){
            intArr[j]= i;
            j++;
        }

        System.out.println("Arrays values:");
        for (int i:intArr){
            System.out.println(i);
        }

        Collections.sort(arrayList);
        System.out.println("Sorter Values: ");
        for (Integer i : arrayList){
            System.out.println(i);
        }

        List<Map.Entry<Integer, Integer>> list = map.entrySet()
                .stream()
                //.sorted()
                 .sorted((a,b) -> Integer.compare(a.getValue(),b.getValue()))
                .toList();

        System.out.println("after Sorting");
        //display(map);
        for(Map.Entry<Integer,Integer> entry: list){
            System.out.println("Key: " + entry.getKey() + "-" + "Value: " + entry.getValue());
        }

    }

    private static void display(Map<Integer,Integer> map){
        map.entrySet().stream().forEach(entry ->
                {
                    System.out.println("Key: " + entry.getKey() + "-" + "Value: " + entry.getValue());
                }
        );
    }

    public static void main(String[] args) {
        sortMapByValues();
    }
}
