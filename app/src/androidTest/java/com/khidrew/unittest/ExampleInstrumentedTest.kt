package com.khidrew.unittest

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val scenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun recyclerViewIsDisplayed(){
        onView(withId(R.id.rv)).check(matches(isDisplayed()))
    }


    @Test
    fun testRecyclerViewScroll(){
        onView(withId(R.id.rv))
            .perform(scrollToPosition<ViewHolder>(10))
    }

}