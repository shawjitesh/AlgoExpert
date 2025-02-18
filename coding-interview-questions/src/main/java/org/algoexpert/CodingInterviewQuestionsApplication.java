package org.algoexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Coding Interview Questions Spring Boot application.
 * <p>
 * This is the entry point of the Spring Boot application. The {@link SpringBootApplication} annotation indicates
 * that this is a Spring Boot application. The {@code main} method uses {@link SpringApplication#run(Class, String...)}
 * to launch the application.
 * </p>
 *
 * @author Jitesh Shaw
 */

@SpringBootApplication
public class CodingInterviewQuestionsApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * <p>
     * This method uses {@link SpringApplication#run(Class, String...)} to launch the Coding Interview Questions application.
     * </p>
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(CodingInterviewQuestionsApplication.class, args);
    }
}