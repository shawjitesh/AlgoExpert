package org.algoexpert.algorithms.binarysearchtrees.veryhard;

import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;
import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.algoexpert.algorithms.binarysearchtrees.medium.ConstructBST;

import java.util.List;
import java.util.Objects;

/**
 * An array of integers is said to represent the Binary Search Tree (BST) obtained by inserting each integer in the
 * array, from left to right, into the BST.
 * <p> Write a function that takes in two arrays of integers and determines whether these arrays represent the same BST.
 * Note that you're not allowed to construct any BSTs in your code.
 * <p> A BST is a Binary Search Tree that consists only of BST nodes. A node is said to be a valid BST node if and only
 * if it satisfies the BST property: its value is strictly greater than the values of every node to its left; its value
 * is less than or equal to the values of every node to its right; and its children nodes are either valid BST nodes
 * themselves or None/null.
 * <pre>
 * Sample Input:
 * arrayOne = [10, 15, 8, 12, 94, 81, 5, 2, 11]
 * arrayTwo = [10, 8, 5, 15, 2, 12, 11, 94, 81]
 * Sample Output:
 * true // both arrays represent the BST below
 * 	    10
 *          /      \
 *         8       15
 *       /        /   \
 *      5        12   94
 *     /        /     /
 *    2        11    81
 *
 * Optimal Space & Time Complexity:
 * O(n^2) time | O(d) space - where n is the number of nodes in each array, respectively, and d is the depth of the BST
 * that they represent
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class SameBSTsOptimized implements BinarySearchTreesAlgorithms {

    /**
     * Determines if two arrays represent the same Binary Search Tree (BST).
     * <p>
     * This method checks if two arrays can represent the same BST by comparing their sizes, root values, and
     * recursively comparing the left and right subtrees. The left subtree contains values smaller than the root, and
     * the right subtree contains values greater than or equal to the root.
     * </p>
     *
     * @param arrayOne the first array representing a BST
     * @param arrayTwo the second array representing a BST
     * @return true if the two arrays represent the same BST, false otherwise
     */
    @Override
    public boolean sameBstsOptimized(List<Integer> arrayOne, List<Integer> arrayTwo) {
        return areSameBsts(arrayOne, arrayTwo, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Recursively determines if two arrays represent the same Binary Search Tree (BST).
     * <p>
     * This method checks if two arrays can represent the same BST by comparing their root values and recursively
     * comparing the left and right subtrees within the given value range. The left subtree contains values smaller than
     * the root, and the right subtree contains values greater than or equal to the root.
     * </p>
     *
     * @param arrayOne the first array representing a BST
     * @param arrayTwo the second array representing a BST
     * @param rootIdxOne the index of the current root in the first array
     * @param rootIdxTwo the index of the current root in the second array
     * @param minValue the minimum value that nodes in the current subtree can have
     * @param maxValue the maximum value that nodes in the current subtree can have
     * @return true if the two arrays represent the same BST, false otherwise
     */
    private boolean areSameBsts(List<Integer> arrayOne, List<Integer> arrayTwo, int rootIdxOne, int rootIdxTwo, int minValue, int maxValue) {

        if (rootIdxOne == -1 || rootIdxTwo == -1) {
            return rootIdxOne == rootIdxTwo;
        }

        if (!Objects.equals(arrayOne.get(rootIdxOne), arrayTwo.get(rootIdxTwo))) {
            return false;
        }

        int leftRootIdxOne = getIdxOfFirstSmaller(arrayOne, rootIdxOne, minValue);
        int leftRootIdxTwo = getIdxOfFirstSmaller(arrayTwo, rootIdxTwo, minValue);
        int rightRootIdxOne = getIdxOfFirstBiggerOrEqual(arrayOne, rootIdxOne, maxValue);
        int rightRootIdxTwo = getIdxOfFirstBiggerOrEqual(arrayTwo, rootIdxTwo, maxValue);

        int currentValue = arrayOne.get(rootIdxOne);
        boolean leftAreSame = areSameBsts(arrayOne, arrayTwo, leftRootIdxOne, leftRootIdxTwo, minValue, currentValue);
        boolean rightAreSame = areSameBsts(arrayOne, arrayTwo, rightRootIdxOne, rightRootIdxTwo, currentValue, maxValue);

        return leftAreSame && rightAreSame;
    }

    /**
     * Finds the index of the first element in the list that is smaller than the element at the given starting index
     * and greater than or equal to the specified minimum value.
     * <p>
     * This method iterates through the list starting from the element after the given starting index and returns the
     * index of the first element that meets the criteria. If no such element is found, it returns -1.
     * </p>
     *
     * @param array the list of integers to search through
     * @param startingIdx the index of the element to compare against
     * @param minValue the minimum value that the found element must be greater than or equal to
     * @return the index of the first element that is smaller than the element at the starting index and greater than or
     * equal to the minimum value, or -1 if no such element is found
     */
    private int getIdxOfFirstSmaller(List<Integer> array, int startingIdx, int minValue) {

        for (int i = startingIdx + 1; i < array.size(); i++) {
            if (array.get(i) < array.get(startingIdx) && array.get(i) >= minValue) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Finds the index of the first element in the list that is greater than or equal to the element at the given
     * starting index and less than the specified maximum value.
     * <p>
     * This method iterates through the list starting from the element after the given starting index and returns the
     * index of the first node with value bigger or equal to that of the current node
     * </p>
     *
     * @param array the list of integers to search through
     * @param startingIdx the index of the element to compare against
     * @param maxValue the maximum value that the found element must be less than
     * @return the index of the first element that is greater than or equal to the element at the starting index and
     * less than the maximum value, or -1 if no such element is found
     */
    private int getIdxOfFirstBiggerOrEqual(List<Integer> array, int startingIdx, int maxValue) {

        for (int i = startingIdx + 1; i < array.size(); i++) {
            if (array.get(i) >= array.get(startingIdx) && array.get(i) < maxValue) {
                return i;
            }
        }

        return -1;
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public int findClosestValueInBst(FindClosestValueInBST.BST tree, int target) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
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

}
