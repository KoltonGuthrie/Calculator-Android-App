package edu.jsu.mcis.cs408.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.ArrayList;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Button> buttons = new ArrayList<>();
    private CalculatorState state = CalculatorState.LEFT;
    private StringBuffer equation = new StringBuffer();
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

        output.append(s);
        updateOutput();
    }

    private void updateOutput() {
        binding.output.setText(output.toString());
    }

    private void updateEquation() {
        binding.equation.setText(equation.toString());
    }

    private void submitOperator(CalculatorOperator oper) {
        if(oper.equals(CalculatorOperator.CLEAR)) {
            if(swapState(CalculatorState.LEFT)) {
                clear();
            }
        }
        else if(oper.equals(CalculatorOperator.SWAP_SIGN)) {
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
        /*
        Does not work atm. The equation text will need to be dynamically created using the leftValue, operator, and rightValue.
        This is because if an equation such as 3 + 4 is completed, the output is 7.
        However, if the equals is clicked any following time, the leftValue needs to be replaced with the output of the first equation
         */
        else if(oper.equals(CalculatorOperator.EQUAL)) {
            if(output.length() <= 0 || output.charAt(0) == '0') {
                return;
            }

            if(!state.equals(CalculatorState.LEFT)) {
                equation.append(output.toString());

                updateEquation();

                rightValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                binding.output.setText(String.valueOf(leftValue.add(rightValue)));
            } else {
                leftValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));
                binding.output.setText(String.valueOf(leftValue));
            }


        } else {

            if(output.length() <= 0) output.append('0');

            operator = oper;

            if(state.equals(CalculatorState.OPERATOR)) {
                equation.deleteCharAt(equation.length()-1);
            } else if(!swapState(CalculatorState.OPERATOR)) {
                // WHY COULDN'T I SWAP!!?!??
                Log.wtf("MainActivity", "Couldn't stop to OPERATOR state?!?");
                return;
            } else {
                equation.append(output.toString());
            }

            equation.append(oper.toString());

            updateEquation();

            leftValue = BigDecimal.valueOf(Double.parseDouble(output.toString()));

        }
    }

    private void clear() {
        clearEquation();
        clearOutput();

        operator = null;
        leftValue = null;
        rightValue = null;
    }
    private void clearEquation() {
        equation.setLength(0);
        binding.equation.setText("");
    }

    private void clearOutput() {
        output.setLength(0);
        binding.output.setText("0");
    }


    // Chech if swap should be allowed. If so, swap!
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