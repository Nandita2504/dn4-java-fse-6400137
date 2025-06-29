package main.com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
    private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

    public static void main(String[] args) {
        logger.info("Application started");

        try {
            int result = divide(10, 0); // Will throw ArithmeticException
            logger.info("Division result: {}", result);
        } catch (ArithmeticException e) {
            logger.error("An error occurred: Division by zero", e);
        }

        logger.warn("This is a warning about deprecated method usage.");
        logger.info("Application finished successfully");
    }

    private static int divide(int a, int b) {
        return a / b;
    }
}
