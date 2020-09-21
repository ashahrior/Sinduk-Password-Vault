package database;

import java.sql.*;

public class test {

    static void getTable(Connection conn) {
        String query = "select * from passbook.user_table;";
        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)) {
            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getString("id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("email_address") + "\t" +
                        rs.getString("password"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void insert_data(Connection conn, String name, String mail, String password) {
        String sql = "INSERT INTO passbook.user_table (username,email_address,password) "
                + "VALUES (?,?,?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, name);
            pstmt.setString(2, mail);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static void getByUser(Connection conn, String name) {
        //String query = "SELECT * FROM passbook.user_table WHERE username = '"+name+"' ;";
        //String query = "SELECT * FROM passbook.user_table WHERE email_address='"+name+"' ;";
        String query = "SELECT web_name, web_address, web_user_email, web_password FROM passbook.web_credentials " +
                "WHERE username='"+name+"' ;";
        try {
            //PreparedStatement pstmt  = conn.prepareStatement(query);
            Statement stmt = conn.createStatement();
            //pstmt.setString(1, name);
            //ResultSet rs = pstmt.executeQuery();
            ResultSet rs = stmt.executeQuery(query);
            // loop through the result set
            while (rs.next()) {
                /*System.out.println(
                        rs.getString("id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("email_address") + "\t" +
                        rs.getString("password")
                );*/
                System.out.println(rs.getString("web_name")+
                        rs.getString("web_address")+
                        rs.getString("web_user_email")+
                        rs.getString("web_password"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        DBConnectionManager dbmn = new DBConnectionManager();
        try {
            conn = dbmn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //insert_data("ashef","ashef123@gmail.com","ce123");
        //getTable();

        getByUser(conn,"ashahrior_007");

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
