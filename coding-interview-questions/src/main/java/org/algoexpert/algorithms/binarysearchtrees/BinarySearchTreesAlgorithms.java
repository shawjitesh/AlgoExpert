package org.algoexpert.algorithms.binarysearchtrees;

import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.springframework.stereotype.Component;

@Component
public interface BinarySearchTreesAlgorithms {

    int findClosestValueInBst(FindClosestValueInBST.BST tree, int target);
}
