package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util util = new Util();
    ResultSet resultSet;

    public void createUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USER (id INT NOT NULL AUTO_INCREMENT, NAME VARCHAR (40), " +
                    "LASTNAME VARCHAR (40), AGE INT, PRIMARY KEY (id))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void dropUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = util.getConnection().prepareStatement("INSERT INTO USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM USER WHERE ID = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new LinkedList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM USER");

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate("TRUNCATE TABLE USER");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
