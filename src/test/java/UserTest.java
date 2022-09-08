import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    User userGood = new User("Test", "PSsrw12@", "Test@Test.com");
    User userInvalidUser = new User("Zed", "PSsrw12@", "Test@Test.com");


    @Test
    void isValidUserGood() {
        assertTrue(userGood.isValidUser());
    }

    @Test
    void isValidUserBad() {
        assertFalse(userInvalidUser.isValidUser());
    }
}