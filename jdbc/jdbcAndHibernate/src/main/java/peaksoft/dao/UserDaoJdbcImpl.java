package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String query = "create table users(" + "id serial primary key,"
                + "name varchar(50) not null," + "lastName varchar(50) not null,"
                + "age smallint not null);";
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void dropUsersTable() {
        String droptable = "drop table if exists users;";
        try (Connection connection = Util.connect(); Statement stat = connection.createStatement()) {

            stat.executeUpdate(droptable);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void saveUser(String name, String lastName, byte age) {
        String SQL = "insert into users (name, lastName, age) values (?,?,?)";
        try (Connection connection = Util.connect();
                PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void removeUserById(long id) {
        String sql = "delete from users where id = ?;";
        try (Connection connection = Util.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<User> getAllUsers() throws SQLException {
        String SQL = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                list.add(new User( rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Table not found!!!");
            throw new SQLException();
        }

    }


    public void cleanUsersTable() {
        String sql = "truncate table users;";
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}


