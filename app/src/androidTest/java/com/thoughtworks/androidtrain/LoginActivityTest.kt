package com.thoughtworks.androidtrain

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thoughtworks.androidtrain.view.LoginActivity
import com.thoughtworks.androidtrain.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun should_jump_to_login_page_and_click_checkbox_as_checked_correctly() {

        onView(withText("login_layout")).perform(click())
        intended(hasComponent(LoginActivity::class.java.name))

        onView(ViewMatchers.withId(R.id.rememberMe)).perform(click())
        onView(ViewMatchers.withId(R.id.rememberMe)).check(ViewAssertions.matches(ViewMatchers.isChecked()))
    }
}