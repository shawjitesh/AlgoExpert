package org.algoexpert.services;

import lombok.extern.slf4j.Slf4j;

import org.algoexpert.algorithms.binarytrees.easy.BranchSums;
import org.algoexpert.algorithms.binarytrees.medium.InvertBinaryTree;
import org.algoexpert.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.algoexpert.utils.AlgorithmNames.BRANCH_SUMS;
import static org.algoexpert.utils.AlgorithmNames.INVERT_BINARY_TREE;

/**
 * Service class for executing binaryTree-related algorithms.
 * <p>
 * This class provides methods to execute various algorithms related to binaryTrees. It includes methods to
 * execute the different algorithms and a method to execute an algorithm based on the provided algorithm name.
 * </p>
 * <p>
 * The class uses the {@link Slf4j} annotation for logging and the {@link Service} annotation to indicate that it is a
 * Spring service component.
 * </p>
 *
 * @author Jitesh Shaw
 */

@Service
@Slf4j
public class BinaryTreesService {

    private final LoggerUtil loggerUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreesService.class);
    private final BufferedReader bufferedReader;

    /**
     * Constructs an instance of the BinaryTreesService.
     * <p>
     * This constructor is annotated with {@link Autowired}, indicating that it should be used by Spring's dependency
     * injection to create an instance of the service. It initializes the {@code loggerUtil} field with the provided
     * instance.
     * </p>
     *
     * @param loggerUtil an instance of {@link LoggerUtil} used for logging prompts and warnings
     * @param bufferedReader the BufferedReader instance for reading input
     */
    @Autowired
    public BinaryTreesService(LoggerUtil loggerUtil, BufferedReader bufferedReader) {
        this.loggerUtil = loggerUtil;
        this.bufferedReader = bufferedReader;
    }

    /**
     * Constructs a binary tree from an array of node values.
     * <p>
     * This method takes an array of integers representing the values of the nodes in a binary tree and constructs the
     * binary tree in a level-order manner. The first element of the array becomes the root of the tree, and later
     * elements are added as left and right children in the order they appear in the array.
     * </p>
     * <p>
     * If the input array is empty, the method returns null.
     * </p>
     *
     * @param binaryTreeNodes an array of integers representing the values of the nodes in the binary tree
     * @return the root of the constructed binary tree, or null if the input array is empty
     */
    private BranchSums.BinaryTree getBinaryTreeForBranchSums(int[] binaryTreeNodes) {

        int n = binaryTreeNodes.length;
        if(n == 0) {
            return null;
        }

        BranchSums.BinaryTree root = new BranchSums.BinaryTree(binaryTreeNodes[0]);
        BranchSums.BinaryTree[] treeNodes = new BranchSums.BinaryTree[n];
        treeNodes[0] = root;

        for (int i = 1; i < n; i++) {
            BranchSums.BinaryTree newTreeNode = new BranchSums.BinaryTree(binaryTreeNodes[i]);
            treeNodes[i] = newTreeNode;

            int parentIdx = (i - 1) / 2;
            if (i % 2 != 0) {
                treeNodes[parentIdx].left = newTreeNode;
            } else {
                treeNodes[parentIdx].right = newTreeNode;
            }
        }

        return root;
    }

    /**
     * Constructs a binary tree from an array of node values.
     * <p>
     * This method takes an array of integers representing the values of the nodes in a binary tree and constructs the
     * binary tree in a level-order manner. The first element of the array becomes the root of the tree, and later
     * elements are added as left and right children in the order they appear in the array.
     * </p>
     * <p>
     * If the input array is empty, the method returns null.
     * </p>
     *
     * @param binaryTreeNodes an array of integers representing the values of the nodes in the binary tree
     * @return the root of the constructed binary tree, or null if the input array is empty
     */
    private InvertBinaryTree.BinaryTree getBinaryTreeForInvertBinaryTree(int[] binaryTreeNodes) {

        int n = binaryTreeNodes.length;
        if(n == 0) {
            return null;
        }

        InvertBinaryTree.BinaryTree root = new InvertBinaryTree.BinaryTree(binaryTreeNodes[0]);
        InvertBinaryTree.BinaryTree[] treeNodes = new InvertBinaryTree.BinaryTree[n];
        treeNodes[0] = root;

        for (int i = 1; i < n; i++) {
            InvertBinaryTree.BinaryTree newTreeNode = new InvertBinaryTree.BinaryTree(binaryTreeNodes[i]);
            treeNodes[i] = newTreeNode;

            int parentIdx = (i - 1) / 2;
            if (i % 2 != 0) {
                treeNodes[parentIdx].left = newTreeNode;
            } else {
                treeNodes[parentIdx].right = newTreeNode;
            }
        }

        return root;
    }

    /**
     * Executes the specified algorithm based on the provided algorithm name.
     * <p>
     * This method uses a switch statement to determine which algorithm to execute based on the given algorithm name.
     * If the algorithm name does not match any known algorithms, it logs that the algorithm is not available.
     * </p>
     *
     * @param algorithmName the name of the algorithm to execute
     */
    public boolean executeAlgorithm(String algorithmName) {

        switch (algorithmName) {
            case BRANCH_SUMS:
                try {
                    executeBranchSums();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, BRANCH_SUMS);
                    return false;
                }
                break;
            case INVERT_BINARY_TREE:
                try {
                    executeInvertBinaryTree();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, INVERT_BINARY_TREE);
                    return false;
                }
                break;
            default:
                LOGGER.info("Algorithm not available");
        }

        return true;
    }

    /**
     * Executes the Branch Sums algorithm on a binary tree.
     * <p>
     * This method prompts the user to enter the number of nodes in the binary tree and their values. It then constructs
     * the binary tree using the provided values and calculates the branch sums. The branch sums are logged as output.
     * </p>
     * <p>
     * If an empty binary tree is provided, a warning is logged. If an error occurs while reading input, a warning is
     * logged with the error details.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeBranchSums() {

        try {
            loggerUtil.promptToEnterNumberOfNodesInBinaryTree(LOGGER);
            int n = Integer.parseInt(bufferedReader.readLine());

            int[] binaryTreeNodes = new int[n];
            loggerUtil.promptToEnterValuesOfNodesInBinaryTree(LOGGER, n);
            for (int i = 0; i < n; i++) {
                binaryTreeNodes[i] = Integer.parseInt(bufferedReader.readLine());
            }

            BranchSums.BinaryTree binaryTree = getBinaryTreeForBranchSums(binaryTreeNodes);
            if (binaryTree != null) {
                List<Integer> branchSums = new BranchSums().branchSums(binaryTree);
                LOGGER.info("The branch sums for the provided Binary Tree are {}", branchSums);
            } else {
                loggerUtil.warnEmptyBinaryTree(LOGGER);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }

    /**
     * Executes the Invert Binary Tree algorithm on a binary tree.
     * <p>
     * This method prompts the user to enter the number of nodes in the binary tree and their values. It then constructs
     * the binary tree using the provided values and inverts the binary tree. The original and inverted binary trees are
     * logged as output.
     * </p>
     * <p>
     * If an empty binary tree is provided, a warning is logged. If an error occurs while reading input, a warning is
     * logged with the error details.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeInvertBinaryTree() {

        try {
            loggerUtil.promptToEnterNumberOfNodesInBinaryTree(LOGGER);
            int n = Integer.parseInt(bufferedReader.readLine());

            int[] binaryTreeNodes = new int[n];
            loggerUtil.promptToEnterValuesOfNodesInBinaryTree(LOGGER, n);
            for (int i = 0; i < n; i++) {
                binaryTreeNodes[i] = Integer.parseInt(bufferedReader.readLine());
            }

            InvertBinaryTree.BinaryTree binaryTree = getBinaryTreeForInvertBinaryTree(binaryTreeNodes);
            if (binaryTree != null) {
                LOGGER.info("Provided Binary Tree: {}", binaryTree);
                InvertBinaryTree.BinaryTree invertedBinaryTree = new InvertBinaryTree().invertBinaryTree(binaryTree);
                LOGGER.info("The inverted Binary Tree is: {}", invertedBinaryTree);
            } else {
                loggerUtil.warnEmptyBinaryTree(LOGGER);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }
}
