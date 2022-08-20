package org.example.services;

import org.example.model.Message;
import org.example.model.User;

public interface UserServices {
        Message isExistsUser(String username, String password);
        Message appendUser(User user);
}
