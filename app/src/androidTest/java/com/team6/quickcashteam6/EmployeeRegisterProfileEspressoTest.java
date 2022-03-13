package com.team6.quickcashteam6;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EmployeeRegisterProfileEspressoTest {

    @Rule
    public ActivityScenarioRule<EmployeeRegisterProfileActivity> myRule = new ActivityScenarioRule<EmployeeRegisterProfileActivity>(EmployeeRegisterProfileActivity.class);
    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfMoved2LoginPageAfterProfile() {
        onView(withId(R.id.insert_age)).perform(typeText("20"));
        onView(withId(R.id.male)).perform(click());
        onView(withId(R.id.insert_mobile)).perform(typeText("9020000000"));
        onView(withId(R.id.register_employee_profile)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_employee_profile)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
    }


}
