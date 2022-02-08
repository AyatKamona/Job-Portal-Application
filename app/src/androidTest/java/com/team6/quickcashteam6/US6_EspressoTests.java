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
     * MUST TEST INDIVIDUALLY
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
        onView(withId(R.id.insert_job_title)).perform(typeText("Babysitting"));
        onView(withId(R.id.insert_job_payment)).perform(typeText("$20/hr"));
        onView(withId(R.id.insert_start_time)).perform(typeText("2022-02-14 at 8pm"));
        onView(withId(R.id.insert_skills)).perform(typeText("Responsible"));
        onView(withId(R.id.insert_job_description)).perform(typeText("Babysit a 4 year old"));
        onView(withId(R.id.insert_job_description)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitJobButton)).perform(click());
        intended(hasComponent(EmployerPageActivity.class.getName()));
    }

    /**
     * Tests if correct error message is shown when job title field is empty.
     */
    @Test
    public void checkIfJobTitleIsEmpty() {
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.insert_job_title)).perform(typeText(""));
        onView(withId(R.id.insert_job_payment)).perform(typeText("$20/hr"));
        onView(withId(R.id.insert_start_time)).perform(typeText("2022-02-14 at 8pm"));
        onView(withId(R.id.insert_skills)).perform(typeText("Responsible"));
        onView(withId(R.id.insert_job_description)).perform(typeText("Babysit a 4 year old"));
        onView(withId(R.id.insert_job_description)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitJobButton)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText(R.string.EMPTY_JOB_TITLE)));
    }

    /**
     * Tests if correct error message is shown when payment field is empty.
     */
    @Test
    public void checkIfPaymentIsEmpty() {
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.insert_job_title)).perform(typeText("Babysitting"));
        onView(withId(R.id.insert_job_payment)).perform(typeText(""));
        onView(withId(R.id.insert_start_time)).perform(typeText("2022-02-14 at 8pm"));
        onView(withId(R.id.insert_skills)).perform(typeText("ResponsibleResponsible"));
        onView(withId(R.id.insert_job_description)).perform(typeText("Babysit a 4 year old"));
        onView(withId(R.id.insert_job_description)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitJobButton)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText(R.string.EMPTY_PAYMENT)));
    }

    /**
     * Tests if correct error message is shown when start time field is empty.
     */
    @Test
    public void checkIfStartTimeIsEmpty() {
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.insert_job_title)).perform(typeText("Babysitting"));
        onView(withId(R.id.insert_job_payment)).perform(typeText("$20/hr"));
        onView(withId(R.id.insert_start_time)).perform(typeText(""));
        onView(withId(R.id.insert_skills)).perform(typeText("Responsible"));
        onView(withId(R.id.insert_job_description)).perform(typeText("Babysit a 4 year old"));
        onView(withId(R.id.insert_job_description)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitJobButton)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText(R.string.EMPTY_START_TIME)));
    }

    /**
     * Tests if correct error message is shown when skills field is empty.
     */
    @Test
    public void checkIfSkillsIsEmpty() {
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.insert_job_title)).perform(typeText("Babysitting"));
        onView(withId(R.id.insert_job_payment)).perform(typeText("$20/hr"));
        onView(withId(R.id.insert_start_time)).perform(typeText("2022-02-14 at 8pm"));
        onView(withId(R.id.insert_skills)).perform(typeText(""));
        onView(withId(R.id.insert_job_description)).perform(typeText("Babysit a 4 year old"));
        onView(withId(R.id.insert_job_description)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitJobButton)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText(R.string.EMPTY_SKILLS)));
    }

    /**
     * Tests if correct error message is shown when Description field is empty.
     */
    @Test
    public void checkIfDescriptionIsEmpty() {
        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.insert_job_title)).perform(typeText("Babysitting"));
        onView(withId(R.id.insert_job_payment)).perform(typeText("$20/hr"));
        onView(withId(R.id.insert_start_time)).perform(typeText("2022-02-14 at 8pm"));
        onView(withId(R.id.insert_skills)).perform(typeText("Responsible"));
        onView(withId(R.id.insert_job_description)).perform(typeText(""));
        onView(withId(R.id.insert_job_description)).perform(closeSoftKeyboard());
        onView(withId(R.id.submitJobButton)).perform(click());
        onView(withId(R.id.errorMessage)).check(matches(withText(R.string.EMPTY_JOB_DESCRIPTION)));
    }
}
