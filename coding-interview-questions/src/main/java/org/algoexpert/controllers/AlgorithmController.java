package org.algoexpert.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;

import org.algoexpert.services.ArraysService;
import org.algoexpert.services.BinarySearchTreesService;
import org.algoexpert.utils.DataStructureCategories;
import org.algoexpert.utils.JavaReflectionUtil;
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
import java.util.Map;
import java.util.Objects;

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
@Tag(name = "Algorithm Controller", description = "Endpoints for executing different algorithms based on the " +
        "specified data structure category and algorithm name")
public class AlgorithmController {

    private final JavaReflectionUtil javaReflectionUtil;
    private final ArraysService arraysService;
    private final BinarySearchTreesService binarySearchTreesService;
    private final Map<String, Method[]> dataStructureCategoriesAndMethods = new HashMap<>();

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
    public AlgorithmController(JavaReflectionUtil javaReflectionUtil, ArraysService arraysService,
                               BinarySearchTreesService binarySearchTreesService) {
        this.javaReflectionUtil = javaReflectionUtil;
        this.arraysService = arraysService;
        this.binarySearchTreesService = binarySearchTreesService;
    }

    /**
     * Initializes the data structure categories and methods map.
     * <p>
     * This method is annotated with {@link PostConstruct}, indicating that it should be executed after the dependency
     * injection is done to perform any initialization. It calls the
     * {@link JavaReflectionUtil#populateDataStructureCategoriesAndMethodsMap(Map)} method to populate the
     * {@code dataStructureCategoriesAndMethodsMap} with the relevant data.
     * </p>
     */
    @PostConstruct
    public void init() {
        javaReflectionUtil.populateDataStructureCategoriesAndMethodsMap(dataStructureCategoriesAndMethods);
    }

    /**
     * Executes the specified algorithm for a given data structure category.
     * <p>
     * This method is mapped to a GET request with the specified data structure category and algorithm name as request
     * parameters. It checks if the provided data structure category and algorithm name exist in the
     * {@code dataStructureCategoriesAndMethods} map. If they exist, it calls the appropriate service to execute the
     * algorithm. If they do not exist, it returns a message indicating that the data structure category or algorithm is
     * not available.
     * </p>
     *
     * @param datastructureCategory the category of the data structure
     * @param algorithmName the name of the algorithm to execute
     * @return a ResponseEntity with a message indicating the result of the algorithm execution
     */
    @GetMapping("/executeAnyAlgorithm")
    @Operation(summary = "Executes the specified algorithm for a given data structure category")
    public ResponseEntity<String> executeAnyAlgorithm(@RequestParam(value = "datastructureCategory",
                                                               defaultValue = "arrays")
                                       String datastructureCategory,
                                                      @RequestParam(value = "algorithmName",
                                                               defaultValue = "twoNumberSum")
                                       String algorithmName) {

        if (dataStructureCategoriesAndMethods.containsKey(datastructureCategory)) {

            // Get the data structure category enum constant from the display name
            DataStructureCategories dSCategory = DataStructureCategories.fromDisplayName(datastructureCategory);

            switch (Objects.requireNonNull(dSCategory)) {
                case ARRAYS -> {
                    if(Arrays.stream(dataStructureCategoriesAndMethods.get(datastructureCategory))
                            .anyMatch(method -> method.getName().equals(algorithmName))) {
                        if (arraysService.executeAlgorithm(algorithmName)) {
                            return getSuccessfulAlgorithmExecutionResponse(algorithmName, datastructureCategory);
                        }
                        return getErrorWhileExecutingAlgorithmResponse(algorithmName, datastructureCategory);
                    }
                    return getAlgorithmNotAvailableResponse(algorithmName, datastructureCategory);
                }
                case BINARY_SEARCH_TREES -> {
                    if(Arrays.stream(dataStructureCategoriesAndMethods.get(datastructureCategory))
                            .anyMatch(method -> method.getName().equals(algorithmName))) {
                        if (binarySearchTreesService.executeAlgorithm(algorithmName)) {
                            return getSuccessfulAlgorithmExecutionResponse(algorithmName, datastructureCategory);
                        }
                        return getErrorWhileExecutingAlgorithmResponse(algorithmName, datastructureCategory);
                    }
                    return getAlgorithmNotAvailableResponse(algorithmName, datastructureCategory);
                }
                default -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Data structure category \"" + datastructureCategory + "\" not available");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid data structure category or algorithm name");
    }

    /**
     * Returns a ResponseEntity indicating the successful execution of the specified algorithm under the given data
     * structure category.
     * <p>
     * This method constructs a ResponseEntity with a status of HttpStatus.OK and a message body indicating that the
     * specified algorithm was successfully executed under the provided data structure category.
     * </p>
     *
     * @param algorithmName the name of the algorithm that was successfully executed
     * @param datastructureCategory the category of the data structure under which the algorithm was executed
     * @return a ResponseEntity with a status of HttpStatus.OK and a message indicating the successful execution of the
     * algorithm
     */
    private ResponseEntity<String> getSuccessfulAlgorithmExecutionResponse(String algorithmName,
                                                                           String datastructureCategory) {
        return ResponseEntity.ok("Successfully executed algorithm \"" + algorithmName +
                "\" under data structure category \"" + datastructureCategory + "\"");
    }

    /**
     * Returns a ResponseEntity indicating that an error occurred while executing the specified algorithm under the
     * given data structure category.
     * <p>
     * This method constructs a ResponseEntity with a status of HttpStatus.INTERNAL_SERVER_ERROR and a message body
     * indicating that an error occurred while executing the specified algorithm under the provided data structure
     * category.
     * </p>
     *
     * @param algorithmName the name of the algorithm that encountered an error during execution
     * @param datastructureCategory the category of the data structure under which the algorithm encountered an error
     * @return a ResponseEntity with a status of HttpStatus.INTERNAL_SERVER_ERROR and a message indicating the error
     *         encountered during the algorithm execution
     */
    private ResponseEntity<String> getErrorWhileExecutingAlgorithmResponse(String algorithmName,
                                                                           String datastructureCategory) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error while executing algorithm \"" + algorithmName +
                        "\" under data structure category \"" + datastructureCategory + "\"");
    }

    /**
     * Returns a ResponseEntity indicating that the specified algorithm is not available under the given data structure
     * category.
     * <p>
     * This method constructs a ResponseEntity with a status of HttpStatus.NOT_FOUND and a message body indicating that
     * the specified algorithm is not available under the provided data structure category.
     * </p>
     *
     * @param algorithmName the name of the algorithm that is not available
     * @param datastructureCategory the category of the data structure under which the algorithm is not available
     * @return a ResponseEntity with a status of HttpStatus.NOT_FOUND and a message indicating the unavailability of the
     * algorithm
     */
    private ResponseEntity<String> getAlgorithmNotAvailableResponse(String algorithmName,
                                                                    String datastructureCategory) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Algorithm \"" + algorithmName + "\" not available under data structure category \"" +
                        datastructureCategory + "\"");
    }
}
