package com.ocean.tipcaladv

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){
        Espresso.onView(ViewMatchers.withId(R.id.cost_of_service_edit_text))
            .perform(ViewActions.typeText("50.00"))

        Espresso.onView(ViewMatchers.withId(R.id.calculate_button)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tip_result))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("$10.00"))))
    }

    @Test
    fun calculate_18_percent_tip() {
        Espresso.onView(ViewMatchers.withId(R.id.cost_of_service_edit_text))
            .perform(ViewActions.typeText("50.00"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.option_eighteen_percent))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.calculate_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tip_result))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("$9.00"))))
    }

    @Test
    fun calculate_15_percent_tip_round_up() {
        Espresso.onView(ViewMatchers.withId(R.id.cost_of_service_edit_text))
            .perform(ViewActions.typeText("50.00"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.option_fifteen_percent))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.calculate_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tip_result))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("$8.00"))))
    }
    @Test
    fun calculate_15_percent_tip_no_rounding() {
        Espresso.onView(ViewMatchers.withId(R.id.cost_of_service_edit_text))
            .perform(ViewActions.typeText("50.00"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.option_fifteen_percent))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.round_up_switch))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.calculate_button))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tip_result))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("$7.50"))))
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ocean.tipcaladv", appContext.packageName)
    }
}