package org.algoexpert.utils;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Utility class for logging various prompts and warnings.
 * <p>
 * This class is annotated with {@link Component} to indicate that it is a Spring-managed component.
 * </p>
 *
 * @author Jitesh Shaw
 */

@Component
public class LoggerUtil {

    /**
     * Logs a prompt message to enter the size of the array.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterArraySize(Logger LOGGER) {
        LOGGER.info("Enter the size of the array: ");
    }
    /**
     * Logs a prompt message to enter the array elements.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterArrayElements(Logger LOGGER) {
        LOGGER.info("Enter the elements of the array: ");
    }
    /**
     * Logs a prompt message to enter the target sum.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterTargetSum(Logger LOGGER) {
        LOGGER.info("Enter the target sum: ");
    }
    /**
     * Logs a warning message indicating an error occurred while reading input.
     *
     * @param LOGGER the {@link Logger} instance used to log the warning message
     * @param e the {@link IOException} that was thrown while reading input
     */
    public void warnErrorWhileReadingInput(Logger LOGGER, IOException e) {
        LOGGER.warn("Error while reading input with exception: {}", e.getMessage());
    }
    /**
     * Logs a warning message indicating an error occurred while executing an algorithm.
     *
     * @param LOGGER the {@link Logger} instance used to log the warning message
     * @param algorithmName the algorithm that was being executed when the error occurred
     */
    public void warnErrorWhileExecutingAlgorithm(Logger LOGGER, String algorithmName) {
        LOGGER.warn("Error while executing algorithm \"{}\"", algorithmName);
    }
    /**
     * Logs a prompt message to enter the size of the first array.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterFirstArraySize(Logger LOGGER) {
        LOGGER.info("Enter the size of the first array");
    }
    /**
     * Logs a prompt message to enter the first array elements.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterFirstArrayElements(Logger LOGGER) {
        LOGGER.info("Enter the elements of the first array");
    }
    /**
     * Logs a prompt message to enter the size of the second array.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterSecondArraySize(Logger LOGGER) {
        LOGGER.info("Enter the size of the second array");
    }
    /**
     * Logs a prompt message to enter the second array elements.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterSecondArrayElements(Logger LOGGER) {
        LOGGER.info("Enter the elements of the second array");
    }
    /**
     * Logs a message indicating that the two arrays represent the same Binary Search Tree (BST).
     *
     * @param LOGGER the {@link Logger} instance used to log the message
     */
    public void infoConfirmBST(Logger LOGGER) {
        LOGGER.info("The two arrays represent the same Binary Search Tree (BST)");
    }
    /**
     * Logs a message indicating that the two arrays do not represent the same Binary Search Tree (BST).
     *
     * @param LOGGER the {@link Logger} instance used to log the message
     */
    public void infoConfirmNotBST(Logger LOGGER) {
        LOGGER.info("The two arrays do not represent the same Binary Search Tree (BST)");
    }
    /**
     * Logs a prompt message to enter the number of nodes in the binary tree.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     */
    public void promptToEnterNumberOfNodesInBinaryTree(Logger LOGGER) {
        LOGGER.info("Enter the number of nodes in the Binary Tree");
    }
    /**
     * Logs a prompt message to enter the values of the nodes in the binary tree.
     *
     * @param LOGGER the {@link Logger} instance used to log the prompt message
     * @param numberOfNodes the number of nodes in the binary tree
     */
    public void promptToEnterValuesOfNodesInBinaryTree(Logger LOGGER, int numberOfNodes) {
        LOGGER.info("Enter the values of {} nodes in the Binary Tree", numberOfNodes);
    }
    /**
     * Logs a warning message indicating that an empty binary tree was provided.
     *
     * @param LOGGER the {@link Logger} instance used to log the warning message
     */
    public void warnEmptyBinaryTree(Logger LOGGER) {
        LOGGER.warn("Empty Binary Tree provided!");
    }
}
