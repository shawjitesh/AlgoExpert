package org.algoexpert.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    @GetMapping("/{algorithmName}")
    public String executeAlgorithm(@PathVariable String algorithmName) {
        // Here you would call the appropriate algorithm implementation based on the algorithmName
        // For demonstration, we return a placeholder message
        return "Executing algorithm: " + algorithmName;
    }
}
