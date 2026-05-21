package com.leetCode.collection.algo.twoPointersOpositeEndP1;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
//        Input: "abcabcbb"
//        Output: 3
//        Explanation: "abc"

        System.out.println(longSubStringString1( "abcabcbb"));
    }

    public static int longSubStringString1(String input){
        int max = 0;
        int left = 0;

        Map<Character,Integer> charMap = new HashMap<>();
        for(int right =0; right < input.length(); right++){
            char c = input.charAt(right);
            if(charMap.containsKey(c)){
                left = Math.max(left, charMap.get(c)+1);
            }
            charMap.put(c,right);
            max = Math.max(max, right - left+1);
        }
        return max;
    }

    public static int longSubStringString(String input){

        int pointerOne  = 0;
        int pointerTwo = 0;
        int index =0;

        Map<Character,Integer> map = new HashMap<>();
        List<String> listOfString = new ArrayList<>();
        while (pointerTwo < input.length()) {
            if(map.containsKey(input.charAt(pointerTwo))){
                int indexOfDuplicateChar = map.get(input.charAt(pointerTwo));
                listOfString.add(input.substring(indexOfDuplicateChar +1, pointerTwo+1));
                map.put(input.charAt(pointerTwo),pointerTwo++);
            } else{
                map.put(input.charAt(index),index++);
                pointerTwo++;
            }
        }

        System.out.println(listOfString);

        return -1;
    }
}
