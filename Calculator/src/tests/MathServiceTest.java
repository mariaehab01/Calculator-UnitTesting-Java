package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.assertEquals;

import main.ICalculatorService;
import main.MathService;

@RunWith(JMock.class)
public class MathServiceTest {
    public Mockery context = new JUnit4Mockery();
    private MathService mathService;
    private ICalculatorService calculatorServiceMock;

    @Before
    public void setUp() {
        context = new JUnit4Mockery();
        calculatorServiceMock = context.mock(ICalculatorService.class);
        mathService = new MathService(calculatorServiceMock);
    }

    @Test
    public void testAddDivisionToSumWithPositiveNumbers() {
        final int a = 10;
        final int b = 2;

        context.checking(new Expectations() {{
            oneOf(calculatorServiceMock).divide(a, b); will(returnValue(5)); // 10/2 = 5
            oneOf(calculatorServiceMock).sum(a, b); will(returnValue(12));   // 10+2 = 12
        }});

        int result = mathService.addDivisionToSum(a, b);

        assertEquals(17, result); // 5 + 12 = 17
    }

    @Test
    public void testAddDivisionToSumWithNegativeNumbers() {
        final int a = -10;
        final int b = 2;

        context.checking(new Expectations() {{
            oneOf(calculatorServiceMock).divide(a, b); will(returnValue(-5)); // -10/2 = -5
            oneOf(calculatorServiceMock).sum(a, b); will(returnValue(-8));    // -10+2 = -8
        }});

        int result = mathService.addDivisionToSum(a, b);

        assertEquals(-13, result); // -5 + (-8) = -13
    }

    @Test(expected = ArithmeticException.class)
    public void testAddDivisionToSumWithDivisionByZero() {
        final int a = 10;
        final int b = 0;

        context.checking(new Expectations() {{
            oneOf(calculatorServiceMock).divide(a, b); will(throwException(new ArithmeticException("Division by zero")));
        }});

        mathService.addDivisionToSum(a, b);
    }

    @Test
    public void testAddDivisionToSumWithZeroSum() {
        final int a = 0;
        final int b = 5;

        context.checking(new Expectations() {{
            oneOf(calculatorServiceMock).divide(a, b); will(returnValue(0)); // 0/5 = 0
            oneOf(calculatorServiceMock).sum(a, b); will(returnValue(5));    // 0+5 = 5
        }});

        int result = mathService.addDivisionToSum(a, b);

        assertEquals(5, result); // 0 + 5 = 5
    }

    @Test
    public void testAddDivisionToSumWithLargeNumbers() {
        final int a = Integer.MAX_VALUE;
        final int b = 1;

        context.checking(new Expectations() {{
            oneOf(calculatorServiceMock).divide(a, b); will(returnValue(Integer.MAX_VALUE)); // MAX_VALUE/1 = MAX_VALUE
            oneOf(calculatorServiceMock).sum(a, b); will(returnValue(Integer.MAX_VALUE));    // This would overflow in reality, but we're mocking
        }});

        int result = mathService.addDivisionToSum(a, b);

        assertEquals(2 * Integer.MAX_VALUE, result);
    }
}
