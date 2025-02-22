package org.algoexpert.algorithms.binarysearchtrees.veryhard;

import lombok.Data;
import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;
import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.algoexpert.algorithms.binarysearchtrees.medium.ConstructBST;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes in an array of integers and returns an array of the same length, where each element in
 * the output array corresponds to the number of integers in the input array that are to the right of the relevant index
 * and that are strictly smaller than the integer at that index.
 * <p> In other words, the value at output[i] represents the number of integers that are to the right of I and that are
 * strictly smaller than input[i].
 * <pre>
 * Sample Input:
 * array = [8, 5, 11, -1, 3, 4, 2]
 * Sample Output:
 * [5, 4, 4, 0, 1, 1, 0]
 * // There are 5 integers smaller than 8 to the right of it.
 * // There are 4 integers smaller than 5 to the right of it.
 * // There are 4 integers smaller than 11 to the right of it.
 * // Etc..
 *
 * Optimal Space & Time Complexity
 * Average case: when the created BST is balanced O(log(n)) time | O(n) space - where n is the length of the array ||
 * Worst case: when the created BST is like a linked list O(n^2) time | O(n) space
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class RightSmallerThan implements BinarySearchTreesAlgorithms {

    /**
     * Finds the number of elements to the right of each element in the array that are smaller than the element itself.
     * <p>
     * This method constructs a special Binary Search Tree (BST) to efficiently count the number of smaller elements to
     * the right of each element in the input array. It iterates through the array in reverse order, inserting each
     * element into the BST and updating the count of smaller elements. The result is a list of integers where each
     * integer represents the count of smaller elements to the right for the corresponding element in the input array.
     * </p>
     *
     * @param array the list of integers to process
     * @return a list of integers where each integer is the count of smaller elements to the right for the corresponding
     * element in the input array
     */
    @Override
    public List<Integer> rightSmallerThan(List<Integer> array) {

        if (array.isEmpty()) {
            return new ArrayList<>();
        }

        int lastIdx = array.size() - 1;
        SpecialBST specialBST = new SpecialBST(array.get(lastIdx), lastIdx, 0);
        for (int i = array.size() - 2; i >= 0; i--) {
            specialBST.insert(array.get(i), i);
        }

        List<Integer> rightSmallerCounts = new ArrayList<>(array);
        getRightSmallerCounts(specialBST, rightSmallerCounts);

        return rightSmallerCounts;
    }

    /**
     * Updates the list of right smaller counts for each element in the array.
     * <p>
     * This method traverses the given special Binary Search Tree (BST) and updates the list of right smaller counts
     * for each element in the array. It sets the count of smaller elements to the right for each element based on the
     * information stored in the special BST nodes.
     * </p>
     *
     * @param specialBST the root of the special BST used to count smaller elements to the right
     * @param rightSmallerCounts the list to update with the counts of smaller elements to the right for each element
     */
    private void getRightSmallerCounts(SpecialBST specialBST, List<Integer> rightSmallerCounts) {

        if (specialBST == null) {
            return;
        }

        rightSmallerCounts.set(specialBST.idx, specialBST.numSmallerAtInsertTime);
        getRightSmallerCounts(specialBST.left, rightSmallerCounts);
        getRightSmallerCounts(specialBST.right, rightSmallerCounts);
    }

    /**
     * Represents a node in a special Binary Search Tree (BST) used to count the number of smaller elements to the right
     * of each element in an array.
     * <p>
     * This class provides methods to construct a new node and insert new values into the special BST. Each node stores
     * the value, index, number of smaller elements to the right at the time of insertion, left subtree size, and
     * references to the left and right child nodes.
     * </p>
     *
     * @author Jitesh Shaw
     */
    @Data
    static class SpecialBST {

        int value;
        int idx;
        int numSmallerAtInsertTime;
        int leftSubtreeSize;
        SpecialBST left;
        SpecialBST right;

        /**
         * Constructs a new node for the special Binary Search Tree (BST).
         * <p>
         * This constructor initializes a node with the given value, index, and the number of smaller elements to the
         * right at the time of insertion. It also initializes the left subtree size to 0 and sets the left and right
         * child nodes to null.
         * </p>
         *
         * @param value the value of the node
         * @param idx the index of the node in the original array
         * @param numSmallerAtInsertTime the number of smaller elements to the right at the time of insertion
         */
        SpecialBST(int value, int idx, int numSmallerAtInsertTime) {
            this.value = value;
            this.idx = idx;
            this.numSmallerAtInsertTime = numSmallerAtInsertTime;
            leftSubtreeSize = 0;
            left = null;
            right = null;
        }

        /**
         * Inserts a new value into the special Binary Search Tree (BST).
         * <p>
         * This method inserts a new node with the given value and index into the special BST. It initializes the number
         * of smaller elements to the right at the time of insertion to 0 and calls the helper method to perform the
         * actual insertion.
         * </p>
         *
         * @param value the value of the node to insert
         * @param idx the index of the node in the original array
         */
        void insert(int value, int idx) {
            insertHelper(value, idx, 0);
        }

        /**
         * Helper method to insert a new value into the special Binary Search Tree (BST).
         * <p>
         * This method recursively inserts a new node with the given value and index into the special BST. It updates
         * the number of smaller elements to the right at the time of insertion and adjusts the left subtree size
         * accordingly.
         * </p>
         *
         * @param value the value of the node to insert
         * @param idx the index of the node in the original array
         * @param numSmallerAtInsertTime the number of smaller elements to the right at the time of insertion
         */
        private void insertHelper(int value, int idx, int numSmallerAtInsertTime) {

            if (value < this.value) {
                leftSubtreeSize++;
                if (left == null) {
                    left = new SpecialBST(value, idx, numSmallerAtInsertTime);
                } else {
                    left.insertHelper(value, idx, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubtreeSize;
                if (value > this.value) {
                    numSmallerAtInsertTime++;
                }
                if (right == null) {
                    right = new SpecialBST(value, idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, numSmallerAtInsertTime);
                }
            }
        }
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

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public boolean sameBstsOptimized(List<Integer> arrayOne, List<Integer> arrayTwo) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
