package com.team6.quickcashteam6;

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
public class EmployeeHomepageEspressoTest {
    @Rule
    public ActivityScenarioRule<EmployeePageActivity> myRule = new ActivityScenarioRule<EmployeePageActivity>(EmployeePageActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfMovedToPostJobPage(){
        onView(withId(R.id.viewJobsButton)).perform(click());
        intended(hasComponent(AllJobsActivity.class.getName()));
    }
}

