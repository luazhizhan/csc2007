package edu.singaporetech.grocerylist

import android.util.Log

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.rules.activityScenarioRule

import androidx.test.filters.LargeTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.*
import androidx.test.uiautomator.*

import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsNot.not
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ViewIDInstrumentedTest {

    companion object {
        private val TAG = ViewIDInstrumentedTest::class.simpleName
        protected const val REPORT_ITEM_MAX_LENGTH = 100
    }

    // UiAutomator device
    protected lateinit var device: UiDevice

    @get:Rule
    var activityRule = activityScenarioRule<MainActivity>()

    @Before
    fun setupUiAutomator() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun onStartMainActivity_uiMatches() {
        Log.i(
            TAG,
            "\n### TEST existence of recyclerViewGroceryList, groceryListTextView, first list item"
        )
        onView(withId(R.id.recyclerViewGroceryList)).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.groceryListTextView), withText("Cilantro"))).check(matches(isDisplayed()))
    }

}