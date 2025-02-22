package org.algoexpert.algorithms.binarysearchtrees.easy;

import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;
import org.algoexpert.algorithms.binarysearchtrees.medium.ConstructBST;

import java.util.List;

/**
 * Write a function that takes in a Binary Search Tree (BST) and a target integer value and returns the closest value to
 * the target value contained in the BST.
 * <p> You can assume that there will only be one closest value.
 * <p> Each BST node has an integer value, a left child, and a right child node. A node is said to be valid BST if and
 * only it satisfies the BST property; its value is strictly greater than the values of every node to is left; its value
 * is less than or equal to the values of every node to its right; and its children nodes are either valid BST nodes
 * themselves or None/null.
 * <pre>
 * Sample Input:
 * tree =      10
 *          /      \
 *         5       15
 *       /   \    /   \
 *      2     5  13   22
 *     /          \
 *    1           14
 * target = 12
 * Sample Output:
 * 13
 *
 * Optimal Space & Time Complexity:
 * Average: O(log(n)) time | O(1) space - where n is the number of nodes in the BST || Worst: O(n) time | O(1) space -
 * where n is the number of nodes in the BST
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class FindClosestValueInBST implements BinarySearchTreesAlgorithms {

    /**
     * Finds the value in the Binary Search Tree (BST) that is closest to the given target value.
     * <p>
     * This method traverses the BST starting from the root node and iteratively compares the target value with the
     * current node's value to find the closest value. It keeps track of the minimum difference between the target value
     * and the current node's value, updating the closest value accordingly.
     * </p>
     *
     * @param tree the root node of the BST
     * @param target the target value to find the closest value to
     * @return the value in the BST that is closest to the target value
     */
    @Override
    public int findClosestValueInBst(BST tree, int target) {

        BST currentNode = tree;
        int closestValue = -1;
        int minDifference = Integer.MAX_VALUE;

        while (currentNode != null) {
            if(currentNode.value == target) {
                return currentNode.value;
            }
            int difference = Math.abs(target - currentNode.value);
            if(difference < minDifference) {
                minDifference = difference;
                closestValue = currentNode.value;
            }
            if(target > currentNode.value) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }

        return closestValue;
    }

    /**
     * Represents a node in a Binary Search Tree (BST).
     * <p>
     * Each node contains an integer value, and references to the left and right child nodes.
     * </p>
     */
    public static class BST {

        public int value;
        public BST left;
        public BST right;

        /**
         * Constructs a BST node with the specified value.
         *
         * @param value the integer value of the node
         */
        public BST(int value) {
            this.value = value;
        }
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public ConstructBST.BST constructBst(List<String[]> bstOperations) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public boolean sameBstsOptimized(List<Integer> arrayOne, List<Integer> arrayTwo) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public List<Integer> rightSmallerThan(List<Integer> array) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
