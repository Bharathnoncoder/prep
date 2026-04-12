package com.leetCode;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
//        Input: numbers = [2,7,11,15], target = 9
//        Output: [1,2]

        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));


//        2) Container With Most Water
//
//        Problem:
//        You are given an integer array height where each element represents the height of a vertical line.
//
//                Find two lines that together with the x-axis form a container that holds the most water.
//
//        Return the maximum amount of water the container can store.
//        Input: height = [1,8,6,2,5,4,8,3,7]
//        Output: 49
    }

    // using two pointer algo i solved this
    private  static  int[] twoSum(int[] arr, int target){

        int i = 0;
        int j = arr.length -1;

        while (i < j){
            if(arr[i] + arr[j] > target) {
                j--;
            } else if(arr[i] + arr[j] < target){
                i++;
            } else  if(arr[i] + arr[j] == target){
                return new int[]{i+1 , j+1};
            }
        }
        return new int[]{};
    }
}
