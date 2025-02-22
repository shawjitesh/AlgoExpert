package org.algoexpert.services;

import lombok.extern.slf4j.Slf4j;
import org.algoexpert.algorithms.binarysearchtrees.easy.FindClosestValueInBST;
import org.algoexpert.algorithms.binarysearchtrees.hard.SameBSTs;
import org.algoexpert.algorithms.binarysearchtrees.medium.ConstructBST;
import org.algoexpert.algorithms.binarysearchtrees.veryhard.RightSmallerThan;
import org.algoexpert.algorithms.binarysearchtrees.veryhard.SameBSTsOptimized;
import org.algoexpert.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.algoexpert.utils.AlgorithmNames.*;

/**
 * Service class for executing binarySearchTree-related algorithms.
 * <p>
 * This class provides methods to execute various algorithms related to binarySearchTrees. It includes methods to
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
public class BinarySearchTreesService {

    private final LoggerUtil loggerUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(BinarySearchTreesService.class);
    private final BufferedReader bufferedReader;

    /**
     * Constructs an instance of the BinarySearchTreesService.
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
    public BinarySearchTreesService(LoggerUtil loggerUtil, BufferedReader bufferedReader) {
        this.loggerUtil = loggerUtil;
        this.bufferedReader = bufferedReader;
    }

    /**
     * Constructs a Binary Search Tree (BST) from an array of node values.
     * <p>
     * This method takes an array of integers representing the node values and constructs a BST by inserting each value
     * into its appropriate position in the tree. The first value in the array is used as the root of the BST.
     * </p>
     *
     * @param bstNodes an array of integers representing the node values to be inserted into the BST
     * @return the root node of the constructed BST
     */
    private FindClosestValueInBST.BST getBST(int[] bstNodes) {

        FindClosestValueInBST.BST root = new FindClosestValueInBST.BST(bstNodes[0]);

        for (int i = 1; i < bstNodes.length; i++) {
            FindClosestValueInBST.BST currentNode = root;
            FindClosestValueInBST.BST previousNode = null;

            while (currentNode != null) {
                previousNode = currentNode;
                if (bstNodes[i] < currentNode.value) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }

            if (bstNodes[i] < previousNode.value) {
                previousNode.left = new FindClosestValueInBST.BST(bstNodes[i]);
            } else {
                previousNode.right = new FindClosestValueInBST.BST(bstNodes[i]);
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
            case FIND_CLOSEST_VALUE_IN_BST:
                try {
                    executeFindClosestValueInBst();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, FIND_CLOSEST_VALUE_IN_BST);
                    return false;
                }
                break;
            case CONSTRUCT_BST:
                try {
                    executeConstructBst();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, CONSTRUCT_BST);
                    return false;
                }
                break;
            case SAME_BSTS:
                try {
                    executeSameBsts();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, SAME_BSTS);
                    return false;
                }
                break;
            case SAME_BSTS_OPTIMIZED:
                try {
                    executeSameBstsOptimized();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, SAME_BSTS_OPTIMIZED);
                    return false;
                }
                break;
            case RIGHT_SMALLER_THAN:
                try {
                    executeRightSmallerThan();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, RIGHT_SMALLER_THAN);
                    return false;
                }
                break;
            default:
                LOGGER.info("Algorithm not available");
        }

        return true;
    }

    /**
     * Executes the "Find Closest Value in BST" algorithm.
     * <p>
     * This method reads the number of nodes, the values of the nodes, and the target value from the standard input.
     * It constructs a Binary Search Tree (BST) from the node values and then finds the value in the BST that is closest
     * to the target value using the {@link FindClosestValueInBST#findClosestValueInBst(FindClosestValueInBST.BST, int)}
     * method. The result is logged to the console.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeFindClosestValueInBst() {

        try {
            LOGGER.info("Enter the number of nodes in the BST");
            int n = Integer.parseInt(bufferedReader.readLine());

            int[] bstNodes = new int[n];
            LOGGER.info("Enter the values of the nodes in the BST");
            for (int i = 0; i < n; i++) {
                LOGGER.info("Enter the value of node {}", i + 1);
                bstNodes[i] = Integer.parseInt(bufferedReader.readLine());
            }

            FindClosestValueInBST.BST tree = getBST(bstNodes);
            LOGGER.info("Enter the target value");
            int target = Integer.parseInt(bufferedReader.readLine());

            int closestValue = new FindClosestValueInBST().findClosestValueInBst(tree, target);
            LOGGER.info("The value in the BST that is closest to the target value is: {}", closestValue);
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }

    /**
     * Executes the "Construct BST" algorithm.
     * <p>
     * This method reads the number of operations to perform on the Binary Search Tree (BST) and the details of each
     * operation from the standard input. It constructs a BST by performing the specified operations in sequence.
     * The operations can be "insert", "contains", or "remove". After performing all the operations, it logs the
     * constructed BST.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeConstructBst() {

        try {
            LOGGER.info("Enter the number of operations to perform on the BST");
            int n = Integer.parseInt(bufferedReader.readLine());

            LOGGER.info("Enter the {} operations to perform on the BST", n);
            List<String[]> bstOperations = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                LOGGER.info("Enter the operation to perform (insert, contains, or remove)");
                String operation = bufferedReader.readLine();

                LOGGER.info("Enter the value to perform the operation on");
                String value = bufferedReader.readLine();

                bstOperations.add(new String[]{operation, value});
            }

            ConstructBST.BST bst = new ConstructBST().constructBst(bstOperations);
            if (bst != null) {
                LOGGER.info("BST constructed successfully: {}", bst);
            } else {
                LOGGER.info("The Constructed BST has no nodes to display, after applying all the operations");
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }

    /**
     * Executes the "Same BSTs" algorithm.
     * <p>
     * This method reads the sizes and elements of two arrays from the standard input. It then checks if the two arrays
     * represent the same Binary Search Tree (BST) using the {@link SameBSTs#sameBsts(List, List)} method. The result is
     * logged to the console.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeSameBsts() {

        try {
            loggerUtil.promptToEnterFirstArraySize(LOGGER);
            int n = Integer.parseInt(bufferedReader.readLine());

            Integer[] arrayOne = new Integer[n];
            loggerUtil.promptToEnterFirstArrayElements(LOGGER);
            for (int i = 0; i < n; i++) {
                arrayOne[i] = Integer.parseInt(bufferedReader.readLine());
            }

            loggerUtil.promptToEnterSecondArraySize(LOGGER);
            int m = Integer.parseInt(bufferedReader.readLine());

            Integer[] arrayTwo = new Integer[m];
            loggerUtil.promptToEnterSecondArrayElements(LOGGER);
            for (int i = 0; i < m; i++) {
                arrayTwo[i] = Integer.parseInt(bufferedReader.readLine());
            }

            boolean result = new SameBSTs().sameBsts(List.of(arrayOne), List.of(arrayTwo));
            if (result) {
                loggerUtil.infoConfirmBST(LOGGER);
            } else {
                loggerUtil.infoConfirmNotBST(LOGGER);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }

    /**
     * Executes the "Same BSTs Optimized" algorithm.
     * <p>
     * This method reads the sizes and elements of two arrays from the standard input. It then checks if the two arrays
     * represent the same Binary Search Tree (BST) using the {@link SameBSTsOptimized#sameBstsOptimized(List, List)}
     * method. The result is logged to the console.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeSameBstsOptimized() {

        try {
            loggerUtil.promptToEnterFirstArraySize(LOGGER);
            int n = Integer.parseInt(bufferedReader.readLine());

            Integer[] arrayOne = new Integer[n];
            loggerUtil.promptToEnterFirstArrayElements(LOGGER);
            for (int i = 0; i < n; i++) {
                arrayOne[i] = Integer.parseInt(bufferedReader.readLine());
            }

            loggerUtil.promptToEnterSecondArraySize(LOGGER);
            int m = Integer.parseInt(bufferedReader.readLine());

            Integer[] arrayTwo = new Integer[m];
            loggerUtil.promptToEnterSecondArrayElements(LOGGER);
            for (int i = 0; i < m; i++) {
                arrayTwo[i] = Integer.parseInt(bufferedReader.readLine());
            }

            boolean result = new SameBSTsOptimized().sameBstsOptimized(List.of(arrayOne), List.of(arrayTwo));
            if (result) {
                loggerUtil.infoConfirmBST(LOGGER);
            } else {
                loggerUtil.infoConfirmNotBST(LOGGER);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }

    /**
     * Executes the "Right Smaller Than" algorithm.
     * <p>
     * This method reads the size and elements of an array from the standard input. It then finds the number of elements
     * to the right of each element in the array that are smaller than the element itself using the
     * {@link RightSmallerThan#rightSmallerThan(List)} method. The result is logged to the console.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeRightSmallerThan() {

        try {
            loggerUtil.promptToEnterArraySize(LOGGER);
            int n = Integer.parseInt(bufferedReader.readLine());

            Integer[] array = new Integer[n];
            loggerUtil.promptToEnterArrayElements(LOGGER);

            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(bufferedReader.readLine());
            }

            List<Integer> result = new RightSmallerThan().rightSmallerThan(Arrays.asList(array));
            if (result.isEmpty()) {
                LOGGER.info("No such elements present, as the input array is sorted in ascending order");
            } else {
                LOGGER.info("Right Smaller Counts: {}", result);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }
}
