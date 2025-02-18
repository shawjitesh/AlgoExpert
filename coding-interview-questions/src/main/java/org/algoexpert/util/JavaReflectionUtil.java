package org.algoexpert.util;

import org.algoexpert.algorithms.arrays.ArraysAlgorithms;
import org.algoexpert.algorithms.binarysearchtrees.BinarySearchTreesAlgorithms;
import org.algoexpert.algorithms.binarytrees.BinaryTreesAlgorithms;
import org.algoexpert.algorithms.dynamicprogramming.DynamicProgrammingAlgorithms;
import org.algoexpert.algorithms.famousalgorithms.FamousAlgorithms;
import org.algoexpert.algorithms.graphs.GraphsAlgorithms;
import org.algoexpert.algorithms.greedyalgorithms.GreedyAlgorithms;
import org.algoexpert.algorithms.heaps.HeapsAlgorithms;
import org.algoexpert.algorithms.linkedlists.LinkedListsAlgorithms;
import org.algoexpert.algorithms.recursion.RecursionAlgorithms;
import org.algoexpert.algorithms.searching.SearchingAlgorithms;
import org.algoexpert.algorithms.sorting.SortingAlgorithms;
import org.algoexpert.algorithms.stacks.StacksAlgorithms;
import org.algoexpert.algorithms.strings.StringsAlgorithms;
import org.algoexpert.algorithms.tries.TriesAlgorithms;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Utility class for populating a map with data structure categories and their corresponding methods using Java Reflection.
 * <p>
 * This class provides a method to populate a map where the key is the display name of a data structure category
 * and the value is an array of {@link Method} objects representing the methods of the corresponding algorithm class.
 * </p>
 * <p>
 * The method iterates through all values of the {@link DataStructureCategories} enum and, based on the category,
 * retrieves the declared methods of the corresponding algorithm class. It then puts the category's display name
 * and the array of methods into the provided map.
 * </p>
 * <p>
 * This class is annotated with {@link Component} to indicate that it is a Spring-managed component.
 * </p>
 *
 * @author Jitesh Shaw
 */

@Component
public class JavaReflectionUtil {

    /**
     * Populates the provided map with data structure categories and their corresponding methods.
     * <p>
     * This method iterates through all values of the {@link DataStructureCategories} enum and, based on the category,
     * retrieves the declared methods of the corresponding algorithm class. It then puts the category's display name
     * and the array of methods into the provided map.
     * </p>
     *
     * @param dataStructureCategoriesAndMethodsMap a map where the key is the display name of a data structure category
     *                                             and the value is an array of {@link Method} objects representing
     *                                             the methods of the corresponding algorithm class.
     */
    public void populateDataStructureCategoriesAndMethodsMap(HashMap<String, Method[]> dataStructureCategoriesAndMethodsMap) {

        Arrays.stream(DataStructureCategories.values()).forEach(dataStructureCategory -> {
            switch(dataStructureCategory) {
                case ARRAYS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            ArraysAlgorithms.class.getDeclaredMethods());
                    break;
                case BINARY_SEARCH_TREES:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            BinarySearchTreesAlgorithms.class.getDeclaredMethods());
                    break;
                case BINARY_TREES:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            BinaryTreesAlgorithms.class.getDeclaredMethods());
                    break;
                case DYNAMIC_PROGRAMMING:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            DynamicProgrammingAlgorithms.class.getDeclaredMethods());
                    break;
                case FAMOUS_ALGORITHMS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            FamousAlgorithms.class.getDeclaredMethods());
                    break;
                case GRAPHS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            GraphsAlgorithms.class.getDeclaredMethods());
                    break;
                case GREEDY_ALGORITHMS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            GreedyAlgorithms.class.getDeclaredMethods());
                    break;
                case HEAPS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            HeapsAlgorithms.class.getDeclaredMethods());
                    break;
                case LINKED_LISTS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            LinkedListsAlgorithms.class.getDeclaredMethods());
                    break;
                case RECURSION:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            RecursionAlgorithms.class.getDeclaredMethods());
                    break;
                case SEARCHING:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            SearchingAlgorithms.class.getDeclaredMethods());
                    break;
                case SORTING:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            SortingAlgorithms.class.getDeclaredMethods());
                    break;
                case STACKS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            StacksAlgorithms.class.getDeclaredMethods());
                    break;
                case STRINGS:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            StringsAlgorithms.class.getDeclaredMethods());
                    break;
                case TRIES:
                    dataStructureCategoriesAndMethodsMap.put(dataStructureCategory.getDisplayName(),
                            TriesAlgorithms.class.getDeclaredMethods());
                    break;
                default:
                    break;
            }
        });
    }
}
