package org.example.services.impl;

import org.example.dao.PostDao;
import org.example.dao.impl.MessageDao;
import org.example.dao.impl.PostDaoImpl;
import org.example.model.Message;
import org.example.model.Post;
import org.example.services.PostServices;

import java.util.List;

public class PostServicesImpl implements PostServices {

    private static PostServices postServices = null;

    public static PostServices getUserServices() {
        if (postServices == null) {
            postServices = new PostServicesImpl();
        }
        return postServices;
    }


    @Override
    public Message getPost() {
        PostDao postDao = PostDaoImpl.getUserDao();
        List<Post> posts = postDao.getAll();
        Message message = null;
        if (posts.size() > 0) {
            message = new Message(MessageDao.findByStatusCode(110), posts);
        } else {
            message = MessageDao.findByStatusCode(111);
        }
        return message;
    }

    @Override
    public Message appendPost(Post post) {
        PostDao postDao = PostDaoImpl.getUserDao();
        Message message = null;
         if (postDao.saveDB(post)){
             message = MessageDao.findByStatusCode(100);
         }else{
             message = MessageDao.findByStatusCode(101);
         }
         return message;
    }
}
