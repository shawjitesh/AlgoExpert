package org.algoexpert.algorithms.binarysearchtrees.medium;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;
import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Write a BST class for a Binary Search Tree. The class should support:
 * <ul>
 *     <li>Inserting values with the insert method.</li>
 *     <li>Removing values with the remove method; this method should only remove the first instance of a given
 *          value.</li>
 *     <li>Searching for values with the contains method.</li>
 * </ul>
 * <p> Note that you can't remove values from a single-node tree. In other words, calling the remove method on a
 * single-node tree should simply do nothing.
 * <p> Each BST node has an integer value, a left child node, and a right child node. A node is said to be a valid BST
 * node if and only if it satisfies the BST property: its value is strictly greater than the values of every node to its
 * left; its value is less than or equal to the values of every node to its right; and its children nodes are either
 * valid BST nodes themselves or None/null.
 * <pre>
 * Sample Usage:
 * // Assume the following BST has already been created:
 *
 *             10
 *          /      \
 *         5       15
 *       /   \    /   \
 *      2     5  13   22
 *     /          \
 *    1           14
 *
 * // All operations below are performed sequentially.
 * insert(12):
 *             10
 *          /      \
 *         5       15
 *       /   \    /   \
 *      2     5  13   22
 *     /        / \
 *    1        12  14
 * remove(10):
 *             12
 *          /      \
 *         5       15
 *       /   \    /   \
 *      2     5  13   22
 *     /           \
 *    1            14
 * contains(15): true
 *
 * Optimal Space & Time Complexity:
 * Average (all 3 methods): O(log(n)) time | O(1) space - where n is the number of nodes in the BST ||
 * Worst (all 3 methods): O(n) time | O(1) space - where n is the number of nodes in the BST
 * </pre>
 *
 * @author Jitesh Shaw
 */

