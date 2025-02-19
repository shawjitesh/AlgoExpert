package org.algoexpert.util;

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
}
