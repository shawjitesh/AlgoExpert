package org.algoexpert.util;

import lombok.Getter;

/**
 * Enum representing various categories of data structures.
 * <p>
 * Each enum constant has a display name associated with it, which can be retrieved using the {@link #getDisplayName()}
 * method.
 * </p>
 * <p>
 * The enum constants include:
 * <ul>
 *     <li>ARRAYS</li>
 *     <li>BINARY_SEARCH_TREES</li>
 *     <li>BINARY_TREES</li>
 *     <li>DYNAMIC_PROGRAMMING</li>
 *     <li>FAMOUS_ALGORITHMS</li>
 *     <li>GRAPHS</li>
 *     <li>GREEDY_ALGORITHMS</li>
 *     <li>HEAPS</li>
 *     <li>LINKED_LISTS</li>
 *     <li>RECURSION</li>
 *     <li>SEARCHING</li>
 *     <li>SORTING</li>
 *     <li>STACKS</li>
 *     <li>STRINGS</li>
 *     <li>TRIES</li>
 * </ul>
 * </p>
 *
 * @author Jitesh Shaw
 */

@Getter
public enum DataStructureCategories {

    ARRAYS("arrays"),
    BINARY_SEARCH_TREES("binarySearchTrees"),
    BINARY_TREES("binaryTrees"),
    DYNAMIC_PROGRAMMING("dynamicProgramming"),
    FAMOUS_ALGORITHMS("famousAlgorithms"),
    GRAPHS("graphs"),
    GREEDY_ALGORITHMS("greedyAlgorithms"),
    HEAPS("heaps"),
    LINKED_LISTS("linkedLists"),
    RECURSION("recursion"),
    SEARCHING("searching"),
    SORTING("sorting"),
    STACKS("stacks"),
    STRINGS("strings"),
    TRIES("tries");

    private final String displayName;

    /**
     * Constructor for the {@link DataStructureCategories} enum.
     * <p>
     * Initializes the enum constant with the specified display name.
     * </p>
     *
     * @param displayName the display name associated with the data structure category
     */
    DataStructureCategories(String displayName) {
        this.displayName = displayName;
    }
}
