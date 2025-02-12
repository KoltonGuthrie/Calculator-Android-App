package edu.jsu.mcis.cs408.calculator;

import java.math.BigDecimal;

public class CalculatorModel {

    private BigDecimal leftValue = null;
    private BigDecimal rightValue = null;
    private CalculatorOperator operator = null;
    private CalculatorState state = CalculatorState.LEFT;
    private StringBuffer output = new StringBuffer();

    public void setLeftValue(BigDecimal leftValue) {
        this.leftValue = leftValue;
    }

    public void setRightValue(BigDecimal rightValue) {
        this.rightValue = rightValue;
    }

    public void setOperator(CalculatorOperator operator) {
        this.operator = operator;
    }

    public void setState(CalculatorState state) {
        this.state = state;
    }

    public BigDecimal getLeftValue() {
        return leftValue;
    }

    public BigDecimal getRightValue() {
        return rightValue;
    }

    public CalculatorOperator getOperator() {
        return operator;
    }

    public CalculatorState getState() {
        return state;
    }

    public StringBuffer getOutput() {
        return output;
    }

}