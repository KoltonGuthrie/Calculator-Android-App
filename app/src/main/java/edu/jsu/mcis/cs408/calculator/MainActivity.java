package edu.jsu.mcis.cs408.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        layout = binding.main;

        setContentView(view);

        initLayout();

        //addListenersToButtons();

    }

    private void initLayout() {

        int textViewId = View.generateViewId(); // generate new ID
        TextView tv = new TextView(this); // create new TextView
        tv.setId(textViewId); // assign ID
        tv.setTag("output"); // assign tag (for acquiring references later)
        tv.setText("0"); // set text (using a string resource)
        tv.setTextSize(24); // set size

        ViewGroup.LayoutParams params = tv.getLayoutParams();
        params.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
        params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        tv.setLayoutParams(params);

        layout.addView(tv); // add to layout

        int KEYS_HEIGHT = 4;
        int KEYS_WIDTH = 5;

        int[][] horizontals = new int[KEYS_HEIGHT][KEYS_WIDTH];
        int[][] verticals = new int[KEYS_WIDTH][KEYS_HEIGHT];

        String[] btnStrings = getResources().getStringArray(R.array.buttons);
        String[] buttonTags = getResources().getStringArray(R.array.buttonTags);

        for (int i = 0; i < KEYS_HEIGHT; i++) {
            for (int j = 0; j < KEYS_WIDTH; j++) {
                int idx = i * KEYS_WIDTH + j;
                String string = btnStrings[idx];
                Log.i("MainActivity" ,string);
                int id = View.generateViewId(); // generate new ID
                Button b = new Button(this); // create new TextView
                b.setId(id); // assign ID
                b.setTag(buttonTags[idx]);
                b.setText(string); // set text (using a string resource)
                b.setTextSize(24); // set size
                layout.addView(b); // add to layout

                horizontals[i][j] = id;
                verticals[j][i] = id;

                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String msg = getMessageFromButtonTag(view.getTag().toString());

                            if (msg == null) return;

                            Toast toast = Toast.makeText(binding.getRoot().getContext(), msg, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

            }
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(layout);


        set.connect(textViewId, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);

        for (int i = 0; i < verticals.length; i++) {
            set.createVerticalChain(ConstraintSet.PARENT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM, verticals[i], null, ConstraintSet.CHAIN_PACKED);
        }

        for (int i = 0; i < horizontals.length; i++) {
            set.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT, horizontals[i], null, ConstraintSet.CHAIN_PACKED);
        }

        set.applyTo(layout);
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