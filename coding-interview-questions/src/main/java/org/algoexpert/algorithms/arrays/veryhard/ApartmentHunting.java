package org.algoexpert.algorithms.arrays.veryhard;

import org.algoexpert.algorithms.arrays.ArraysAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You're looking to move into a new apartment on a specific street, and you're given a list of contiguous blocks on
 * that street where each block contains an apartment that you could move into.
 * <p> You also have a list of requirements: a list of buildings that are important to you. For instance, you might
 * value having a school and a gym near your apartment. The list of blocks that you have contains information at every
 * block about all the buildings that are present and absent at the block in question. For instance, for every block,
 * you might know whether a school, a pool, an office, and a gym are present.
 * <p> To optimize your life, you want to pick an apartment block such that you minimize the farthest distance you'd
 * have to walk from your apartment to reach any of your required buildings.
 * <p> Write a function that takes in a list of contiguous blocks on a specific street and a list of your required
 * buildings, and that returns the location (the index) of the block that's most optimal for you.
 * <p> If there are multiple most optimal blocks, your function can return the index of any one of them.
 * <pre>
 * Sample Input:
 * blocks = [
 *   {
 *     "gym": false,
 *     "school": true,
 *     "store": false
 *   },
 *   {
 *     "gym": true,
 *     "school": false,
 *     "store": false
 *   },
 *   {
 *     "gym": true,
 *     "school": true,
 *     "store": false
 *   },
 *   {
 *     "gym": false
 *     "school": true,
 *     "store": false
 *   },
 *   {
 *     "gym": false,
 *     "school": true,
 *     "store": true
 *   }
 * ]
 * reqs = ["gym", "school", "store"]
 * Sample Output:
 * 3 // at index 3, the farthest you'd have to walk to reach a gym, a school, or a store is 1 block; at any other index,
 * you'd have to walk further
 *
 * Optimal Space & Time Complexity
 * O(br) time | O(br) space - where b is the number of blocks and r is the number of requirements
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class ApartmentHunting implements ArraysAlgorithms {

    /**
     * Finds the best block to live in based on the minimum distance to all required facilities.
     * <p> This method takes a list of blocks, where each block is represented as a map of facility availability, and an
     * array of required facilities. It calculates the minimum distance from each block to the closest block with each
     * required facility and returns the index of the block that has the smallest maximum distance to any required
     * facility.
     * <p> The method iterates through each required facility and calculates the minimum distance for each block to the
     * closest block with that facility, both from the left and the right. These distances are stored in a list and used
     * to find the block with the smallest maximum distance to any required facility.
     *
     * @param blocks a list of maps, where each map represents a block and contains the availability of required
     *               facilities
     * @param reqs an array of required facilities
     * @return the index of the block that has the smallest maximum distance to any required facility
     */
    @Override
    public int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {

        int n = blocks.size();
        List<Integer[]> minDistancesForEachReq = new ArrayList<>();

        for (String req : reqs) {
            Integer[] minDistances = new Integer[n];
            int closestReqIdx = Integer.MAX_VALUE;

            // Calculate the minimum distance for each block to the closest block on the left with the requirement
            for (int i = 0; i < n; i++) {
                Map<String, Boolean> block = blocks.get(i);
                if (block.get(req)) {
                    closestReqIdx = i;
                }
                minDistances[i] = distanceBetween(i, closestReqIdx);
            }

            // Calculate the minimum distance for each block to the closest block on the right with the requirement
            for (int i = n - 1; i >= 0; i--) {
                Map<String, Boolean> block = blocks.get(i);
                if (block.get(req)) {
                    closestReqIdx = i;
                }
                minDistances[i] = Math.min(minDistances[i], distanceBetween(i, closestReqIdx));
            }

            // Add the minimum distances for the current requirement to the list
            minDistancesForEachReq.add(minDistances);
        }

        return getBlockIndexWithMinDistanceToAllReq(n, minDistancesForEachReq);
    }

    /**
     * Finds the index of the block that has the smallest maximum distance to any required facility.
     * <p> This method takes the number of blocks and a list of minimum distances for each requirement. It calculates
     * the maximum distance for each block to the closest block with any requirement and then finds the block with the
     * smallest maximum distance.
     * <p> The method iterates through the map of maximum distances for each block and updates the index of the block
     * with the smallest maximum distance.
     *
     * @param n the number of blocks
     * @param minDistancesForEachReq a list of integer arrays, where each array contains the minimum distances for each
     *                               requirement for each block
     * @return the index of the block that has the smallest maximum distance to any required facility
     */
    private static int getBlockIndexWithMinDistanceToAllReq(int n, List<Integer[]> minDistancesForEachReq) {

        Map<Integer, Integer> maxDistanceForEachBlock = getMaxDistanceForEachBlock(n, minDistancesForEachReq);

        int blockIndexWithMinDistanceToAllReq = -1;
        int minMaxDistance = Integer.MAX_VALUE;
        // Find the block with the minimum maximum distance to the closest block with any requirement
        for (Map.Entry<Integer, Integer> entry : maxDistanceForEachBlock.entrySet()) {
            if(entry.getValue() < minMaxDistance) {
                minMaxDistance = entry.getValue();
                blockIndexWithMinDistanceToAllReq = entry.getKey();
            }
        }
        return blockIndexWithMinDistanceToAllReq;
    }

    /**
     * Calculates the maximum distance for each block to the closest block with any requirement.
     * <p>
     * This method takes the number of blocks and a list of minimum distances for each requirement. It iterates through
     * each block and calculates the maximum distance to the closest block with any requirement. The result is stored in
     * a map where the key is the block index, and the value is the maximum distance for that block.
     *
     * @param n the number of blocks
     * @param minDistancesForEachReq a list of integer arrays, where each array contains the minimum distances for each
     *                               requirement for each block
     * @return a map where the key is the block index and the value is the maximum distance to the closest block with
     *         any requirement
     */
    private static Map<Integer, Integer> getMaxDistanceForEachBlock(int n, List<Integer[]> minDistancesForEachReq) {

        Map<Integer, Integer> maxDistanceForEachBlock = new HashMap<>();
        // Calculate the maximum distance for each block to the closest block with any requirement
        for (int i = 0; i < n; i++) {
            int maxDistanceOfAllReq = Integer.MIN_VALUE;
            for (Integer[] minDistances : minDistancesForEachReq) {
                if(minDistances[i] > maxDistanceOfAllReq) {
                    maxDistanceOfAllReq = minDistances[i];
                }
            }
            maxDistanceForEachBlock.put(i, maxDistanceOfAllReq);
        }
        return maxDistanceForEachBlock;
    }

    /**
     * Calculates the absolute distance between two integers.
     * <p>
     * This method takes two integer parameters and returns the absolute value of their difference. It uses the
     * {@link Math#abs(int)} method to ensure the result is non-negative.
     * </p>
     *
     * @param a the first integer
     * @param b the second integer
     * @return the absolute distance between the two integers
     */
    private static int distanceBetween(int a, int b) {
        return Math.abs(a - b);
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
    public List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
