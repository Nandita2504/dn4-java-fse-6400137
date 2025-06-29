import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorFixtureTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up...");
        calculator = new Calculator();  // Setup: create calculator instance
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Cleaning up...");
        calculator = null; // Teardown: clear reference
    }

    @Test
    public void testAddition() {
        // Arrange
        int a = 10, b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(15, result);
    }

    @Test
    public void testSubtraction() {
        // Arrange
        int a = 10, b = 4;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(6, result);
    }
}
