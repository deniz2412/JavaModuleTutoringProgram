package user;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(Integer id);

    boolean contains(User user);

    boolean addOrUpdate(User user) throws Exception;

    boolean delete(User user) throws Exception;
}
