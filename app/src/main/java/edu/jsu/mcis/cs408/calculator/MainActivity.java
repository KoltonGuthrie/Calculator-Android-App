package edu.jsu.mcis.cs408.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Button> buttons = new ArrayList<>();
    private CalculatorState state = CalculatorState.LEFT;
    private StringBuffer output = new StringBuffer();
    private BigDecimal leftValue = null;
    private BigDecimal rightValue = null;
    private CalculatorOperator operator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addListenersToButtons();
    }

    private void addListenersToButtons() {
        CalculatorClickHandler click = new CalculatorClickHandler();

        for (int i = 0; i < binding.main.getChildCount(); ++i) {
            View child = binding.main.getChildAt(i);
            if(child instanceof Button) {
                child.setOnClickListener(click);
            }
        }
    }

    /*
        Will accept a string. This string should be a number or a decimal.
     */
    private void submitValue(String s) {
        if(state.equals(CalculatorState.OPERATOR)) {
            if(swapState(CalculatorState.RIGHT)) {
                clearOutput();
            }
        }

        if(output.length() <= 0 && s.equals("0")) {
            return;
        }

        if(state.equals(CalculatorState.LEFT) && leftValue != null) {
            clear();
        }

        output.append(s);
        updateOutput();
    }

    private void updateOutput() {
        binding.output.setText(output.toString());
    }

    private void updateEquation(boolean b) {
        String lv, rv, op, eq;
        lv = leftValue != null ? leftValue.toString() : "";
        rv = rightValue != null ? rightValue.toString() : "";
        op = operator != null ? operator.toString() : "";
        eq = b ? CalculatorOperator.EQUAL.toString() : "";
        binding.equation.setText(String.format("%s %s %s %s", lv, op, rv, eq));
    }

    private void submitOperator(CalculatorOperator oper) {
        Log.i("MainActivity", "CURRENT STATE: " + state.toString());
        if (oper.equals(CalculatorOperator.CLEAR)) {
            if (swapState(CalculatorState.LEFT)) {
                clear();
            }
        } else if (oper.equals(CalculatorOperator.SWAP_SIGN)) {
            if (output.length() <= 0 || output.charAt(0) == '0') {
                return;
            }

            if (output.charAt(0) == '-') {
                output.deleteCharAt(0);
            } else {
                output.insert(0, '-');
            }
            updateOutput();
        }
        else if (oper.equals(CalculatorOperator.EQUAL)) {
            if(output.length() > 0 && output.charAt(output.length()-1) == '.') {
                output.append(0);
            }
            if (rightValue != null && leftValue != null) {
                if(output.length() > 0) {
                    leftValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                } else {
                    leftValue = new BigDecimal(0);
                }
            }
            if(state.equals(CalculatorState.RIGHT)) {
                if(output.length() > 0) {
                    rightValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                } else {
                    rightValue = new BigDecimal(0);
                }
            }
            if(state.equals(CalculatorState.OPERATOR)) {
                rightValue = leftValue;
            }

            state = CalculatorState.LEFT;

            if(operator == null) {
                if(output.length() <= 0) {
                    output.append(0);
                }
                leftValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                output.setLength(0);
                updateEquation(true);
                updateOutput();
                return;
            }

            BigDecimal result = doOperation(leftValue, rightValue, operator);

            output.setLength(0);
            output.append(result.toString());
            updateEquation(true);
            updateOutput();
        } else {
            // TODO: Add Percentage and Sqrt

            if(output.length() <= 0) {
                output.append(0);
            }
            if(output.length() > 0 && output.charAt(output.length()-1) == '.') {
                output.append(0);
            }

            if(state.equals(CalculatorState.LEFT)) {
                leftValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                state = CalculatorState.OPERATOR;
                rightValue = null; // Could be NOT null from past operations
            } else if (state.equals(CalculatorState.RIGHT)) {
                rightValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                leftValue = doOperation(leftValue, rightValue, operator);
                rightValue = null;
                state = CalculatorState.RIGHT;
            }

            operator = oper;
            output.setLength(0);
            updateEquation(false);
        }

    }

    private BigDecimal doOperation(BigDecimal leftValue, BigDecimal rightValue, CalculatorOperator operator) {
        BigDecimal result;
        switch(operator) {
            case ADD:
                result = leftValue.add(rightValue);
                break;
            case SUBTRACT:
                result = leftValue.subtract(rightValue);
                break;
            case DIVIDE:
                result = leftValue.divide(rightValue);
                break;
            case MULTIPLY:
                result = leftValue.multiply(rightValue);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        return result;
}

    private void clear() {
        clearEquation();
        clearOutput();

        operator = null;
        leftValue = null;
        rightValue = null;
    }
    private void clearEquation() {
        binding.equation.setText("");
    }

    private void clearOutput() {
        output.setLength(0);
        binding.output.setText("0");
    }


    // Check if swap should be allowed. If so, swap!
    private boolean swapState(CalculatorState s) {
        state = s;
    return true;

    }

    private void runMethodFromButtonTag(String str) {
        switch (str) {
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
    class CalculatorClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String tag = view.getTag().toString();
            runMethodFromButtonTag(tag);
        }
    }

}