package user;

import exceptions.NoUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserDaoImplInMemory mockDao = Mockito.mock(UserDaoImplInMemory.class);
    User mockUserValid = Mockito.mock(User.class);
    User legitUser = new User(3, "", "", "");
    User mockUser = Mockito.mock(User.class);

    List<User> mockList = List.of(new User[]{mockUser, mockUserValid});
    UserService userService = new UserService(mockDao);

    @BeforeEach
    void BeforeTest() {
        when(mockUserValid.getPassword()).thenReturn("PSsrw12@");
        when(mockUserValid.getUsername()).thenReturn("Valid User5");
        when(mockUserValid.getEmail()).thenReturn("Valid@Valid.com");
        when(mockDao.getAll()).thenReturn(mockList);
        when(mockUser.getPassword()).thenReturn("12@");
        when(mockUser.getUsername()).thenReturn("!nvalid ");
        when(mockUser.getEmail()).thenReturn("Invalid");
    }

    @Test
    void isValidUserInvalid() {
        assertFalse(userService.isValidUser(mockUser));
        legitUser.setEmail("Test@valid.com");
        assertFalse(userService.isValidUser(legitUser));
        legitUser.setPassword("PSsrw12@");
        assertFalse(userService.isValidUser(legitUser));


    }

    @Test
    void isValidUserValid() {
        assertTrue(userService.isValidUser(mockUserValid));

    }

    @Test
    void login() {
        assertThrows(NoUserException.class, () -> userService.login("", ""));
        assertTrue(userService.login("Valid User5", "PSsrw12@"));
        assertFalse(userService.login("Valid User5", "!nvalid"));
    }
}