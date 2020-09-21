package database;

import account_credentials.Accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccountInsertion {
    private final String username;
    private final String email_address;
    private final String password;

    public DBAccountInsertion(Accounts accounts) {
        this.username = accounts.getUsername();
        this.email_address = accounts.getEmail_address();
        this.password = accounts.getPassword();
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
