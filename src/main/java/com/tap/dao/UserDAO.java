package com.tap.dao;

import com.tap.model.User;

public interface UserDAO {
    void addUser(User user);
    User getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);
}
