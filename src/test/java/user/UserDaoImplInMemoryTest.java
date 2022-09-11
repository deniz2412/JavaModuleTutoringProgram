package user;

import exceptions.NoUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplInMemoryTest {

    User mockUser = Mockito.mock(User.class);
    User mockUserInMap = Mockito.mock(User.class);
    UserDaoImplInMemory userDao = new UserDaoImplInMemory();

    @BeforeEach
    void BeforeTest() {
        userDao.addOrUpdate(mockUserInMap);

    }

    @Test
    void contains() {

        assertTrue(userDao.contains(mockUserInMap));
        assertFalse(userDao.contains(mockUser));
    }

    @Test
    void getAll() {
        List<User> userList = new ArrayList<>();
        userList.add(mockUserInMap);
        List<User> users = userDao.getAll();
        assertArrayEquals(userList.toArray(), users.toArray());

    }

    @Test
    void getById() {
        assertThrows(NoUserException.class, () -> userDao.getById(1));
        assertEquals(mockUserInMap, userDao.getById(0));
    }

    @Test
    void addOrUpdate() {
        assertTrue(userDao.addOrUpdate(mockUserInMap));

    }

    @Test
    void delete() {
        assertTrue(userDao.delete(mockUserInMap));
        assertThrows(NoUserException.class, () -> userDao.delete(mockUser));

    }

    @Test
    void instanceToTest() {

    }
}