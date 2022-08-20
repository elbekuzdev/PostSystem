package org.example.model;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;

import java.util.Date;

public class Post extends BasicModel{
    private String text;
    private User user;



    public Post(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Post(String text, int userId) {
        UserDao userDao = UserDaoImpl.getUserDao();
        this.text = text;
        this.user = userDao.findById(userId).get();
    }

    public Post(String text, User user, String createdDate) {
        this.text = text;
        this.user = user;
        super.setCreated_time(createdDate);
    }

    public Post(int id, String text, User user, String createdDate) {
        super.setId(id);
        this.text = text;
        this.user = user;
        super.setCreated_time(createdDate);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
