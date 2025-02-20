package org.algoexpert.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Configuration class for defining application beans.
 * <p>
 * This class is annotated with {@link Configuration}, indicating that it contains bean definitions for the Spring
 * container. It provides a method to create a {@link BufferedReader} bean that reads from the standard input stream.
 * </p>
 *
 * @author Jitesh Shaw
 */

@Configuration
public class AppConfig {

    /**
     * Creates a {@link BufferedReader} bean that reads from the standard input stream.
     * <p>
     * This method is annotated with {@link Bean}, indicating that it returns a Spring bean to be managed by the Spring
     * container. The {@link BufferedReader} is initialized with an {@link InputStreamReader} that reads from
     * {@link System#in}, allowing for console input to be read.
     * </p>
     *
     * @return a {@link BufferedReader} instance for reading from the standard input stream
     */
    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
