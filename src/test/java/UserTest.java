import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    User userGood = new User("Test", "PSsrw12@", "Test@Test.com");
    User userInvalidUser = new User("Zed", "PSsrw12@", "Test@Test.com");
    User userInvalidEM = new User("Test", "PSsrw12@", "TestTest");
    User userInvalidPW = new User("Test", "PSsrw1ASdasd3@", "Test@Test.com");


    @Test
    void isValidUserGood() {
        assertTrue(userGood.isValidUser());
    }

    @Test
    void isValidUserBad() {
        assertFalse(userInvalidUser.isValidUser());
    }

    @Test
    void isValidUserPW() {
        assertFalse(userInvalidPW.isValidUser());
    }

    @Test
    void isValidUsedEM() {
        assertFalse(userInvalidEM.isValidUser());
    }

}