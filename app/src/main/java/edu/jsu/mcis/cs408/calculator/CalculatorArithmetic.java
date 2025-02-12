package edu.jsu.mcis.cs408.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorArithmetic {
    public static BigDecimal doOperation(BigDecimal left, BigDecimal right, CalculatorOperator operator) {
        switch (operator) {
            case ADD:
                return left.add(right);
            case SUBTRACT:
                return left.subtract(right);
            case MULTIPLY:
                return left.multiply(right);
            case SQUARE_ROOT:
                return BigDecimalUtils.sqrt(left, 2);
            case DIVIDE:
                if (right.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return left.divide(right, 10, RoundingMode.HALF_UP).stripTrailingZeros();
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}