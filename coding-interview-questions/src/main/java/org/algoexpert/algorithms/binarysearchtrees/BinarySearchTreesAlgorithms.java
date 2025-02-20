package org.algoexpert.algorithms.binarysearchtrees;

import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.springframework.stereotype.Component;

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
}
