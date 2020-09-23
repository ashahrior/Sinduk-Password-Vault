package database;

import account_credentials.Account;

import java.sql.*;


public class DBAccountAccessor {

    private static Account executeSearch(String query) {
        Connection conn = null;
        DBConnectionManager dbmn = new DBConnectionManager();
        String name="", email="", pass="";
        Account account = null;
        try {
            conn = dbmn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(
                        rs.getString("username") + "\t" +
                        rs.getString("email_address") + "\t" +
                        rs.getString("password")
                );
                name = rs.getString("username");
                email = rs.getString("email_address");
                pass = rs.getString("password");
                account = new Account(name,email,pass);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    public static Account checkByUser(String username) {
        String query = "SELECT * FROM passbook.user_table WHERE username='"+username+"' ;";
        return executeSearch(query);
    }

    public static Account checkByEmail(String email) {
        String query = "SELECT * FROM passbook.user_table WHERE email_address='"+email+"' ;";
        return executeSearch(query);
    }
}
