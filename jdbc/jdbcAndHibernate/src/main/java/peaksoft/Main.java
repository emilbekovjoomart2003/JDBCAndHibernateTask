package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.createUsersTable();
        userDaoJdbc.saveUser("Argen","Bildrer", (byte) 12);
        System.out.println(userDaoJdbc.getAllUsers());
    }
}
