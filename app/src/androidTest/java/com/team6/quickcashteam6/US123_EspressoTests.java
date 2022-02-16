package com.team6.quickcashteam6;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.Log;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class US123_EspressoTests {
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

    /**
     * Tests if user is moved from the login page to the register page when the register button is clicked.
     */
    @Test
    public void checkIfMovedToRegisterPage(){
        onView(withId(R.id.registrationLink)).perform(click());
        intended(hasComponent(RegisterActivity.class.getName()));
    }

    /**
     * Tests if employer is moved back to the Employer page after they have successfully posted a job.
     */
    @Test
    public void checkIfMovedBackToLogin(){
        onView(withId(R.id.registrationLink)).perform(click());
        onView(withId(R.id.rEmail)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.rPassword)).perform(typeText("@Qwerty123"));
        onView(withId(R.id.rPassword)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
    }

    /**
     * Tests if correct error message is shown when email field is empty.
     */
    @Test
    public void checkIfEmailIsEmpty() {
        onView(withId(R.id.registrationLink)).perform(click());
        onView(withId(R.id.rEmail)).perform(typeText(""));
        onView(withId(R.id.rPassword)).perform(typeText("@Qwerty123"));
        onView(withId(R.id.rPassword)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText("")));
    }

    /**
     * Tests if correct error message is shown when password field is empty.
     */
    @Test
    public void checkIfPasswordIsEmpty() {
        onView(withId(R.id.registrationLink)).perform(click());
        onView(withId(R.id.rEmail)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.rPassword)).perform(typeText(""));
        onView(withId(R.id.rPassword)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText("")));
    }

}
