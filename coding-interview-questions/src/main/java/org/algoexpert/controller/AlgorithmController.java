package org.algoexpert.controller;

import jakarta.annotation.PostConstruct;
import org.algoexpert.services.ArraysService;
import org.algoexpert.util.JavaReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    private final JavaReflectionUtil javaReflectionUtil;
    private final ArraysService arraysService;
    private final HashMap<String, Method[]> dataStructureCategoriesAndMethodsMap = new HashMap<>();

    @Autowired
    public AlgorithmController(JavaReflectionUtil javaReflectionUtil, ArraysService arraysService) {
        this.javaReflectionUtil = javaReflectionUtil;
        this.arraysService = arraysService;
    }

    @PostConstruct
    public void init() {
        javaReflectionUtil.populateDataStructureCategoriesAndMethodsMap(dataStructureCategoriesAndMethodsMap);
    }

    @GetMapping("/{datastructureCategory}/{algorithmName}")
    public String executeAlgorithm(@PathVariable String datastructureCategory, @PathVariable String algorithmName) {

        if(dataStructureCategoriesAndMethodsMap.containsKey(datastructureCategory) &&
                Arrays.stream(dataStructureCategoriesAndMethodsMap.get(datastructureCategory))
                        .anyMatch(method -> method.getName().equals(algorithmName))) {
            arraysService.executeAlgorithm(algorithmName);
        } else {
            return "Data structure category \"" + datastructureCategory + "\" not available";
        }

        return "Successfully executed algorithm \"" + algorithmName + "\" under data structure category \"" +
                datastructureCategory + "\"";
    }
}
