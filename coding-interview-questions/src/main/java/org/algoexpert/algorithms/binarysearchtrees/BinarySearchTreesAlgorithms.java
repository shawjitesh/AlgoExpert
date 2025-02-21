package org.algoexpert.algorithms.binarysearchtrees;

import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.algoexpert.algorithms.binarysearchtrees.medium.ConstructBST;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Interface for Binary Search Tree (BST) algorithms.
 * <p>
 * This interface defines methods for performing various operations on a BST. Implementations of this interface
 * should provide the logic for these operations.
 * </p>
 * <p>
 * The interface is annotated with {@link Component}, indicating that it is a Spring component and can be injected as a
 * dependency where needed.
 * </p>
 *
 * @author Jitesh Shaw
 */

@Component
public interface BinarySearchTreesAlgorithms {

    /**
     * Finds the value in the Binary Search Tree (BST) that is closest to the given target value.
     *
     * @param tree the root node of the BST
     * @param target the target value to find the closest value to
     * @return the value in the BST that is closest to the target value
     */
    int findClosestValueInBst(FindClosestValueInBST.BST tree, int target);
    /**
     * Constructs a Binary Search Tree (BST) by performing a series of operations.
     *
     * @param bstOperations a list of operations to perform on the BST, where each operation is represented as a string
     *                      array with the operation type and the value
     * @return the constructed BST after performing all the operations
     * @throws IllegalArgumentException if an invalid operation type is encountered
     */
    ConstructBST.BST constructBst(List<String[]> bstOperations);
    /**
     * Determines if two arrays represent the same Binary Search Tree (BST).
     *
     * @param arrayOne the first array representing a BST
     * @param arrayTwo the second array representing a BST
     * @return true if the two arrays represent the same BST, false otherwise
     */
    boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo);
    /**
     * Determines if two arrays represent the same Binary Search Tree (BST) in optimized approach.
     *
     * @param arrayOne the first array representing a BST
     * @param arrayTwo the second array representing a BST
     * @return true if the two arrays represent the same BST, false otherwise
     */
    boolean sameBstsOptimized(List<Integer> arrayOne, List<Integer> arrayTwo);
}
