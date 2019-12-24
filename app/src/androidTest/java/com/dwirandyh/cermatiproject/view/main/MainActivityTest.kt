package com.dwirandyh.cermatiproject.view.main

import android.util.Log.e
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.rule.ActivityTestRule
import com.dwirandyh.cermatiproject.R
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertTrue

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testSearchDataSuccess() {
        onView(withId(R.id.edit_query)).perform(typeText("dwi"))
        Thread.sleep(3000)

        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.recycler_user)
        val itemCount = recyclerView.adapter?.itemCount ?: 0

        assertTrue(itemCount > 0)
    }

    @Test
    fun testSearchDataNotFound() {
        onView(withId(R.id.edit_query)).perform(typeText("zzzsfdzfdsfzsfsdzfsd"))
        Thread.sleep(3000)

        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.recycler_user)
        val itemCount = recyclerView.adapter?.itemCount ?: 0

        assertTrue(itemCount == 0)
    }

    @Test
    fun testInputEmptyQuery() {
        onView(withId(R.id.edit_query)).perform(typeText("dwi"))
        Thread.sleep(1000)
        onView(withId(R.id.edit_query)).perform(clearText())
        Thread.sleep(1000)
        onView(withId(R.id.text_message)).check(matches(withText(containsString("username"))))
    }
}