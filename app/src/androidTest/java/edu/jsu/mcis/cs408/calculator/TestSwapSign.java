package edu.jsu.mcis.cs408.calculator;

import static androidx.test.espresso.action.ViewActions.click;
import static org.junit.Assert.assertEquals;
import static edu.jsu.mcis.cs408.calculator.TestUtils.parseEquation;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
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
    public void testButtonPress_SwapSign1() {
        for (ViewInteraction i : parseEquation("5±3=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-53"), bd);
        });
    }

    @Test
    public void testButtonPress_SwapSign2() {
        for (ViewInteraction i : parseEquation("4+2±8=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-24"), bd);
        });
    }

    @Test
    public void testButtonPress_SwapSign3() {
        for (ViewInteraction i : parseEquation("6+±=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_SwapSign4() {
        for (ViewInteraction i : parseEquation("8±±1=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("81"), bd);
        });
    }

    @Test
    public void testButtonPress_SwapSign5() {
        for (ViewInteraction i : parseEquation("±88x5±=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-440"), bd);
        });
    }

    @Test
    public void testButtonPress_SwapSign6() {
        for (ViewInteraction i : parseEquation("2x5±=±=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-50"), bd);
        });
    }

}
