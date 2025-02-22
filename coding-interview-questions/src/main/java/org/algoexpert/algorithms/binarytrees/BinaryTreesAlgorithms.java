package org.algoexpert.algorithms.binarytrees;

import org.algoexpert.algorithms.binarytrees.easy.BranchSums;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Interface for Binary Tree algorithms.
 * <p>
 * This interface defines methods for performing various operations on a Binary Tree. Implementations of this interface
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
public interface BinaryTreesAlgorithms {

    /**
     * Calculates the branch sums of a binary tree.
     *
     * @param root the root of the binary tree
     * @return a list of integers representing the sums of all branches in the tree
     */
    List<Integer> branchSums(BranchSums.BinaryTree root);
}
