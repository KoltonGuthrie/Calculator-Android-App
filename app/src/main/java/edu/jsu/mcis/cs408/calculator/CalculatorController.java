package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

import java.math.BigDecimal;

public class CalculatorController {

    private CalculatorModel model;
    private MainActivity view;

    public CalculatorController(CalculatorModel model, MainActivity view) {
        this.model = model;
        this.view = view;
    }

    public void handleInput(String input) {
        switch (input) {
            case "btn0":
                submitValue("0");
                break;
            case "btn1":
                submitValue("1");
                break;
            case "btn2":
                submitValue("2");
                break;
            case "btn3":
                submitValue("3");
                break;
            case "btn4":
                submitValue("4");
                break;
            case "btn5":
                submitValue("5");
                break;
            case "btn6":
                submitValue("6");
                break;
            case "btn7":
                submitValue("7");
                break;
            case "btn8":
                submitValue("8");
                break;
            case "btn9":
                submitValue("9");
                break;
            case "btnPlusSub":
                submitOperator(CalculatorOperator.SWAP_SIGN);
                break;
            case "btnDec":
                submitValue(".");
                break;
            case "btnPlus":
                submitOperator(CalculatorOperator.ADD);
                break;
            case "btnEqual":
                submitOperator(CalculatorOperator.EQUAL);
                break;
            case "btnMult":
                submitOperator(CalculatorOperator.MULTIPLY);
                break;
            case "btnSub":
                submitOperator(CalculatorOperator.SUBTRACT);
                break;
            case "btnDiv":
                submitOperator(CalculatorOperator.DIVIDE);
                break;
            case "btnPrc":
                submitOperator(CalculatorOperator.PERCENTAGE);
                break;
            case "btnSqrt":
                submitOperator(CalculatorOperator.SQUARE_ROOT);
                break;
            case "btnClr":
                submitOperator(CalculatorOperator.CLEAR);
                break;
        }
    }

    private void submitValue(String s) {
        if (model.getState().equals(CalculatorState.OPERATOR)) {
            swapState(CalculatorState.RIGHT);
            clearOutput();
        }

        if (model.getOutput().length() <= 0 && s.equals("0")) {
            return;
        }

        if (model.getState().equals(CalculatorState.LEFT) && model.getLeftValue() != null) {
            clear();
        }

        if(model.getState().equals(CalculatorState.RIGHT) && model.getRightValue() != null) {
            setRightValue(null);
        }

        model.getOutput().append(s);

        updateEquation(false);
        view.updateOutput(model.getOutput().toString());
    }

    private void submitOperator(CalculatorOperator operator) {
        switch (operator) {
            case CLEAR:
                handleClear();
                break;
            case SWAP_SIGN:
                handleSwapSign();
                break;
            case SQUARE_ROOT:
                handleSqrt();
                return;
            case PERCENTAGE:
                handlePercentage();
                return;
            case EQUAL:
                handleEquals();
                break;
            default:
                handleArithmeticOperator(operator);
                break;
        }
    }

    private void handleClear() {
        swapState(CalculatorState.LEFT);
        clear();
        view.updateOutput("0");
        view.updateEquation("");
    }

    private void handlePercentage() {


        if (model.getOutput().length() > 0 && model.getOutput().charAt(model.getOutput().length() - 1) == '.') {
            model.getOutput().append(0);
        }

        if(model.getState().equals(CalculatorState.LEFT) && model.getLeftValue() == null && model.getRightValue() == null) {
            setLeftValue(new BigDecimal(0));
            model.getOutput().setLength(0);
            model.getOutput().append(0);
            updateEquation(false);
            view.updateOutput(model.getOutput().toString());
            return;
        }

        if(model.getState().equals(CalculatorState.OPERATOR) && model.getOutput().length() <= 0) {
            setRightValue(model.getLeftValue());
        }

        if(model.getState().equals(CalculatorState.LEFT) && model.getLeftValue() != null && model.getRightValue() != null) {
            setLeftValue(parseOutputToBigDecimal());
            setRightValue(parseOutputToBigDecimal());
        }

        if(model.getState().equals(CalculatorState.RIGHT) && model.getRightValue() == null) {
            setRightValue(parseOutputToBigDecimal());
        }

        BigDecimal result = model.getLeftValue().multiply(CalculatorArithmetic.doOperation(model.getRightValue(), new BigDecimal(100), CalculatorOperator.DIVIDE));

        setRightValue(result);
        model.getOutput().setLength(0);
        model.getOutput().append(result.toString());
        updateEquation(false);
        view.updateOutput(model.getOutput().toString());
        swapState(CalculatorState.RIGHT);
    }

