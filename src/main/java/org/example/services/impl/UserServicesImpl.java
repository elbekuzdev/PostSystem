package org.example.services.impl;

import org.example.utils.UserUtils;
import org.example.dao.UserDao;
import org.example.dao.impl.MessageDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.Message;
import org.example.model.User;
import org.example.services.UserServices;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

public class UserServicesImpl implements UserServices {

    private static UserServices userServices = null;

    public static UserServices getUserServices(){
        if (userServices == null){
            userServices = new UserServicesImpl();
        }

        return userServices;
    }
    @Override
    public Message isExistsUser(String username, String password) {
        UserDao userDao = UserDaoImpl.getUserDao();
        Message message = null;
        Optional<User> user = userDao.findByUsernameAndPassword(username, password);
        if (user.isPresent()){
            User user1 = user.get();
            String key = Base64.getEncoder().encodeToString((user1.getUsername() + "&" + user1.getPassword() + "&" + (new Date().getTime())).getBytes());
            System.out.println(new String(Base64.getDecoder().decode(key.getBytes())));
            message = new Message(MessageDao.findByStatusCode(110), key);
        }else{

            message = MessageDao.findByStatusCode(111);
        }
        return message;
    }

    @Override
    public Message appendUser(User user) {
        UserDao userDao = UserDaoImpl.getUserDao();
        Message message = null;
        if (userDao.saveDB(user)){
            String key = Base64.getEncoder().encodeToString((user.getUsername() + "&" + user.getPassword() + "&" + (new Date().getTime())).getBytes());
            message = new Message(MessageDao.findByStatusCode(100), key);
        }else{
            message = MessageDao.findByStatusCode(101);
        }
        return message;
    }


}
