package edu.singaporetech.vaccinereg

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.activityScenarioRule
import org.hamcrest.Matchers
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalToIgnoringCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * Note that all test functions are self-documenting via the Logging contents
 *
 * The tests in here will naturally fail before you implement anything.
 * This tests will pass once you have the UI elements in place for the RegistrationActivity.
 * This is also just one of the many tests that the eventual grading script will run.
 */
@RunWith(AndroidJUnit4::class)
class ViewInstrumentedTest {
    @get:Rule
    var activityRule = activityScenarioRule<RegistrationActivity>()

    @Test
    fun onStart_RegistrationActivityUiMatches() {
        Log.i(TAG, """
            
            ### RegistrationActivity layout and UI elements similar to screenshot 
            - check ImageView exists with id "imageViewCDCVoucher"
            - check Button exists with id "buttonLogin" and text $GETSTARTED_BTN_TXT" and is clickable
            """.trimIndent())

        onView(
            Matchers.allOf(
                withId(R.id.imageViewVaccination),
                withClassName(containsString("Image"))
            )
        )
            .check(matches(isDisplayed()))


        onView(withId(R.id.buttonGetStarted))
            .check(matches(isDisplayed()))
        onView(withText(equalToIgnoringCase(GETSTARTED_BTN_TXT)))
            .perform(click())

    }

    companion object {
        private val TAG = ViewInstrumentedTest::class.simpleName
        private const val GETSTARTED_BTN_TXT = "GET STARTED"
    }
}