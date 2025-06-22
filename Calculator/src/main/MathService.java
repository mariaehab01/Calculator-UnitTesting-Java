package main;
public class MathService {

    private final ICalculatorService calculatorService;

    public MathService(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int addDivisionToSum(int a, int b) throws ArithmeticException {
        int divisionResult = calculatorService.divide(a, b);
        int sumResult = calculatorService.sum(a, b);
        return divisionResult + sumResult;
    }
}