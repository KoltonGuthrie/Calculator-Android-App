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
        Arrays.stream(parseEquation("5%3=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("3"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage2() {
        Arrays.stream(parseEquation("4+2%8=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("12"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage3() {
        Arrays.stream(parseEquation("6+%")).forEach(i -> i.perform(click()));

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
        Arrays.stream(parseEquation("100x6%+1=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage5() {
        Arrays.stream(parseEquation("100x6%=+1=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage6() {
        Arrays.stream(parseEquation("425+8%=+8=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("467"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage7() {
        Arrays.stream(parseEquation("100+5")).forEach(i -> i.perform(click()));

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
        Arrays.stream(parseEquation("500+5")).forEach(i -> i.perform(click()));

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
        Arrays.stream(parseEquation("5%=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage10() {
        Arrays.stream(parseEquation("120+")).forEach(i -> i.perform(click()));

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
        Arrays.stream(parseEquation("2-123=%=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("23.41"), bd);
        });
    }



}
