package edu.jsu.mcis.cs408.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtils {

    public static BigDecimal sqrt(BigDecimal value, int precision) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("Invalid Input");
        }

        BigDecimal guess = value.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
        BigDecimal tolerance = BigDecimal.ONE.scaleByPowerOfTen(-precision);

        while (true) {
            BigDecimal nextGuess = value.divide(guess, MathContext.DECIMAL128)
                    .add(guess)
                    .divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);

            BigDecimal difference = nextGuess.subtract(guess).abs();
            if (difference.compareTo(tolerance) <= 0) {
                return nextGuess.setScale(precision, RoundingMode.HALF_UP);
            }

            guess = nextGuess;
        }
    }
}