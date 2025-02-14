package edu.jsu.mcis.cs408.calculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import static edu.jsu.mcis.cs408.calculator.TestUtils.parseEquation;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
public class TestSwapSign {

    private ActivityScenario<MainActivity> scenario;
    private CalculatorController controller;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> controller = activity.controller);
    }

    @Test
    public void testButtonPress_Addition1() {
        Arrays.stream(parseEquation("5±3=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-53"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition2() {
        Arrays.stream(parseEquation("4+2±8=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-24"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition3() {
        Arrays.stream(parseEquation("6+±=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition4() {
        Arrays.stream(parseEquation("8±±1=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("81"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition5() {
        Arrays.stream(parseEquation("±88x5±=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-440"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition6() {
        Arrays.stream(parseEquation("2x5±=±=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-50"), bd);
        });
    }

}
