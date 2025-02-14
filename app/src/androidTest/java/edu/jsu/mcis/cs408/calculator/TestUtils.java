package edu.jsu.mcis.cs408.calculator;

import java.math.BigDecimal;

public class TestUtils {

    public static BigDecimal parseOutputToBigDecimal(CalculatorController con) {
        if(con.getModel().getOutput().length() <= 0) return new BigDecimal(0);
        return new BigDecimal(con.getModel().getOutput().toString());
    }
}
