package ru.alinka.springboot.Springboot.dao;



import ru.alinka.springboot.Springboot.model.User;

import java.util.List;
public interface UserDao {
    List<User> getUsers();

    User getUserById(int id);

    void saveUser(User user);

    void updateUser(int id, User user);

    void deleteUserById(int id);
}
