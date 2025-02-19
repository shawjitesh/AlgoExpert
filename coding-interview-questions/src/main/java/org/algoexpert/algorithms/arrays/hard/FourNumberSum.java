package org.algoexpert.algorithms.arrays.hard;

import org.algoexpert.algorithms.arrays.ArraysAlgorithms;

import java.util.*;

/**
 * Write a function that takes in a non-empty array of distinct integers and an integer representing a target sum. The
 * function should find all quadruplets in the array that sum up to the target sum and return a two-dimensional array
 * of all these quadruplets in no particular order.
 * <p> If no four numbers sum up to the target sum, the function should return an empty array.
 * <pre>
 * Sample Input:
 * array = [7, 6, 4, -1, 1, 2]
 * targetSum = 16
 * Sample Output:
 * [[7, 6, 4, -1], [7, 6, 1, 2]] // the quadruplets could be ordered differently
 *
 * Optimal Space & Time Complexity:
 * Average: O(n^2) time | O(n^2) space - where n is the length of the input array || Worst: O(n^3) time | O(n^2) space -
 * where n is the length of the input array
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class FourNumberSum implements ArraysAlgorithms {

    /**
     * This method takes a non-empty array of distinct integers and an integer representing a target sum. It returns a
     * list of integer arrays, where each array contains four numbers from the input array that sum up to the target
     * sum. If no such quadruplet exists, it returns an empty list.
     * <p> The method uses a hashtable to store all pairs of numbers and their sums as it iterates through the array.
     * For each pair of numbers, it calculates the current sum and the difference needed to reach the target sum. If the
     * difference is found in the hashtable, it means there exists a pair of numbers that, together with the current
     * pair, sum up to the target sum. These quadruplets are added to the result list.
     * <p> The method iterates through the array twice: the first loop starts from the second element, and the second
     * loop starts from the element after the current element in the first loop. This ensures that all pairs of numbers
     * are considered.
     *
     * @param array the input array of distinct integers
     * @param targetSum the target sum to find in the array
     * @return a list of integer arrays, where each array contains four integers that sum up to the target sum,
     * or an empty list if no such quadruplet exists
     */
    @Override
    public List<Integer[]> fourNumberSum(int[] array, int targetSum) {

        Map<Integer, List<Integer[]>> allPairSums = new HashMap<>();
        List<Integer[]> quadruplets = new ArrayList<>();

        int n = array.length;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentSum = array[i] + array[j];
                int difference = targetSum - currentSum;
                if (allPairSums.containsKey(difference)) {
                    for (Integer[] pair : allPairSums.get(difference)) {
                       quadruplets.add(new Integer[]{pair[0], pair[1], array[i], array[j]});
                    }
                }
            }
            for (int j = 0; j < i; j++) {
                int currentSum = array[i] + array[j];
                if (allPairSums.containsKey(currentSum)) {
                    allPairSums.get(currentSum).add(new Integer[]{array[j], array[i]});
                } else {
                    Integer[] pair = new Integer[]{array[j], array[i]};
                    allPairSums.put(currentSum, new ArrayList<>(){{
                        add(pair);
                    }});
                }
            }
        }

        return quadruplets;
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
    public List<Integer[]> threeNumberSum(int[] array, int targetSum) {
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
