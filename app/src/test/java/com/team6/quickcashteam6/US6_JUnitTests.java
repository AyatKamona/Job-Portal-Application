package com.team6.quickcashteam6;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class US6_JUnitTests {
    static PostJobActivity postJobActivity;

    @BeforeClass
    public static void setup() {

        postJobActivity = new PostJobActivity();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    /**
     * checks if job title field is empty
     */
    public void checkIfEmptyJobTitle() {
        assertTrue(postJobActivity.isEmptyJobTitle(""));
        assertFalse(postJobActivity.isEmptyJobTitle("water plants"));
    }

    @Test
    /**
     * checks if payment field is empty
     */
    public void checkIfEmptyPayment() {
        assertTrue(postJobActivity.isEmptyPayment(""));
        assertFalse(postJobActivity.isEmptyPayment("$20/hr"));
    }

    @Test
    /**
     * checks if start time field is empty
     */
    public void checkIfEmptyStartTime() {
        assertTrue(postJobActivity.isEmptyStartTime(""));
        assertFalse(postJobActivity.isEmptyStartTime("2022-02-14 at 8:00am"));
    }

}
