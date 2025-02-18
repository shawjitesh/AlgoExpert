package org.algoexpert.algorithms.arrays;

import org.springframework.stereotype.Component;

/**
 * Interface for array-related algorithms.
 * <p>
 * This interface defines methods for various algorithms that operate on arrays.
 * </p>
 * <p>
 * The interface is annotated with {@link Component}, indicating that it is a Spring component and can be injected as a
 * dependency where needed.
 * </p>
 *
 * @author Jitesh Shaw
 */

@Component
public interface ArraysAlgorithms {

    /**
     * Finds a pair of numbers in the given array that sum up to the target sum.
     * <p>
     * This method searches the provided array for two numbers that add up to the specified target sum. If such a pair
     * is found, it returns an array containing the two numbers. If no such pair exists, it returns an empty array.
     * </p>
     *
     * @param array the array of integers to search
     * @param targetSum the target sum to find in the array
     * @return an array containing the pair of numbers that sum up to the target sum, or an empty array if no such pair
     * exists
     */
    int[] twoNumberSum(int[] array, int targetSum);
}
