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
public class TestAddition {

    private ActivityScenario<MainActivity> scenario;
    private CalculatorController controller;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> controller = activity.controller);
    }

    @Test
    public void testButtonPress_Addition1() {
        onView(withTagValue(is("btn5"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn3"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("8"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition2() {
        onView(withTagValue(is("btn4"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("14"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition3() {
        onView(withTagValue(is("btn6"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnEqual"))).perform(click());
        }

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("36"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition4() {
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("9"), bd);
        });
    }

    @Test
    public void testButtonPress_Addition5() {
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("88"), bd);
        });
    }

}
