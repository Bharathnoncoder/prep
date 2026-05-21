package com.leetCode.collection.algo.twoPointersOpositeEndP1;


import java.util.Map;
import java.util.HashMap;

public class LongestSubstringwithAtMostKDistinctCharacters {

    public static void main(String[] args) {
        System.out.println(longestKDistinct("eceba",2));
    }

    public static int longestKDistinct(String s, int k) {
        int left = 0;
        int max = 0;

        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            map.put(c, map.getOrDefault(c, 0) + 1);

            // Shrink window if distinct chars > k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
