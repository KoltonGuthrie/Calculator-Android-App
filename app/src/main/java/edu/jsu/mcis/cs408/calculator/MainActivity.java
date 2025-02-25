package edu.jsu.mcis.cs408.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CalculatorController controller;
    private final boolean DEBUG_MODE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        controller = new CalculatorController(new CalculatorModel(), this);
        addListenersToButtons();

        if(!DEBUG_MODE) {
            binding.rightOutput.setVisibility(View.GONE);
            binding.operatorOutput.setVisibility(View.GONE);
            binding.leftOutput.setVisibility(View.GONE);
            binding.stateOutput.setVisibility(View.GONE);
        }
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

    public void updateOutput(String output) {
        binding.output.setText(output);
    }

    public void updateEquation(String equation) {
        binding.equation.setText(equation);
    }

    public void updateStateOutput(CalculatorState state) {
        binding.stateOutput.setText(state.toString());
    }

    public void updateLeftOutput(BigDecimal bd) {
        String out = bd == null ? "" : bd.toString();
        binding.leftOutput.setText(out);
    }

    public void updateRightOutput(BigDecimal bd) {
        String out = bd == null ? "" : bd.toString();
        binding.rightOutput.setText(out);
    }

    public void updateOperatorOutput(CalculatorOperator co) {
        String out = co == null ? "" : co.toString();
        binding.operatorOutput.setText(out);
    }

    class CalculatorClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String tag = view.getTag().toString();
            controller.handleInput(tag);
        }
    }

    public CalculatorController getController() {
        return controller;
    }
}