package org.example.dao.impl;

import org.example.dao.PostgresConnection;
import org.example.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class MessageDao {
    public static Message findByStatusCode(int statusCode){
        Connection connection = PostgresConnection.getInstance();
        Message message = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select status_code, definition from message where status_code = ?");
            statement.setInt(1, statusCode);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String definition = resultSet.getString(2);
                message = new Message(definition, statusCode);
            }else{
                message = new Message("status code not found", 505);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return message;

    }

}
