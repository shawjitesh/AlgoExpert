package org.algoexpert.algorithms.binarytrees.medium;

import lombok.Data;

import org.algoexpert.algorithms.binarytrees.BinaryTreesAlgorithms;
import org.algoexpert.algorithms.binarytrees.easy.BranchSums;

import java.util.List;

/**
 * Write a function that takes in a Binary Tree and inverts it. In other words, the function should swap every left node
 * in the tree for its corresponding right node.
 * <p> Each BinaryTree node has an integer value, left child node, and a right child node. Children nodes can either be
 * BinaryTree nodes themselves or None/null.
 * <pre>
 * Sample Input:
 * tree =
 *              1
 *           /     \
 *          2       3
 *        /   \   /   \
 *       4     5 6     7
 *      / \
 *     8   9
 * Sample Output:
 *              1
 *           /     \
 *          3       2
 *        /   \   /   \
 *       7     6 5     4
 *                    / \
 *                   9   8
 *
 * Optimal Space & Time Complexity
 * O(n) time | O(d) space - where n is the number of nodes in the Binary Tree and d is the depth (height) of the Binary
 * Tree
 * </pre>
 *
 * @author Jitesh Shaw
 */

public class InvertBinaryTree implements BinaryTreesAlgorithms {

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
     * Inverts a binary tree.
     * <p>
     * This method takes the root of a binary tree and inverts it, swapping the left and right children of all nodes in
     * the tree. The inversion is performed in-place, modifying the original tree structure.
     * </p>
     *
     * @param tree the root of the binary tree to be inverted
     * @return the root of the inverted binary tree
     */
    @Override
    public InvertBinaryTree.BinaryTree invertBinaryTree(BinaryTree tree) {

        if (tree != null) {
            invertBinaryTreeHelper(tree);
        }

        return tree;
    }

    /**
     * Recursively inverts a binary tree.
     * <p>
     * This helper method traverses the binary tree and swaps the left and right children of each node. The inversion is
     * performed in-place, modifying the original tree structure.
     * </p>
     *
     * @param tree the current node in the binary tree
     */
    private void invertBinaryTreeHelper(BinaryTree tree) {

        if (tree == null) {
            return;
        }

        swapLeftAndRight(tree);
        invertBinaryTreeHelper(tree.left);
        invertBinaryTreeHelper(tree.right);
    }

    /**
     * Swaps the left and right children of a binary tree node.
     * <p>
     * This method takes a binary tree node and swaps its left and right child nodes. The swap is performed in-place,
     * modifying the original tree structure.
     * </p>
     *
     * @param tree the binary tree node whose children are to be swapped
     */
    private void swapLeftAndRight(BinaryTree tree) {

        BinaryTree left = tree.left;

        tree.left = tree.right;
        tree.right = left;
    }

    /**
     * This method throws an {@link UnsupportedOperationException} indicating that the implementation for this algorithm
     * is not provided in this class.
     */
    @Override
    public List<Integer> branchSums(BranchSums.BinaryTree root) {
        throw new UnsupportedOperationException("Implementation for this algorithm is not a part of this class");
    }
}
