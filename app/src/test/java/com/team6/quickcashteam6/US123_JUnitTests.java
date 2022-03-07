package com.team6.quickcashteam6;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class US123_JUnitTests {

    @Test
    public void checkIfEmptyEmail() {
        assertTrue(LoginActivity.isEmptyEmail(""));
        assertFalse(LoginActivity.isEmptyEmail("test@gmail.com"));
    }
    @Test
    public void checkIfEmptyPassword() {
        assertTrue(LoginActivity.isEmptyPassword(""));
        assertFalse(LoginActivity.isEmptyPassword("@Qwerty123"));
    }

    @Test
    public void checkIfEmptyrEmail() {
        assertTrue(RegisterActivity.isEmptyrEmail(""));
        assertFalse(RegisterActivity.isEmptyrEmail("test@gmail.com"));
    }
    @Test
    public void checkIfEmptyrPassword() {
        assertTrue(RegisterActivity.isEmptyrPassword(""));
        assertFalse(RegisterActivity.isEmptyrPassword("@Qwerty123"));
    }

}


