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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class US6_EspressoTests {
    @Rule
    public ActivityScenarioRule<EmployerPageActivity> myRule = new ActivityScenarioRule<>(EmployerPageActivity.class);
    public IntentsTestRule<EmployerPageActivity> myIntentRule = new IntentsTestRule<>(EmployerPageActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    /**
     * Tests if employer is moved from the Employer page to the PostJob page when the postJob button is clicked.
     */
    @Test
    public void checkIfMovedToPostJobPage(){
        onView(withId(R.id.postJobButton)).perform(click());
        intended(hasComponent(PostJobActivity.class.getName()));
    }

    /**
     * Tests if employer is moved back to the Employer page after they have successfully posted a job.
     */
    @Test
    public void checkIfMovedBackToEmployerPage(){
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.submitJobButton)).perform(click());
        intended(hasComponent(EmployerPageActivity.class.getName()));
    }


}
