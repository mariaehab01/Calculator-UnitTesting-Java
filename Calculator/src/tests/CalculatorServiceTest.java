package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.CalculatorService;
import main.ICalculatorService;

public class CalculatorServiceTest {
    private ICalculatorService calculatorService;

    @Before
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testSumWithPositiveNumbers() {
        int result = calculatorService.sum(5, 3);
        assertEquals(8, result);
    }

    @Test
    public void testSumWithNegativeNumbers() {
        int result = calculatorService.sum(-5, -3);
        assertEquals(-8, result);
    }

    @Test
    public void testSumWithMaxIntegerValue() {
        int result = calculatorService.sum(Integer.MAX_VALUE, 0);
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void testDivideWithPositiveNumbers() {
        int result = calculatorService.divide(10, 2);
        assertEquals(5, result);
    }

    @Test
    public void testDivideWithNegativeNumbers() {
        int result = calculatorService.divide(-10, 2);
        assertEquals(-5, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calculatorService.divide(10, 0);
    }

}
