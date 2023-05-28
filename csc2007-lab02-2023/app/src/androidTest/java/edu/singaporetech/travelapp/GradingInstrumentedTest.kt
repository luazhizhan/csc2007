package edu.singaporetech.travelapp

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
class GradingInstrumentedTest {

    companion object {
        private val TAG = GradingInstrumentedTest::class.simpleName
        protected const val REPORT_ITEM_MAX_LENGTH = 100
    }

    // UiAutomator device
    protected lateinit var device: UiDevice

    @get:Rule
    var testLogger = TestLogger()

    @get:Rule
    var activityRule = activityScenarioRule<MainActivity>()

    @Before
    fun setupUiAutomator() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }


    @Test
    fun onLaunch_buttonsWorking() {
        Log.i(
            TAG,
            "\n### TEST buttons \"TEMPERATURE CONVERTER\", \"CURRENCY CONVERTER\" & \"EMAIL FRIEND\" exist; " +
                    "buttons can be clicked and respective activity launched"
        )
        onView(withText(Matchers.equalToIgnoringCase("temperature converter"))).perform(click())
        pressBack()
        onView(withText(Matchers.equalToIgnoringCase("currency converter"))).perform(click())
        pressBack()
        onView(withText(Matchers.equalToIgnoringCase("email friend"))).perform(click())
        pressBack()
    }

    /**
     * This will watch all tests in a rule and do post actions.
     * 1. Add test info to report str
     * 2. accumulate marks.
     */
    inner class TestLogger : TestWatcher() {
        override fun succeeded(description: Description) {
            super.succeeded(description)
            Log.i(TAG, "- test PASSED.  \n")
        }

        override fun failed(
            e: Throwable,
            description: Description
        ) {
            super.failed(e, description)
            var errStr = e.message
            if (errStr!!.length > REPORT_ITEM_MAX_LENGTH) errStr =
                errStr.split("\n").toTypedArray()[0] + "... (err msg too long)"
            Log.i(
                TAG,
                "- FAILED Reason-> $errStr  \n"
            )
        }
    }

}