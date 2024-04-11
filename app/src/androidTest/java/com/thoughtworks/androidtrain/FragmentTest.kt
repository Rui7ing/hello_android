package com.thoughtworks.androidtrain

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thoughtworks.androidtrain.view.LanguageSelectionActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentTest {


    @get:Rule
    var activity = ActivityScenarioRule(LanguageSelectionActivity::class.java)

    @Test
    fun launchFragmentAndVerifyUI() {

        onView(withId(R.id.JavaButton)).check(matches(withText("JAVA")))
        onView(withId(R.id.AndroidButton)).check(matches(withText("Android")))

        onView(withId(R.id.JavaButton)).perform(click())
        onView(withId(R.id.javaText)).check(matches(withText("Java is a High Level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.")))

        onView(withId(R.id.AndroidButton)).perform(click())
        onView(withId(R.id.androidText)).check(matches(withText("Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smartphones and tablets.")))

    }
}