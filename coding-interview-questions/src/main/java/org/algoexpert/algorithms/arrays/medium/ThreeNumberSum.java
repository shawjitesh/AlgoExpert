package org.algoexpert.algorithms.arrays.medium;

import org.algoexpert.algorithms.arrays.ArraysAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Write a function that takes in a non-empty array of distinct integers and an integer representing a target sum. The
 * function should find all triplets in the array that sum up to the target sum and return a two-dimensional array of
 * all these triplets. The numbers in each triplet should be ordered in ascending order, and the triplets themselves
 * should be ordered in ascending order with respect to the numbers they hold.
 * <p> If no three numbers sum up to the target sum, the function should return an empty array.
 * <pre>
 * Sample Input:
 * array = [12, 3, 1, 2, -6, 5, -8, 6]
 * targetSum = 0
 * Sample Output:
 * [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 *
 * Optimal Space & Time Complexity:
 * O(n^2) time | O(n) space - where n is the length of the input array
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class ThreeNumberSum implements ArraysAlgorithms {

    /**
     * This method takes a non-empty array of distinct integers and an integer representing a target sum. It returns a
     * list of integer arrays, where each array contains three numbers from the input array that sum up to the target
     * sum. If no such triplet exists, it returns an empty list.
     * <p>
     * The method sorts the input array and then uses a three-pointer approach to find all unique triplets that sum up
     * to the target sum. It iterates through the array, and for each element, it uses two pointers (left and right) to
     * find pairs that, together with the current element, sum up to the target sum.
     * </p>
     *
     * @param array the input array of distinct integers
     * @param targetSum the target sum to find in the array
     * @return a list of integer arrays, where each array contains three integers that sum up to the target sum,
     * or an empty list if no such triplet exists
     */
    @Override
    public List<Integer[]> threeNumberSum(int[] array, int targetSum) {

        List<Integer[]> result = new ArrayList<>();

        Arrays.sort(array);

        int n = array.length;
        for(int current = 0; current < n - 2; current++) {
            int left = current + 1;
            int right = n - 1;

            while(left < right) {
                int currentSum = array[current] + array[left] + array[right];
                if(currentSum == targetSum) {
                    result.add(new Integer[] {array[current], array[left], array[right]});
                    left++;
                    right--;
                } else if(currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public int[] twoNumberSum(int[] array, int targetSum) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
