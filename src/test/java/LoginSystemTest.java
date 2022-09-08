import Exceptions.NoPasswordFragmentException;
import Exceptions.NoUserException;
import Exceptions.UserAlreadyinListException;
import Exceptions.WrongPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginSystemTest {

    User mockUser = Mockito.mock(User.class);
    User mockUser2 = Mockito.mock(User.class);
    LoginSystem loginSystem = new LoginSystem();

    @BeforeEach
    void BeforeTest() {
        when(mockUser.isValidUser()).thenReturn(true);
        when(mockUser.getPassword()).thenReturn("Password");
        when(mockUser.getUsername()).thenReturn("Username");
        loginSystem.userList.add(mockUser);
    }

    @Test
    void addToListWhileInListTest() {
        assertThrows(UserAlreadyinListException.class, () -> loginSystem.addToList(mockUser));
    }

    @Test
    void addToListWhileNotInListTest() {
        loginSystem.userList.remove(mockUser);
        assertFalse(loginSystem.userList.contains(mockUser));
        loginSystem.addToList(mockUser);

    }

    @Test
    void loginGoodTest() {

        assertEquals(mockUser, loginSystem.login(mockUser.getUsername(), mockUser.getPassword()));
    }

    @Test
    void loginFailedTest() {
        assertNull(loginSystem.login(mockUser.getUsername(), "Wrong"));

    }

    @Test
    void loginUserNotInList() {
        assertThrows(NoUserException.class, () -> loginSystem.login("Fail", "Big Fail"));
    }

    @Test
    void resetPasswordGood() {
        assertEquals("Password set to new password", loginSystem.resetPassword(mockUser, "Pass", "NewPassword"));

    }

    @Test
    void resetPasswordWrongPassword() {
        assertThrows(NoPasswordFragmentException.class, () -> loginSystem.resetPassword(mockUser, "Wrong", "PasswordFragment"));

    }

    @Test
    void resetPasswordNoUser() {
        assertThrows(NoUserException.class, () -> loginSystem.resetPassword(mockUser2, "Pass", "PasswordFragment"));
    }

    @Test
    void deleteFromListDeletedTest() {

        assertTrue(loginSystem.userList.contains(mockUser));
        assertDoesNotThrow(() -> loginSystem.deleteFromList(mockUser, "Password"));
        assertFalse(loginSystem.userList.contains(mockUser));


    }

    @Test
    void deleteFromListWrongPasswordTest() {
        assertThrows(WrongPasswordException.class, () -> loginSystem.deleteFromList(mockUser, "WrongPassword"));
    }

    @Test
    void deleteFromListNoUserTest() {
        assertThrows(NoUserException.class, () -> loginSystem.deleteFromList(mockUser2, "Password"));

    }
}