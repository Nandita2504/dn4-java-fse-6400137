package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(4, calc.subtract(7, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(12, calc.multiply(3, 4));
    }

    @Test
    public void testDivide() {
        assertEquals(5, calc.divide(10, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calc.divide(5, 0);
    }
}
