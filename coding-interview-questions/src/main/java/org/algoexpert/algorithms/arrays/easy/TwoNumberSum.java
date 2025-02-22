package org.algoexpert.algorithms.arrays.easy;

import org.algoexpert.algorithms.arrays.ArraysAlgorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Write a function that takes in a non-empty array of distinct integers and an integer representing a target sum.
 * If any two numbers in the input array sum up to the target sum, the function should return them in an array, in
 * any order. If no two numbers sum up to the target sum, the function should return an empty array.
 * <p> Note that the target sum has to be obtained by summing two different integers in the array; you can't add a
 * single integer to itself to get the target sum.
 * <p> You can assume that there will be at most one pair of numbers summing up to the target sum.
 * <pre>
 * Sample Input:
 * array = [3, 5, -4, 8, 11, 1, -1, 6]
 * targetSum = 10
 * Sample Output:
 * [-1, 11] // the numbers could be in reverse order
 *
 * Optimal Space & Time Complexity:
 * O(n) time | O(n) space - where n is the length of the input array
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class TwoNumberSum implements ArraysAlgorithms {

    /**
     * This method takes a non-empty array of distinct integers and an integer representing a target sum.
     * It returns an array containing any two numbers from the input array that sum up to the target sum.
     * If no such pair exists, it returns an empty array.
     * <p>
     * The method uses a hashtable to store the elements of the array as it iterates through it.
     * For each element, it calculates the potential match that would sum up to the target sum.
     * If the potential match is found in the hashtable, it returns the pair of numbers.
     * Otherwise, it adds the current element to the hashtable.
     *
     * @param array the input array of distinct integers
     * @param targetSum the target sum to find in the array
     * @return an array containing two integers that sum up to the target sum, or an empty array if no such pair exists
     */
    @Override
    public int[] twoNumberSum(int[] array, int targetSum) {

        Map<Integer, Boolean> arrayElementsAsDifference = new HashMap<>();

        for(int element : array) {
            int difference = targetSum - element;
            if(arrayElementsAsDifference.containsKey(difference)) {
                return new int[] {difference, element};
            } else {
                arrayElementsAsDifference.put(element, true);
            }
        }

        return new int[0];
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
