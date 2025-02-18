package org.algoexpert.services;

import lombok.extern.slf4j.Slf4j;
import org.algoexpert.algorithms.arrays.easy.TwoNumberSum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.algoexpert.util.AlgorithmNames.TWO_NUMBER_SUM;

/**
 * Service class for executing array-related algorithms.
 * <p>
 * This class provides methods to execute various algorithms related to arrays. It includes methods to execute the
 * different algorithms and a method to execute an algorithm based on the provided algorithm name.
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
public class ArraysService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArraysService.class);

    /**
     * Executes the "Two Number Sum" algorithm.
     * <p>
     * This method reads the array size, array elements, and target sum from the standard input. It then uses the
     * {@link TwoNumberSum#twoNumberSum(int[], int)} method to find a pair of numbers in the array that sum up to the
     * target sum. If such a pair is found, it logs the pair; otherwise, it logs that no pair was found.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeTwoNumberSum() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            LOGGER.info("Enter the array size");
            int arraySize = Integer.parseInt(bufferedReader.readLine());
            int[] array = new int[arraySize];

            LOGGER.info("Enter the array elements");
            for (int i = 0; i < arraySize; i++) {
                array[i] = Integer.parseInt(bufferedReader.readLine());
            }

            LOGGER.info("Enter the target sum");
            int targetSum = Integer.parseInt(bufferedReader.readLine());

            int[] result = new TwoNumberSum().twoNumberSum(array, targetSum);
            if (result.length == 0) {
                LOGGER.info("No pair of numbers sum up to the target sum");
                return;
            }

            LOGGER.info("The pair of numbers that sum up to the target sum are: {} and {}", result[0], result[1]);
        } catch (IOException e) {
            LOGGER.error("Error occurred while reading the input: {}", e.getMessage());
        }
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
    public void executeAlgorithm(String algorithmName) {

        switch (algorithmName) {
            case TWO_NUMBER_SUM:
                executeTwoNumberSum();
                break;
            default:
                LOGGER.info("Algorithm not available");
        }
    }
}
