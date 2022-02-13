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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)

public class RegisterActivityEspressoTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> myRule = new ActivityScenarioRule<>(RegisterActivity.class);
    public IntentsTestRule<RegisterActivity> myIntentRule = new IntentsTestRule<>(RegisterActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkRegister()  {
        onView(withId(R.id.rEmail)).perform(typeText("abc@dal.ca"));
        onView(withId(R.id.rPassword)).perform(typeText("Abc@12345"));
        onView(withId(R.id.buttonRegister)).perform(click());
    }

    @Test
    public void checkIfMoved2EmployerPage() {
        onView(withId(R.id.rEmail)).perform(typeText("sam@dal.ca"));
        onView(withId(R.id.rPassword)).perform(typeText("Sam@12345"));
        onView(withId(R.id.buttonRegister)).perform(click());
        intended(hasComponent(UserChoices.class.getName()));
    }

}

