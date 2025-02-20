package org.algoexpert.services;

import lombok.extern.slf4j.Slf4j;

import org.algoexpert.algorithms.arrays.easy.TwoNumberSum;
import org.algoexpert.algorithms.arrays.hard.FourNumberSum;
import org.algoexpert.algorithms.arrays.medium.ThreeNumberSum;
import org.algoexpert.algorithms.arrays.veryhard.ApartmentHunting;
import org.algoexpert.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.algoexpert.utils.AlgorithmNames.*;

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

    private final LoggerUtil loggerUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(ArraysService.class);
    private final BufferedReader bufferedReader;

    /**
     * Constructs an instance of the ArraysService.
     * <p>
     * This constructor is annotated with {@link Autowired}, indicating that it should be used by Spring's dependency
     * injection to create an instance of the service. It initializes the {@code loggerUtil} field with the provided
     * instance.
     * </p>
     *
     * @param loggerUtil an instance of {@link LoggerUtil} used for logging prompts and warnings
     */
    @Autowired
    public ArraysService(LoggerUtil loggerUtil, BufferedReader bufferedReader) {
        this.loggerUtil = loggerUtil;
        this.bufferedReader = bufferedReader;
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
            case TWO_NUMBER_SUM:
                try {
                    executeTwoNumberSum();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, THREE_NUMBER_SUM);
                    return false;
                }
                break;
            case THREE_NUMBER_SUM:
                try {
                    executeThreeNumberSum();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, THREE_NUMBER_SUM);
                    return false;
                }
                break;
            case FOUR_NUMBER_SUM:
                try {
                    executeFourNumberSum();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, FOUR_NUMBER_SUM);
                    return false;
                }
                break;
            case APARTMENT_HUNTING:
                try {
                    executeApartmentHunting();
                } catch (RuntimeException e) {
                    loggerUtil.warnErrorWhileExecutingAlgorithm(LOGGER, APARTMENT_HUNTING);
                    return false;
                }
                break;
            default:
                LOGGER.info("Algorithm not available");
        }

        return true;
    }

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

        try {
            loggerUtil.promptToEnterArraySize(LOGGER);
            int arraySize = Integer.parseInt(bufferedReader.readLine());
            int[] array = new int[arraySize];

            loggerUtil.promptToEnterArrayElements(LOGGER);
            for (int i = 0; i < arraySize; i++) {
                array[i] = Integer.parseInt(bufferedReader.readLine());
            }

            loggerUtil.promptToEnterTargetSum(LOGGER);
            int targetSum = Integer.parseInt(bufferedReader.readLine());

            int[] result = new TwoNumberSum().twoNumberSum(array, targetSum);
            if (result.length == 0) {
                LOGGER.info("No pair of numbers sum up to the target sum");
                return;
            }

            LOGGER.info("The pair of numbers that sum up to the target sum are: {} and {}", result[0], result[1]);
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
        }
    }

    /**
     * Executes the "Three Number Sum" algorithm.
     * <p>
     * This method reads the array size, array elements, and target sum from the standard input. It then uses the
     * {@link ThreeNumberSum#threeNumberSum(int[], int)} method to find all unique triplets in the array that sum up to
     * the target sum. If such triplets are found, it logs each triplet; otherwise, it logs that no triplet was found.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeThreeNumberSum() throws RuntimeException {

        try {
            loggerUtil.promptToEnterArraySize(LOGGER);
            int arraySize = Integer.parseInt(bufferedReader.readLine());
            int[] array = new int[arraySize];

            loggerUtil.promptToEnterArrayElements(LOGGER);
            for (int i = 0; i < arraySize; i++) {
                array[i] = Integer.parseInt(bufferedReader.readLine());
            }

            loggerUtil.promptToEnterTargetSum(LOGGER);
            int targetSum = Integer.parseInt(bufferedReader.readLine());

            List<Integer[]> result = new ThreeNumberSum().threeNumberSum(array, targetSum);
            if (result.isEmpty()) {
                LOGGER.info("No triplet of numbers sum up to the target sum");
                return;
            }

            for (Integer[] triplet : result) {
                LOGGER.info("The triplet of numbers that sum up to the target sum are: {}, {}, and {}", triplet[0],
                        triplet[1], triplet[2]);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
            throw new RuntimeException("Error while reading input", e.getCause());
        }
    }

    /**
     * Executes the "Four Number Sum" algorithm.
     * <p>
     * This method reads the array size, array elements, and target sum from the standard input. It then uses the
     * {@link FourNumberSum#fourNumberSum(int[], int)} method to find all unique quadruplets in the array that sum up to
     * the target sum. If such quadruplets are found, it logs each quadruplet; otherwise, it logs that no quadruplet
     * was found.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeFourNumberSum() {

        try {
            loggerUtil.promptToEnterArraySize(LOGGER);
            int arraySize = Integer.parseInt(bufferedReader.readLine());
            int[] array = new int[arraySize];

            loggerUtil.promptToEnterArrayElements(LOGGER);
            for (int i = 0; i < arraySize; i++) {
                array[i] = Integer.parseInt(bufferedReader.readLine());
            }

            loggerUtil.promptToEnterTargetSum(LOGGER);
            int targetSum = Integer.parseInt(bufferedReader.readLine());

            List<Integer[]> result = new FourNumberSum().fourNumberSum(array, targetSum);
            if (result.isEmpty()) {
                LOGGER.info("No quadruplet of numbers sum up to the target sum");
                return;
            }

            for (Integer[] quadruplet : result) {
                LOGGER.info("The quadruplet of numbers that sum up to the target sum are: {}, {}, {}, and {}",
                        quadruplet[0], quadruplet[1], quadruplet[2], quadruplet[3]);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
            throw new RuntimeException("Error while reading input", e.getCause());
        }
    }

    /**
     * Executes the "Apartment Hunting" algorithm.
     * <p>
     * This method reads the number of blocks, the number of requirements, and the requirements themselves from the
     * standard input. It then reads the availability of each requirement for each block and stores this information in
     * a list of maps. The method uses the {@link ApartmentHunting#apartmentHunting(List, String[])} method to find the
     * best block to live in based on the minimum distance to all required facilities.
     * If a block satisfies all requirements, it logs the index of that block; otherwise, it logs that no block
     * satisfies all requirements.
     * </p>
     * <p>
     * The method handles any {@link IOException} that may occur during input reading and logs an error message.
     * </p>
     */
    private void executeApartmentHunting() {

        try {
            LOGGER.info("Enter the number of blocks: ");
            int numBlocks = Integer.parseInt(bufferedReader.readLine());

            LOGGER.info("Enter the number of requirements: ");
            int numReqs = Integer.parseInt(bufferedReader.readLine());

            String[] reqs = new String[numReqs];
            LOGGER.info("Enter the requirements: ");
            for (int i = 0; i < numReqs; i++) {
                reqs[i] = bufferedReader.readLine();
            }

            List<Map<String, Boolean>> blockDetails = new ArrayList<>();
            for (int i = 0; i < numBlocks; i++) {
                Map<String, Boolean> block = new HashMap<>();
                LOGGER.info("Enter the availability of requirements for block {}: ", i + 1);
                for (int j = 0; j < numReqs; j++) {
                    LOGGER.info("Enter the availability of requirement {} in block {}: ", reqs[j], i + 1);
                    block.put(reqs[j], Boolean.parseBoolean(bufferedReader.readLine()));
                }
                blockDetails.add(block);
            }

            int result = new ApartmentHunting().apartmentHunting(blockDetails, reqs);
            if (result == -1) {
                LOGGER.info("No block satisfies all requirements");
            } else {
                LOGGER.info("The block that satisfies all requirements is: {}", result);
            }
        } catch (IOException e) {
            loggerUtil.warnErrorWhileReadingInput(LOGGER, e);
            throw new RuntimeException("Error while reading input", e.getCause());
        }
    }
}
