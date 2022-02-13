package com.team6.quickcashteam6;
import android.content.Context;
import android.view.View;

import androidx.test.espresso.assertion.PositionAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
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
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class LoginActivityEspressoTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> myRule = new ActivityScenarioRule<>(LoginActivity.class);
    public IntentsTestRule<LoginActivity> myIntentRule = new IntentsTestRule<>(LoginActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkLogin()  {
        onView(withId(R.id.lEmail)).perform(typeText("sam@dal.ca"));
        onView(withId(R.id.lPassword)).perform(typeText("Sam@12345"));
        onView(withId(R.id.buttonLogin)).perform(click());
    }

    @Test
    public void checkIfMoved2EmployerPage() {
        onView(withId(R.id.lEmail)).perform(typeText("sam@dal.ca"));
        onView(withId(R.id.lPassword)).perform(typeText("Sam@12345"));
        onView(withId(R.id.buttonLogin)).perform(click());
        intended(hasComponent(EmployerPageActivity.class.getName()));
    }

}
