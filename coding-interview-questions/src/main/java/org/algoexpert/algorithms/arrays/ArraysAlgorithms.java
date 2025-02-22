package org.algoexpert.algorithms.arrays;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
     *
     * @param array the array of integers to search
     * @param targetSum the target sum to find in the array
     * @return an array containing the pair of numbers that sum up to the target sum, or an empty array if no such pair
     * exists
     */
    int[] twoNumberSum(int[] array, int targetSum);
    /**
     * Finds all unique triplets in the given array that sum up to the target sum.
     *
     * @param array the input array of distinct integers
     * @param targetSum the target sum to find in the array
     * @return a list of integer arrays, where each array contains three integers that sum up to the target sum,
     * or an empty list if no such triplet exists
     */
    List<Integer[]> threeNumberSum(int[] array, int targetSum);
    /**
     * Finds all unique quadruplets in the given array that sum up to the target sum.
     *
     * @param array the input array of distinct integers
     * @param targetSum the target sum to find in the array
     * @return a list of integer arrays, where each array contains four integers that sum up to the target sum,
     * or an empty list if no such quadruplet exists
     */
    List<Integer[]> fourNumberSum(int[] array, int targetSum);
    /**
     * Finds the best block to live in based on the minimum distance to all required facilities.
     *
     * @param blocks a list of maps, where each map represents a block and contains the availability of required
     *               facilities
     * @param reqs an array of required facilities
     * @return the index of the block that has the smallest maximum distance to any required facility
     */
    int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs);
}
