package com.algorithm.demo.examples;

/**
 * 最大和连续子数组
 */
public class LargestSumContiguousSubarray<T> {

    private T[] array;

    public LargestSumContiguousSubarray(T[] array)
    {
        this.array = array;
    }

    public int FindSubarrayByViolence(int[] array) {
        int n = array.length;
        int MaxSum = (Integer)array[0];
        for(int i = 0 ; i < n ; i ++){
            int temp = 0;
            for(int j = i; j < n ; j ++ ){
                temp += (Integer)array[j];
                MaxSum = MaxSum > temp ? MaxSum : temp;
            }

        }

        return MaxSum;
    }

    public int FindSubarrayBy()
    {
        int size = array.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + (Integer)array[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }


}
