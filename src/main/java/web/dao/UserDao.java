package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteAllUsers();

    void updateUser(User user);

    User getUserById(long id);
}
