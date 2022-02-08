package com.team6.quickcashteam6;

import junit.framework.TestCase;
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

    /**
     * checks if job title field is empty
     */
    @Test
    public void checkIfEmptyJobTitle() {
        assertTrue(PostJobActivity.isEmptyJobTitle(""));
        assertFalse(PostJobActivity.isEmptyJobTitle("water plants"));
    }

    /**
     * checks if payment field is empty
     */
    @Test
    public void checkIfEmptyPayment() {
        assertTrue(PostJobActivity.isEmptyPayment(""));
        assertFalse(PostJobActivity.isEmptyPayment("$20/hr"));
    }

    /**
     * checks if start time field is empty
     */
    @Test
    public void checkIfEmptyStartTime() {
        assertTrue(PostJobActivity.isEmptyStartTime(""));
        assertFalse(PostJobActivity.isEmptyStartTime("2022-02-14 at 8:00am"));
    }

    /**
     * checks if skill field is empty
     */
    @Test
    public void checkIfEmptySkills() {
        assertTrue(PostJobActivity.isEmptySkills(""));
        assertFalse(PostJobActivity.isEmptySkills("strong"));
    }

    /**
     * checks if description field is empty
     */
    @Test
    public void checkIfEmptyDescription() {
        assertTrue(PostJobActivity.isEmptyDescription(""));
        assertFalse(PostJobActivity.isEmptyDescription("you have to shovel the driveway"));
    }

}
