package com.leetCode.collection.algo.twoPointersOpositeEndP1;

public class ContainerWithMostWater {
    public static void main(String[] args) {

        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

// Expected output: 49
// walls at index 1(height=8) and index 8(height=7)
// water = min(8,7) * (8-1) = 7*7 = 49

        System.out.println(maxContainer(heights));

    }


//    🧠 Important Insight (this is what interviewers care about)
//
//    Why move the smaller height pointer?
//
//    Because:
//
//    Area = width × min(height)
//    Width always decreases
//    So only way to increase area is to find a taller line
//    Moving the taller one is useless → height won’t improve

    public static int maxContainer(int[] arr) {
        int max = 0;
        int left = 0;
        int right = arr.length -1;

        while (left < right) {
           var water =  Math.min(arr[left], arr[right]) * (right - left);
           max = Math.max(max, water);
           if(arr[left] < arr[right]) {
               left++;
           } else right--;
        }
        return max;
    }
}
