package org.example.dao.impl;

import org.example.dao.PostgresConnection;
import org.example.dao.UserDao;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private static UserDao userDao = null;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    @Override
    public boolean saveDB(User user) {
        Connection connection = PostgresConnection.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into users (firstname, lastname, username, password) values (?, ?, ?, ?) returning id");
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getUsername());
            statement.setString(4, Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        Connection connection = PostgresConnection.getInstance();
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("select id, firstname, lastname, username from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String username = resultSet.getString(4);
                users.add(new User(id, firstname, lastname, username));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Optional<User> findById(Integer id) {
        Connection connection = PostgresConnection.getInstance();
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement statement = connection.prepareStatement("select id, firstname, lastname, username from users where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                String username = resultSet.getString(4);
                user = Optional.of(new User(id, firstname, lastname, username));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteById(Integer id) {
        Connection connection = PostgresConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByUsername(String password) {
        Connection connection = PostgresConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where username = ?");
            preparedStatement.setString(1, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        Connection connection = PostgresConnection.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstname = ?, lastname = ?, username = ?, password = ? WHERE id = ?");
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getUsername());
            statement.setString(4, Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        Connection connection = PostgresConnection.getInstance();
        Optional<User> user = Optional.empty();

        try {
            PreparedStatement statement = connection.prepareStatement("select id, firstname, lastname from users where password = ? and username = ?");
            statement.setString(2, username);
            statement.setString(1, Base64.getEncoder().encodeToString(password.getBytes()));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                user = Optional.of(new User(id, firstname, lastname, username, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


}
