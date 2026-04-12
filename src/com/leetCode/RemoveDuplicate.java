package com.leetCode;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class RemoveDuplicate {


    public static void main(String[] args) {
        String s = "programming";
        System.out.println(removeDup(s));
    }

    static String removeDup(String input) {

        Map<Character, Integer> map = new LinkedHashMap<>();

       for(int i = 0; i < input.length(); i++) {
           char e = input.charAt(i);
           if(map.get(e) == null){
               map.put(e, 1);
           } else {
              map.put(e, map.get(e)+1);
           }
       }
        StringBuilder inputBuilder = new StringBuilder();
        for(var c :  map.entrySet()){
           inputBuilder.append(c.getKey());
       }
       return inputBuilder.toString();

    }
}
