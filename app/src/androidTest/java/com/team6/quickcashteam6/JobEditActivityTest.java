package com.team6.quickcashteam6;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
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

public class JobEditActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> myRule = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void editJob() throws InterruptedException {
        onView(withId(R.id.lEmail)).perform(typeText("zxcv@dal.ca"));
        onView(withId(R.id.lPassword)).perform(typeText("zxcv"));
        onView(withId(R.id.buttonLogin)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.updateProfile)).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.viewJobButton)).perform(click());

        onView(withId(R.id.insert_job_title)).perform(replaceText("Updated Test"));
    }

    @Test
    public void editProfile() throws InterruptedException {
        onView(withId(R.id.lEmail)).perform(typeText("zxcv@dal.ca"));
        onView(withId(R.id.lPassword)).perform(typeText("zxcv"));
        onView(withId(R.id.buttonLogin)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.updateProfile)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.insert_name)).perform(replaceText("asdf"));
        onView(withId(R.id.insert_Age)).perform(replaceText("47"));
        onView(withId(R.id.insert_Phone)).perform(replaceText("9021234567"));
        

    }
}
