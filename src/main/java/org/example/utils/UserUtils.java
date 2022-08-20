package org.example.utils;

import org.example.model.User;

import java.util.Base64;
import java.util.Date;

public class UserUtils {
    public static String makeKey(User user) {
        String key = Base64.getEncoder().encodeToString((user.getUsername() + "&" + user.getPassword() + "&" + (new Date().getTime())).getBytes());
        return key;
    }

}
