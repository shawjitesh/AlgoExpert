package org.algoexpert.controller;

import jakarta.annotation.PostConstruct;
import org.algoexpert.services.ArraysService;
import org.algoexpert.util.JavaReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Controller class for handling algorithm execution requests.
 * <p>
 * This class provides endpoints to execute algorithms based on the specified data structure category and algorithm
 * name. It uses the {@link RestController} annotation to indicate that it is a RESTful web service controller and the
 * {@link RequestMapping} annotation to map requests to the "/api" path.
 * </p>
 *
 * @author Jitesh Shaw
 */

@RestController
@RequestMapping("/api")
public class AlgorithmController {

    private final JavaReflectionUtil javaReflectionUtil;
    private final ArraysService arraysService;
    private final HashMap<String, Method[]> dataStructureCategoriesAndMethodsMap = new HashMap<>();

    /**
     * Constructs an instance of the AlgorithmController.
     * <p>
     * This constructor is annotated with {@link Autowired}, indicating that it should be used by Spring's dependency
     * injection to create an instance of the controller. It initializes the {@code javaReflectionUtil} and
     * {@code arraysService} fields with the provided instances.
     * </p>
     *
     * @param javaReflectionUtil an instance of {@link JavaReflectionUtil} used for reflection operations
     * @param arraysService an instance of {@link ArraysService} used to execute array-related algorithms
     */
    @Autowired
    public AlgorithmController(JavaReflectionUtil javaReflectionUtil, ArraysService arraysService) {
        this.javaReflectionUtil = javaReflectionUtil;
        this.arraysService = arraysService;
    }

    /**
     * Initializes the data structure categories and methods map.
     * <p>
     * This method is annotated with {@link PostConstruct}, indicating that it should be executed after the dependency
     * injection is done to perform any initialization. It calls the
     * {@link JavaReflectionUtil#populateDataStructureCategoriesAndMethodsMap(HashMap)} method to populate the
     * {@code dataStructureCategoriesAndMethodsMap} with the relevant data.
     * </p>
     */
    @PostConstruct
    public void init() {
        javaReflectionUtil.populateDataStructureCategoriesAndMethodsMap(dataStructureCategoriesAndMethodsMap);
    }

    /**
     * Executes the specified algorithm under the given data structure category.
     * <p>
     * This method is mapped to a GET request with the specified data structure category and algorithm name as request
     * parameters. It checks if the provided data structure category and algorithm name exist in the
     * {@code dataStructureCategoriesAndMethodsMap}. If they exist, it calls the
     * {@link ArraysService#executeAlgorithm(String)} method to execute the algorithm. If they do not exist, it returns
     * a message indicating that the data structure category or algorithm is not available.
     * </p>
     *
     * @param datastructureCategory the category of the data structure
     * @param algorithmName the name of the algorithm to execute
     * @return a message indicating the result of the algorithm execution
     */
    @GetMapping("/executeAlgorithm")
    public ResponseEntity<String> executeAlgorithm(@RequestParam(value = "datastructureCategory",
                                                               defaultValue = "arrays")
                                       String datastructureCategory,
                                                   @RequestParam(value = "algorithmName",
                                                           defaultValue = "twoNumberSum")
                                       String algorithmName) {

        if(dataStructureCategoriesAndMethodsMap.containsKey(datastructureCategory) &&
                Arrays.stream(dataStructureCategoriesAndMethodsMap.get(datastructureCategory))
                        .anyMatch(method -> method.getName().equals(algorithmName))) {
            if(arraysService.executeAlgorithm(algorithmName)) {
                return ResponseEntity.ok("Successfully executed algorithm \"" + algorithmName + "\" under data " +
                        "structure category \"" + datastructureCategory + "\"");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while executing algorithm \"" + algorithmName + "\" under data structure category \"" +
                            datastructureCategory + "\"");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Data structure category \"" + datastructureCategory + "\" or algorithm \"" + algorithmName +
                            "\" not available");
        }
    }
}
