package edu.jsu.mcis.cs408.calculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;
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
public class TestPercentage {

    private ActivityScenario<MainActivity> scenario;
    private CalculatorController controller;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> controller = activity.controller);
    }

    @Test
    public void testButtonPress_Percentage1() {
        for (ViewInteraction i : parseEquation("5%3=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("3"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage2() {
        for (ViewInteraction i : parseEquation("4+2%8=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("12"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage3() {
        for (ViewInteraction i : parseEquation("6+%")) i.perform(click());

        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnEqual"))).perform(click());
        }

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7.8"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage4() {
        for (ViewInteraction i : parseEquation("100x6%+1=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage5() {
        for (ViewInteraction i : parseEquation("100x6%=+1=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage6() {
        for (ViewInteraction i : parseEquation("425+8%=+8=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("467"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage7() {
        for (ViewInteraction i : parseEquation("100+5")) i.perform(click());

        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnPrc"))).perform(click());
        }
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("105"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage8() {
        for (ViewInteraction i : parseEquation("500+5")) i.perform(click());

        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnPrc"))).perform(click());
        }
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("16125"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage9() {
        for (ViewInteraction i : parseEquation("5%=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage10() {
        for (ViewInteraction i : parseEquation("120+")) i.perform(click());

        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnPrc"))).perform(click());
        }
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("418.5984"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage11() {
        for (ViewInteraction i : parseEquation("2-123=%=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("23.41"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage12() {
        for (ViewInteraction i : parseEquation("%%3=")) i.perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("3"), bd);
        });
    }



}