    private void handleSqrt() {
        if (model.getOutput().length() > 0 && model.getOutput().charAt(model.getOutput().length() - 1) == '.') {
            model.getOutput().append(0);
        }

        BigDecimal value;

        if(model.getState().equals(CalculatorState.OPERATOR) && model.getOutput().length() <= 0) {
            setRightValue(model.getLeftValue());
            value = model.getRightValue();
        } else {
            value = parseOutputToBigDecimal();
        }

        try {
            BigDecimal result = CalculatorArithmetic.doOperation(value, null, CalculatorOperator.SQUARE_ROOT);

            if (model.getState().equals(CalculatorState.LEFT)) {
                setLeftValue(result);
            } else if (model.getState().equals(CalculatorState.RIGHT)) {
                setRightValue(result);
            }

            swapState(CalculatorState.RIGHT);
            model.getOutput().setLength(0);
            model.getOutput().append(result.toString());
            updateEquation(false);
            view.updateOutput(model.getOutput().toString());
        } catch(ArithmeticException err) {
            view.updateOutput(err.getMessage());
        }

    }

    private void handleSwapSign() {
        if (model.getOutput().length() <= 0 || model.getOutput().charAt(0) == '0') {
            return;
        }
        if (model.getOutput().charAt(0) == '-') {
            model.getOutput().deleteCharAt(0);
        } else {
            model.getOutput().insert(0, '-');
        }
        view.updateOutput(model.getOutput().toString());
    }

    private void handleEquals() {
        if (model.getOutput().length() > 0 && model.getOutput().charAt(model.getOutput().length() - 1) == '.') {
            model.getOutput().append(0);
        }

        if (model.getRightValue() != null && model.getLeftValue() != null && model.getState().equals(CalculatorState.LEFT)) {
            if (model.getOutput().length() > 0) {
                setLeftValue(parseOutputToBigDecimal());
            } else {
                setLeftValue(new BigDecimal(0));
            }
        }

        if (model.getState().equals(CalculatorState.RIGHT) && model.getRightValue() == null ) {
            if (model.getOutput().length() > 0) {
                setRightValue(parseOutputToBigDecimal());
            } else {
                setRightValue(new BigDecimal(0));
            }
        }
        if (model.getState().equals(CalculatorState.OPERATOR)) {
            setRightValue(model.getLeftValue());
        }

        swapState(CalculatorState.LEFT);

        if (model.getOperator() == null) {
            if(model.getOutput().length() <= 0) {
                model.getOutput().append(0);
            }
            setLeftValue(parseOutputToBigDecimal());
            updateEquation(true);
            view.updateOutput(model.getOutput().toString());
            return;
        }

        try {
            BigDecimal result = CalculatorArithmetic.doOperation(model.getLeftValue(), model.getRightValue(), model.getOperator());
            model.getOutput().setLength(0);
            model.getOutput().append(result.toString());
            updateEquation(true);
            view.updateOutput(model.getOutput().toString());

        } catch(ArithmeticException err) {
            view.updateOutput(err.getMessage());
        }

        view.updateStateOutput(model.getState());
    }

    private void handleArithmeticOperator(CalculatorOperator operator) {
        if (model.getOutput().length() <= 0) {
            model.getOutput().append(0);
        }
        if (model.getOutput().length() > 0 && model.getOutput().charAt(model.getOutput().length() - 1) == '.') {
            model.getOutput().append(0);
        }

        if (model.getState().equals(CalculatorState.LEFT)) {
            setLeftValue(parseOutputToBigDecimal());
            swapState(CalculatorState.OPERATOR);
            setRightValue(null);
            view.updateStateOutput(model.getState());
        } else if (model.getState().equals(CalculatorState.RIGHT)) {
            if(model.getRightValue() == null) {
                setRightValue(parseOutputToBigDecimal());
            }
            BigDecimal value = CalculatorArithmetic.doOperation(model.getLeftValue(), model.getRightValue(), model.getOperator());
            setLeftValue(value);
            swapState(CalculatorState.OPERATOR);
            setRightValue(null);
            view.updateOutput(value.toString());
            view.updateStateOutput(model.getState());
        }

        setOperator(operator);
        model.getOutput().setLength(0);
        updateEquation(false);
    }

    private BigDecimal parseOutputToBigDecimal() {
        if(model.getOutput().length() <= 0) return new BigDecimal(0);
        return new BigDecimal(model.getOutput().toString());
    }

    private void updateEquation(boolean b) {
        String lv = model.getLeftValue() != null ? model.getLeftValue().toString() + " " : "";
        String rv = model.getRightValue() != null ? model.getRightValue().toString() + " " : "";
        String op = model.getOperator() != null ? model.getOperator().toString() + " " : "";
        String eq = b ? CalculatorOperator.EQUAL + " " : "";
        view.updateEquation(String.format("%s%s%s%s", lv, op, rv, eq));
    }

    private void clear() {
        setLeftValue(null);
        setRightValue(null);
        setOperator(null);

        model.getOutput().setLength(0);
        view.updateOutput("0");
        view.updateEquation("");
    }

    private void clearOutput() {
        model.getOutput().setLength(0);
        view.updateOutput("0");
    }

    private void swapState(CalculatorState s) {
        model.setState(s);
        view.updateStateOutput(model.getState());
    }

    private void setRightValue(BigDecimal bd) {
        view.updateRightOutput(bd);
        model.setRightValue(bd);
    }

    private void setLeftValue(BigDecimal bd) {
        view.updateLeftOutput(bd);
        model.setLeftValue(bd);
    }

    private void setOperator(CalculatorOperator op) {
        view.updateOperatorOutput(op);
        model.setOperator(op);
    }
}