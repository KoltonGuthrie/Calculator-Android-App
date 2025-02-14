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
public class TestLargeEquation {

    private ActivityScenario<MainActivity> scenario;
    private CalculatorController controller;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> controller = activity.controller);
    }

    @Test
    public void testButtonPress_LargeEquation1() {
        Arrays.stream(parseEquation("4.5+=√/3+7%=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("1.07"), bd);
        });
    }

    @Test
    public void testButtonPress_LargeEquation2() {
        Arrays.stream(parseEquation("1596x8-230x100/4+15.4=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("313465.4"), bd);
        });
    }

    @Test
    public void testButtonPress_LargeEquation3() {
        Arrays.stream(parseEquation("4535+5%=x53+43=/2-75=+47±3.2-34=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("125625.675"), bd);
        });
    }

    @Test
    public void testButtonPress_LargeEquation4() {
        Arrays.stream(parseEquation("9√+6=√x564=-3x3.4=/2-123=%=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("75408.5289"), bd);
        });
    }

    @Test
    public void testButtonPress_LargeEquation5() {
        // √  ±
        Arrays.stream(parseEquation("49√±x3±+44.2x7=/4±-8%9=±=")).forEach(i -> i.perform(click()));

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("114.1"), bd);
        });
    }

}
