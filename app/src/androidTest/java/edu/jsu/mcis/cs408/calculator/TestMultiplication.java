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
public class TestMultiplication {

    private ActivityScenario<MainActivity> scenario;
    private CalculatorController controller;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);

        scenario.onActivity(activity -> controller = activity.controller);
    }

    @Test
    public void testButtonPress_Multiplication1() {
        onView(withTagValue(is("btn5"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn3"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("15"), bd);
        });
    }

    @Test
    public void testButtonPress_Multiplication2() {
        onView(withTagValue(is("btn4"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("64"), bd);
        });
    }

    @Test
    public void testButtonPress_Multiplication3() {
        onView(withTagValue(is("btn6"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnEqual"))).perform(click());
        }

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("46656"), bd);
        });
    }

    @Test
    public void testButtonPress_Multiplication4() {
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("16"), bd);
        });
    }

    @Test
    public void testButtonPress_Multiplication5() {
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_Multiplication6() {
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());
        onView(withTagValue(is("btn9"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("72"), bd);
        });
    }

    @Test
    public void testButtonPress_Multiplication7() {
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn9"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("576"), bd);
        });
    }

}
