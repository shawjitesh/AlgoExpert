package org.algoexpert.services;

import lombok.extern.slf4j.Slf4j;
import org.algoexpert.algorithms.arrays.easy.TwoNumberSum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class ArraysService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArraysService.class);

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
            LOGGER.error("Error occurred while reading the input", e);
        }
    }

    public void executeAlgorithm(String algorithmName) {

        switch (algorithmName) {
            case "twoNumberSum":
                executeTwoNumberSum();
                break;
            default:
                LOGGER.info("Algorithm not available");
        }
    }
}
