package org.algoexpert.algorithms.binarysearchtrees.hard;

import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;
import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.algoexpert.algorithms.binarysearchtrees.medium.ConstructBST;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An array of integers is said to represent the Binary Search Tree (BST) obtained by inserting each integer in the
 * array, from left to right, into the BST.
 * <p> Write a function that takes in two arrays of integers and determines whether these arrays represent the same BST.
 * Note that you're not allowed to construct any BSTs in your code.
 * <p> A BST is a Binary Search Tree that consists only of BST nodes. A node is said to be a valid BST node if and only
 * if it satisfies the BST property: its value is strictly greater than the values of every node to its left; its value
 * is less than or equal to the values of every node to tis right; and its children nodes are either valid BST nodes
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
 * O(n^2) time | O(n^2) space - where n is the number of nodes in each array, respectively, and d is the depth of the
 * BST that they represent
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class SameBSTs implements BinarySearchTreesAlgorithms {

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
    public boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {

        if(arrayOne.size() != arrayTwo.size()) {
            return false;
        }

        if(arrayOne.isEmpty()) {    // or arrayTwo.isEmpty()
            return true;
        }

        if(!Objects.equals(arrayOne.get(0), arrayTwo.get(0))) {
            return false;
        }

        List<Integer> leftOne = getSmaller(arrayOne);
        List<Integer> leftTwo = getSmaller(arrayTwo);
        List<Integer> rightOne = getBiggerOrEqual(arrayOne);
        List<Integer> rightTwo = getBiggerOrEqual(arrayTwo);

        return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo);
    }

    /**
     * Returns a list of integers that are smaller than the first element of the input list.
     * <p>
     * This method iterates through the input list starting from the second element and adds elements that are smaller
     * than the first element to a new list.
     * </p>
     *
     * @param array the input list of integers
     * @return a list of integers that are smaller than the first element of the input list
     */
    private List<Integer> getSmaller(List<Integer> array) {
        List<Integer> smaller = new ArrayList<>();
        for(int i = 1; i < array.size(); i++) {
            if(array.get(i) < array.get(0)) {
                smaller.add(array.get(i));
            }
        }
        return smaller;
    }

    /**
     * Returns a list of integers that are greater than or equal to the first element of the input list.
     * <p>
     * This method iterates through the input list starting from the second element and adds elements that are greater
     * </p>
     *
     * @param array the input list of integers
     * @return a list of integers that are greater than or equal to the first element of the input list
     */
    private List<Integer> getBiggerOrEqual(List<Integer> array) {
        List<Integer> biggerOrEqual = new ArrayList<>();
        for(int i = 1; i < array.size(); i++) {
            if(array.get(i) >= array.get(0)) {
                biggerOrEqual.add(array.get(i));
            }
        }
        return biggerOrEqual;
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
    public boolean sameBstsOptimized(List<Integer> arrayOne, List<Integer> arrayTwo) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
