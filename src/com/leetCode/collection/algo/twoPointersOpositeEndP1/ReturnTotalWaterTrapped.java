package com.leetCode.collection.algo.twoPointersOpositeEndP1;

public class ReturnTotalWaterTrapped {

    public static void main(String[] args) {
//        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//        Output: 6

        System.out.println(returnTheUnitOfWater(new int[]{3,0,0,2,0,4}));
    }

    public static int returnTheUnitOfWater(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int sum = 0;

        while (left < right) {

            if (arr[left] < arr[right]) {

                if (arr[left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    sum += leftMax - arr[left];
                }
                left++;

            } else {

                if (arr[right] >= rightMax) {
                    rightMax = arr[right];
                } else {
                    sum += rightMax - arr[right];
                }
                right--;
            }
        }

        return sum;
    }
}
