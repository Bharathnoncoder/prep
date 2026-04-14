package com.leetCode.collection.algo.twoPointersOpositeEndP1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTwoSumAndReturnAllTwoSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int target = 9;

// Expected output:
// [1,8], [2,7], [3,6], [4,5]

        System.out.println(findAllTheTargetSum(arr, target));
    }


    public static List<ArrayList<Integer>> findAllTheTargetSum(int[] arr, int target){

       List<ArrayList<Integer>> result = new ArrayList<>();

       int left = 0;

       int right = arr.length -1;

       while (left < right){

           int sum = arr[left] + arr[right];

           if(sum == target) {
               var foundPair = new ArrayList<Integer>();
               foundPair.add(arr[left]);
               foundPair.add(arr[right]);
               result.add(foundPair);
               left++;
               right--;
           } else if (sum < target)
                left++;
             else
               right--;
       }
        return result;
    }
}
