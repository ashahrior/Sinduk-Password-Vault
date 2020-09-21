package database;

import account_credentials.Accounts;

import java.sql.*;


public class DBAccountLogin {

    private static Accounts executeSearch(String query) {
        Connection conn = null;
        DBConnectionManager dbmn = new DBConnectionManager();
        String name="", email="", pass="";
        Accounts accounts = null;
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
                        rs.getString("id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("email_address") + "\t" +
                        rs.getString("password")
                );
                name = rs.getString("username");
                email = rs.getString("email_address");
                pass = rs.getString("password");
                accounts = new Accounts(name,email,pass);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    public static Accounts checkByUser(String username) {
        String query = "SELECT * FROM passbook.user_table WHERE username='"+username+"' ;";
        return executeSearch(query);
    }

    public static Accounts checkByEmail(String email) {
        String query = "SELECT * FROM passbook.user_table WHERE email_address='"+email+"' ;";
        return executeSearch(query);
    }
}
