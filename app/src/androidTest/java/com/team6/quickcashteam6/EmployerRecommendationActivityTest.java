package com.team6.quickcashteam6;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class EmployerRecommendationActivityTest {
    @Rule
   public ActivityScenarioRule<EmployerRecommendationActivity> myRule = new ActivityScenarioRule<EmployerRecommendationActivity>(EmployerRecommendationActivity.class);
    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void testRecommendButton (){
        onView(withId(R.id.RecommendButton1)).perform(click());
        onView(withId(R.id.linearLayout1)).check(matches(isDisplayed()));
        onView(withId(R.id.linearLayout2)).check(matches(isDisplayed()));
    }

}