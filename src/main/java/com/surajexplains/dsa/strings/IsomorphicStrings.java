package com.surajexplains.dsa.strings;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    //Input: s = "paper", t = "title"
    //"badc". "baba"
    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()){
            return false;
        }
        Map<Character,Character> map = new HashMap<>();

        for(int i=0;i<s.length();i++){
            char sch = s.charAt(i);
            char tch = t.charAt(i);

            if (map.containsKey(sch)){
                if(!map.get(sch).equals(tch)){
                    return false;
                }
            }



            map.put(sch,tch);
        }

        return true;
    }
}
