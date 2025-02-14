package edu.jsu.mcis.cs408.calculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(AndroidJUnit4.class)
public class TestDivision {

    private ActivityScenario<MainActivity> scenario;
    private CalculatorController controller;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> controller = activity.controller);
    }

    @Test
    public void testButtonPress_Division1() {
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btn4"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("2"), bd);
        });
    }

    @Test
    public void testButtonPress_Division2() {
        onView(withTagValue(is("btn4"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0.25"), bd);
        });
    }

    @Test
    public void testButtonPress_Division3() {
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnEqual"))).perform(click());
        }

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0.0001"), bd);
        });
    }

    @Test
    public void testButtonPress_Division4() {
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd); // Division by 0 defaults to 0
        });
    }

    @Test
    public void testButtonPress_Division5() {
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_Division6() {
        onView(withTagValue(is("btnSub"))).perform(click());
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());
        onView(withTagValue(is("btnDiv"))).perform(click());
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("-0.1"), bd);
        });
    }

}
