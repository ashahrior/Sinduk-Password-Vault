package database;

import account_credentials.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccountCreator {
    private final String username;
    private final String email_address;
    private final String password;

    public DBAccountCreator(Account account) {
        this.username = account.getUsername();
        this.email_address = account.getEmail_address();
        this.password = account.getPassword();
    }

    public boolean insert_account() {
        Connection connection;
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        PreparedStatement preparedStatement;

        String query = "INSERT INTO passbook.user_table (username, email_address, password) VALUES " +
                "(?,?,?);";
        try {
            connection = dbConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        try{
            preparedStatement.setString(1,this.username);
            preparedStatement.setString(2,this.email_address);
            preparedStatement.setString(3,this.password);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
