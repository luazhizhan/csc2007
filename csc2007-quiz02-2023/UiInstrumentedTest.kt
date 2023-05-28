package edu.singaporetech.sitiwsp

import android.content.Context
import android.util.Log
import android.view.View
import androidx.datastore.preferences.core.Preferences
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.hasProperty

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val testContext: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun testUI() {
        runTest {
            val preferences = testContext.IWSPOptions.data.firstOrNull()
        }
        onView(
        Matchers.allOf(
            withId(R.id.recyclerviewleft),
            withId(R.id.recyclerviewright),
            withId(R.id.selectionClear),
            withId(R.id.selectionSend),
            withId(R.id.studentId),
            withId(R.id.errorMsg),
            withId(R.id.jobCompany),
            withId(R.id.headerCompany),
            withId(R.id.jobSalary)
        )).check(matches(isDisplayed()))
    }
}
