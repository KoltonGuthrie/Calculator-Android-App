package edu.jsu.mcis.cs408.calculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;

import android.util.Log;

import androidx.test.espresso.ViewInteraction;

import java.math.BigDecimal;

public class TestUtils {

    public static BigDecimal parseOutputToBigDecimal(CalculatorController con) {
        if(con.getModel().getOutput().length() <= 0) return new BigDecimal(0);
        return new BigDecimal(con.getModel().getOutput().toString());
    }

    /*
        This function will NOT follow the order of operations and will only
        give the ViewInteraction in the order they are provided.
     */
    public static ViewInteraction[] parseEquation(String s) {
        String[] arr = s.trim().replaceAll("\\s", "").toLowerCase().split("(?!^)");

        ViewInteraction[] result = new ViewInteraction[arr.length];

        for(int i = 0; i < arr.length; i++) {
            ViewInteraction vi = getViewInteraction(arr[i]);
            if(vi == null) throw new IllegalArgumentException("Invalid character in equation: " + arr[i]);
            result[i] = vi;
        }

        return result;
    }

    private static ViewInteraction getViewInteraction(String s) {
        switch (s) {
            case "0":
                return onView(withTagValue(is("btn0")));
            case "1":
                return onView(withTagValue(is("btn1")));
            case "2":
                return onView(withTagValue(is("btn2")));
            case "3":
                return onView(withTagValue(is("btn3")));
            case "4":
                return onView(withTagValue(is("btn4")));
            case "5":
                return onView(withTagValue(is("btn5")));
            case "6":
                return onView(withTagValue(is("btn6")));
            case "7":
                return onView(withTagValue(is("btn7")));
            case "8":
                return onView(withTagValue(is("btn8")));
            case "9":
                return onView(withTagValue(is("btn9")));
            case "±":
                return onView(withTagValue(is("btnSwap")));
            case ".":
                return onView(withTagValue(is("btnDec")));
            case "+":
                return onView(withTagValue(is("btnPlus")));
            case "=":
                return onView(withTagValue(is("btnEqual")));
            case "x":
                return onView(withTagValue(is("btnMult")));
            case "×":
                return onView(withTagValue(is("btnMult")));
            case "-":
                return onView(withTagValue(is("btnSub")));
            case "/":
                return onView(withTagValue(is("btnDiv")));
            case "÷":
                return onView(withTagValue(is("btnDiv")));
            case "%":
                return onView(withTagValue(is("btnPrc")));
            case "√":
                return onView(withTagValue(is("btnSqrt")));
            case "c":
                return onView(withTagValue(is("btnClr")));
            default:
                return null;
        }
    }


}
