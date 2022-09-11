package user;

import exceptions.NoUserException;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;


@NoArgsConstructor
public class UserDaoImplInMemory implements UserDao {
    private final HashMap<Integer, User> users = new HashMap<>();

    @Override
    public boolean contains(User user) {
        return users.containsValue(user);
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().toList();
    }

    @Override
    public User getById(Integer id) throws NoUserException {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new NoUserException("User does not exist");
        }
    }

    @Override
    public boolean addOrUpdate(User user) {
        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean delete(User user) throws NoUserException {
        if (users.containsValue(user)) {
            users.remove(user.getId(), user);
            return true;
        } else {
            throw new NoUserException("User does not exist");
        }
    }
}
