package edu.jsu.mcis.cs408.calculator;

public enum CalculatorOperator {
    ADD("+"),
    SUBTRACT("-"),
    DIVIDE("÷"),
    MULTIPLY("×"),
    SQUARE_ROOT("√"),
    PERCENTAGE(null),
    SWAP_SIGN(null),
    CLEAR(null),
    EQUAL("=");

    private String display;
    CalculatorOperator(String s) {
        display = s;
    }

    @Override
    public String toString() {
        return display;
    }
}
