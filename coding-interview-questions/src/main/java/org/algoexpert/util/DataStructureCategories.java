package org.algoexpert.util;

import lombok.Getter;

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

    DataStructureCategories(String displayName) {
        this.displayName = displayName;
    }
}
