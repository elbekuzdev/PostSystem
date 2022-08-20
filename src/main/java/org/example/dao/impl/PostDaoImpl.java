package org.example.dao.impl;

import org.example.dao.PostDao;
import org.example.dao.PostgresConnection;
import org.example.dao.UserDao;
import org.example.model.Post;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDaoImpl implements PostDao {

    private static PostDao postDao = null;

    public static PostDao getUserDao() {
        if (postDao == null) {
            postDao = new PostDaoImpl();
        }
        return postDao;
    }

    @Override
    public boolean saveDB(Post post) {
        Connection connection = PostgresConnection.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into post (text, user_id) values (?, ?) returning id");
            statement.setString(1, post.getText());
            statement.setInt(2, post.getUser().getId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Post> getAll() {
        Connection connection = PostgresConnection.getInstance();
        List<Post> posts = new ArrayList<>();
        UserDao userDao = UserDaoImpl.getUserDao();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, text, user_id, created_date from post");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = (int) resultSet.getLong(1);
                String text = resultSet.getString(2);
                int userId = resultSet.getInt(3);
                String createdDate = resultSet.getString(4);
                Optional<User> userOptional = userDao.findById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    posts.add(new Post(id, text, user, createdDate));
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public boolean update(Post post) {
        return false;
    }

    @Override
    public List<Post> findByUserId(Integer userId) {
        return null;
    }
}
