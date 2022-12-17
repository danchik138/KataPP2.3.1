package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long id);
    List<User> getAllUsers();

    void addUser(User user);

    void deleteAllUsers();

    void updateUser(User user);
}
