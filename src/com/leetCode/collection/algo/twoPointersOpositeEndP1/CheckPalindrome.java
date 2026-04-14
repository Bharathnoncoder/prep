package com.leetCode.collection.algo.twoPointersOpositeEndP1;

public class CheckPalindrome {

    public static void main(String[] args) {
        String s1 = "Race Car";   // true
        String s2 = "Hello";      // false
        String s3 = "Never Odd Or Even"; // true

        System.out.println(checkPalindrome(s1));
        System.out.println(checkPalindrome(s2));
        System.out.println(checkPalindrome(s3));
    }

    public static boolean checkPalindrome(String s){
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() -1;
        while (left < right) {
            if(s.charAt(left) == ' ') {
                left++;
                continue;
            }
            if(s.charAt(right) == ' ') {
                right--;
                continue;
            }
            if(s.charAt(left) != s.charAt(right))  return false;
            else {
                left++;
                right--;
            }
        }
        return true;
    }
}
