package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserDaoImplInMemory mockDao = Mockito.mock(UserDaoImplInMemory.class);
    User mockUserValid = Mockito.mock(User.class);

    User mockUser = Mockito.mock(User.class);
    UserService userService = new UserService(mockDao);

    @BeforeEach
    void BeforeTest() {
        when(mockUserValid.getPassword()).thenReturn("PSsrw12@");
        when(mockUserValid.getUsername()).thenReturn("Valid User5");
        when(mockUserValid.getEmail()).thenReturn("Valid@Valid.com");

        when(mockUser.getPassword()).thenReturn("12@");
        when(mockUser.getUsername()).thenReturn("!nvalid ");
        when(mockUser.getEmail()).thenReturn("Invalid");
    }

    @Test
    void isValidUserInvalid() {
        assertFalse(userService.isValidUser(mockUser));
        when(mockUser.getEmail()).thenReturn("Valid@Valid.com");
        assertFalse(userService.isValidUser(mockUser));
    }

    @Test
    void login() {
    }
}