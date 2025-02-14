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
        onView(withTagValue(is("btn5"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
        onView(withTagValue(is("btn3"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("3"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage2() {
        onView(withTagValue(is("btn4"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("12"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage3() {
        onView(withTagValue(is("btn6"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
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
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn6"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage5() {
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnMult"))).perform(click());
        onView(withTagValue(is("btn6"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("7"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage6() {
        onView(withTagValue(is("btn4"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btn5"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn8"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("467"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage7() {
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn5"))).perform(click());
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
        onView(withTagValue(is("btn5"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        onView(withTagValue(is("btn5"))).perform(click());
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
        onView(withTagValue(is("btn5"))).perform(click());
        onView(withTagValue(is("btnPrc"))).perform(click());
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("0"), bd);
        });
    }

    @Test
    public void testButtonPress_Percentage10() {
        onView(withTagValue(is("btn1"))).perform(click());
        onView(withTagValue(is("btn2"))).perform(click());
        onView(withTagValue(is("btn0"))).perform(click());
        onView(withTagValue(is("btnPlus"))).perform(click());
        for(int i = 0; i < 5; i++) {
            onView(withTagValue(is("btnPrc"))).perform(click());
        }
        onView(withTagValue(is("btnEqual"))).perform(click());

        scenario.onActivity(activity -> {
            BigDecimal bd = TestUtils.parseOutputToBigDecimal(controller);
            assertEquals(new BigDecimal("418.5984"), bd);
        });
    }

}
