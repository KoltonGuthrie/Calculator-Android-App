package edu.jsu.mcis.cs408.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addListenersToButtons();



    }

    private void addListenersToButtons() {

        buttons.add(binding.btn0);
        buttons.add(binding.btn1);
        buttons.add(binding.btn2);
        buttons.add(binding.btn3);
        buttons.add(binding.btn4);
        buttons.add(binding.btn5);
        buttons.add(binding.btn6);
        buttons.add(binding.btn7);
        buttons.add(binding.btn8);
        buttons.add(binding.btn9);
        buttons.add(binding.btnPlusSub);
        buttons.add(binding.btnDec);
        buttons.add(binding.btnPlus);
        buttons.add(binding.btnEqual);
        buttons.add(binding.btnMult);
        buttons.add(binding.btnSub);
        buttons.add(binding.btnDiv);
        buttons.add(binding.btnPrc);
        buttons.add(binding.btnSqrt);
        buttons.add(binding.btnClr);

        for(Button btn : buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String msg = getMessageFromButtonTag(view.getTag().toString());

                    if(msg == null) return;

                    Toast toast = Toast.makeText(binding.getRoot().getContext(), msg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    private String getMessageFromButtonTag(String str) {
        switch (str) {
            case "btn0":
                return getResources().getString(R.string.button_zero);
            case "btn1":
                return getResources().getString(R.string.button_one);
            case "btn2":
                return getResources().getString(R.string.button_two);
            case "btn3":
                return getResources().getString(R.string.button_three);
            case "btn4":
                return getResources().getString(R.string.button_four);
            case "btn5":
                return getResources().getString(R.string.button_five);
            case "btn6":
                return getResources().getString(R.string.button_six);
            case "btn7":
                return getResources().getString(R.string.button_seven);
            case "btn8":
                return getResources().getString(R.string.button_eight);
            case "btn9":
                return getResources().getString(R.string.button_nine);
            case "btnPlusSub":
                return getResources().getString(R.string.button_plus_subtract);
            case "btnDec":
                return getResources().getString(R.string.button_decimal);
            case "btnPlus":
                return getResources().getString(R.string.button_plus);
            case "btnEqual":
                return getResources().getString(R.string.button_equal);
            case "btnMult":
                return getResources().getString(R.string.button_multiply);
            case "btnSub":
                return getResources().getString(R.string.button_subtract);
            case "btnDiv":
                return getResources().getString(R.string.button_divide);
            case "btnPrc":
                return getResources().getString(R.string.button_percentage);
            case "btnSqrt":
                return getResources().getString(R.string.button_sqrt);
            case "btnClr":
                return getResources().getString(R.string.button_clear);
            default:
                return null;
        }
    }

}