package com.team6.quickcashteam6;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)

public class UserChoicesEspressoTest {

    @Rule
    public ActivityScenarioRule<UserChoices> myRule = new ActivityScenarioRule<>(UserChoices.class);
    public IntentsTestRule<UserChoices> myIntentRule = new IntentsTestRule<>(UserChoices.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfMoved2LoginPageForEmployer() {
        onView(withId(R.id.nameTxtBox)).perform(typeText("samlol"));
        onView(withId(R.id.employerButton)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
    }

    @Test
    public void checkIfMoved2EmployerPageForEmployee() {
        onView(withId(R.id.nameTxtBox)).perform(typeText("samlol"));
        onView(withId(R.id.employeeButton)).perform(click());
        onView(withId(R.id.skill1)).perform(click());
        onView(withId(R.id.skill2)).perform(click());
        onView(withId(R.id.skill3)).perform(click());
        onView(withId(R.id.skillsregister)).perform(closeSoftKeyboard());
        onView(withId(R.id.skillsregister)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
    }


}
