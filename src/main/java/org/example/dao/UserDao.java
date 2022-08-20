package org.example.dao;

import org.example.model.User;

import java.util.Optional;

public interface UserDao extends BasicDao<User>{
    Optional<User> findByUsernameAndPassword(String username, String password);
    boolean deleteByUsername(String password);
}
