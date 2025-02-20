package org.algoexpert.algorithms.binarysearchtrees.easy;

import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;

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
}
