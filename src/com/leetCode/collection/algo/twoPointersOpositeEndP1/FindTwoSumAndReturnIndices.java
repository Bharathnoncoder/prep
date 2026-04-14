package com.leetCode.collection.algo.twoPointersOpositeEndP1;

import java.util.Arrays;

public class FindTwoSumAndReturnIndices {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int target = 4;

// Expected output: [1, 4]
// arr[1] + arr[4] = 3 + 9 = 12

        System.out.println(Arrays.toString(findSumOfTargetIndices(arr,target)));
    }


    public static int[] findSumOfTargetIndices(int[] arr, int target) {

        int left =0;

        int right = arr.length -1;

        while (left < right){
            int sum = arr[left] + arr[right];
            if(sum == target) return  new int[]{left, right};
            else if (sum < target) left++;
            else right--;
        }
        return new int[]{-1,-1};

    }
}
