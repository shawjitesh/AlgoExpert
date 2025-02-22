package org.algoexpert.algorithms.binarytrees.easy;

import lombok.Data;
import org.algoexpert.algorithms.binarytrees.BinaryTreesAlgorithms;
import org.algoexpert.algorithms.binarytrees.medium.InvertBinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes in a Binary Tree and returns a list of its branch sums ordered from a leftmost branch sum
 * to a rightmost branch sum.
 * <p> A branch sum is the sum of all values in a Binary Tree branch. A Binary Tree branch is a path of nodes in a tree
 * that starts at the root node and ends at any leaf node.
 * <p> Each BinaryTree node has an integer value, a left child node, and a right child node. Children nodes can either
 * be BinaryTree nodes themselves or None/null.
 * <pre>
 * Sample Input:
 * tree =
 * 	            1
 *           /     \
 *          2       3
 *        /   \   /   \
 *       4     5 6     7
 *      / \   /
 *     8   9 10
 * Sample Output:
 * [15, 16, 18, 10, 11]
 * // 15 == 1 + 2 + 4 + 8
 * // 16 == 1 + 2 + 4 + 9
 * // 18 == 1 + 2 + 5 + 10
 * // 10 == 1 + 3 + 6
 * // 11 == 1 + 3 + 7
 *
 * Optimal Space & Time Complexity
 * O(n) time | O(n) space - where n is the number of nodes in the Binary Tree
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class BranchSums implements BinaryTreesAlgorithms {

    /**
     * Represents a node in a Binary Tree.
     * <p>
     * Each node contains an integer value, and references to the left and right child nodes.
     * </p>
     */
    @Data
    public static class BinaryTree {

        public int value;
        public BinaryTree left;
        public BinaryTree right;

        /**
         * Constructs a Binary Tree node with the specified value.
         *
         * @param value the integer value of the node
         */
        public BinaryTree(int value) {
            this.value = value;
        }

    }

    /**
     * Calculates the branch sums of a binary tree.
     * <p>
     * This method takes the root of a binary tree and returns a list of integers representing the sums of all branches
     * in the tree. A branch sum is defined as the sum of all values from the root to a leaf node.
     * </p>
     *
     * @param root the root of the binary tree
     * @return a list of integers representing the sums of all branches in the tree
     */
    @Override
    public List<Integer> branchSums(BinaryTree root) {

        List<Integer> branchSums = new ArrayList<>();

        calculateBranchSums(root, 0, branchSums);

        return branchSums;
    }

    /**
     * Recursively calculates the branch sums of a binary tree.
     * <p>
     * This helper method traverses the binary tree, calculating the running sum of values from the root to each leaf
     * node. When a leaf node is reached, the running sum is added to the list of branch sums.
     * </p>
     *
     * @param node the current node in the binary tree
     * @param runningSum the sum of values from the root to the current node
     * @param branchSums the list to store the sums of all branches
     */
    private void calculateBranchSums(BinaryTree node, int runningSum, List<Integer> branchSums) {

        if (node == null) {
            return;
        }

        int newRunningSum = runningSum + node.value;
        if (node.left == null && node.right == null) {
            branchSums.add(newRunningSum);
        }

        calculateBranchSums(node.left, newRunningSum, branchSums);
        calculateBranchSums(node.right, newRunningSum, branchSums);
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public InvertBinaryTree.BinaryTree invertBinaryTree(InvertBinaryTree.BinaryTree tree) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
