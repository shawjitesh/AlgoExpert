# AlgoExpert

Mission: Ace the Technical Interviews

## Project Overview

This project is designed to help you prepare for technical interviews by providing implementations of various algorithms and data structures. The project is built using Java, Maven, and Spring Boot.

## Categories

- **Arrays**
- **Binary Search Trees**
- **Binary Trees**
- **Dynamic Programming**
- **Famous Algorithms**
- **Graphs**
- **Greedy Algorithms**
- **Heaps**
- **Linked Lists**
- **Recursion**
- **Searching**
- **Sorting**
- **Stacks**
- **Strings**
- **Tries**

## Project Structure

- `src/main/java/org/algoexpert/services/`: Contains service classes for executing algorithms.
- `src/main/java/org/algoexpert/algorithms/`: Contains various algorithm implementations for easy, medium, hard and very hard problems related to various data structure categories
- `src/main/resources/`: Contains configuration files and other resources.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Spring Boot 2.5.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/shawjitesh/algoexpert.git
    cd algoexpert
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

### Swagger Documentation

The project includes Swagger documentation to help you explore and test the different algorithms via REST endpoints.

1. Start the application as described in the installation steps.
2. Open your web browser and navigate to `http://localhost:8080/swagger-ui.html` to access the Swagger UI.
3. Use the Swagger UI to try out the different algorithms under various data structure categories by interacting with the exposed REST endpoints.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.