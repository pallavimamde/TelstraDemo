package com.techmahidra.telstrademo.ui.feature

import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.techmahidra.telstrademo.R
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class FeatureActivityTest {

    @get: Rule
    val featureActivityRule: ActivityTestRule<FeatureActivity> = ActivityTestRule(FeatureActivity::class.java , false , false)

    @Before
    fun setUp() {
        val intent = Intent()
        featureActivityRule.launchActivity(intent)
    }

    @Test
    fun onLaunchActionBarTitleIsDisplayed() {
        val actionBar: ActionBar? = featureActivityRule.activity.supportActionBar
        Assert.assertNotNull(actionBar?.title)
    }


    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(FeatureActivity::class.java)
    }

    @Test
    fun recyclerViewTestScrolling() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_general_info_list))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun recyclerViewTestScrollingToPositionEndIndex() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_general_info_list)).perform(ViewActions.swipeUp())
    }

    @Test
    fun recyclerViewTestScrollingToPositionTop() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_general_info_list)).perform(ViewActions.swipeDown())
    }
}