@Slf4j
public class ConstructBST implements BinarySearchTreesAlgorithms {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstructBST.class);

    /**
     * Represents a node in a Binary Search Tree (BST).
     * <p>
     * Each node contains an integer value, and references to the left and right child nodes.
     * </p>
     */
    @Data
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

        /**
         * Inserts a new value into the Binary Search Tree (BST).
         * <p>
         * This method inserts the specified value into the BST while maintaining the BST property.
         * If the value is less than the current node's value, it is inserted into the left subtree.
         * If the value is greater than or equal to the current node's value, it is inserted into the right subtree.
         * </p>
         *
         * @param value the value to be inserted into the BST
         * @return the current BST node after the insertion
         */
        public BST insert(int value) {

            BST currentNode = this;

            while (true) {
                if (value < currentNode.value) {
                    if (currentNode.left == null) {
                        currentNode.left = new BST(value);
                        break;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        currentNode.right = new BST(value);
                        break;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }

            return this;
        }

        /**
         * Checks if the Binary Search Tree (BST) contains the specified value.
         * <p>
         * This method searches for the specified value in the BST. If the value is less than the current node's value,
         * it searches the left subtree. If the value is greater than the current node's value, it searches the right
         * subtree. If the value is equal to the current node's value, it returns true.
         * </p>
         *
         * @param value the value to search for in the BST
         * @return true if the value is found in the BST, false otherwise
         */
        public boolean contains(int value) {

            BST currentNode = this;

            while (currentNode != null) {
                if (value < currentNode.value) {
                    currentNode = currentNode.left;
                } else if (value > currentNode.value) {
                    currentNode = currentNode.right;
                } else {
                    return true;
                }
            }

            return false;
        }

        /**
         * Removes the specified value from the Binary Search Tree (BST).
         * <p>
         * This method removes the specified value from the BST while maintaining the BST property.
         * It calls a helper method to perform the removal operation and returns the current BST node after the removal.
         * </p>
         *
         * @param value the value to be removed from the BST
         * @return the current BST node after the removal
         */
        public BST remove(int value) {
            remove(value, null);
            return this;
        }

        /**
         * Removes the specified value from the Binary Search Tree (BST).
         * <p>
         * This method removes the specified value from the BST while maintaining the BST property.
         * It recursively searches for the node to be removed. If the node has two children, it replaces the node's
         * value with the minimum value from the right subtree and removes that minimum value node. If the node has only
         * one child or no children, it adjusts the parent's reference to bypass the node.
         * </p>
         *
         * @param value the value to be removed from the BST
         * @param parent the parent node of the current node, used to adjust the parent's reference when removing the
         *               node
         */
        private void remove(int value, BST parent) {

            BST currentNode = this;

            while (currentNode != null) {
                if (value < currentNode.value) {
                    parent = currentNode;
                    currentNode = currentNode.left;
                } else if (value > currentNode.value) {
                    parent = currentNode;
                    currentNode = currentNode.right;
                } else {
                    if (currentNode.left != null && currentNode.right != null) {
                        currentNode.value = currentNode.right.getMinValue();
                        currentNode.right.remove(currentNode.value, currentNode);
                    } else if (parent == null) {
                        if (currentNode.left != null) {
                            currentNode.value = currentNode.left.value;
                            currentNode.right = currentNode.left.right;
                            currentNode.left = currentNode.left.left;
                        } else if (currentNode.right != null) {
                            currentNode.value = currentNode.right.value;
                            currentNode.left = currentNode.right.left;
                            currentNode.right = currentNode.right.right;
                        }
                    } else if (parent.left == currentNode) {
                        parent.left = (currentNode.left != null) ? currentNode.left : currentNode.right;
                    } else if (parent.right == currentNode) {
                        parent.right = (currentNode.left != null) ? currentNode.left : currentNode.right;
                    }
                    break;
                }
            }
        }

        /**
         * Retrieves the minimum value in the Binary Search Tree (BST).
         * <p>
         * This method traverses the left subtree of the BST to find the node with the smallest value.
         * If the current node has no left child, it returns the value of the current node.
         * Otherwise, it recursively calls itself on the left child until it finds the node with no left child.
         * </p>
         *
         * @return the minimum value in the BST
         */
        private int getMinValue() {

            if (this.left == null) {
                return this.value;
            } else {
                return this.left.getMinValue();
            }
        }

    }

    /**
     * Constructs a Binary Search Tree (BST) by performing a series of operations.
     * <p>
     * This method takes a list of operations to perform on the BST. Each operation is represented as a string array
     * where the first element is the operation type ("insert", "contains", or "remove"), and the second element is the
     * value to perform the operation on. The method processes each operation in sequence and modifies the BST
     * accordingly.
     * </p>
     *
     * @param bstOperations a list of operations to perform on the BST, where each operation is represented as a string
     *                      array with the operation type and the value
     * @return the constructed BST after performing all the operations
     * @throws IllegalArgumentException if an invalid operation type is encountered
     */
    @Override
    public BST constructBst(List<String[]> bstOperations) {

        BST bst = null;

        for(String[] operation : bstOperations) {
            String operationToDo = operation[0];
            int value = Integer.parseInt(operation[1]);
            switch(operationToDo) {
                case "insert":
                    if (bst == null) {
                        bst = new BST(value);
                    } else {
                        bst = bst.insert(value);
                    }
                    LOGGER.info("Value {} inserted. BST: {}", value, bst);
                    break;
                case "contains":
                    if (bst != null) {
                        boolean contains = bst.contains(value);
                        LOGGER.info("BST contains {}: {}", value, contains);
                    } else {
                        LOGGER.info("BST is empty. Cannot perform 'contains' operation.");
                    }
                    break;
                case "remove":
                    if (bst != null) {
                        if(bst.left == null && bst.right == null) {
                            LOGGER.info("BST has only one node. Cannot perform 'remove' operation.");
                        } else {
                            bst = bst.remove(value);
                            LOGGER.info("Value {} removed. BST: {}", value, bst);
                        }
                    } else {
                        LOGGER.info("BST is empty. Cannot perform 'remove' operation.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation: " + operationToDo);
            }
        }

        return bst;
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
