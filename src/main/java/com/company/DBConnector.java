package com.company;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class DBConnector {

    public static Connection connect = null;

    public static void connect() {

        // Setup the connection with the DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/LoginPanel?"
                    + "user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(User user) {
        try {
            String sqlInsert = "INSERT INTO tuser (login, password, name, surname)" +
                    " VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = connect.prepareStatement(sqlInsert);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());

            preparedStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static Boolean authenticate(String login, String password) {
        try {
            String sqlSelect = "SELECT password from tuser WHERE login = '" + login + "';";

            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sqlSelect);
            while (rs.next()) {
                if (DigestUtils.md5(password).equals(rs.getString("password"))) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return false;
    }

}
