package database;

import account_credentials.AccountTableDataModel;

import java.sql.*;
import java.util.ArrayList;


public class DBTableDataManager {

    private static Connection getConnection() {
        Connection conn = null;
        DBConnectionManager dbmn = new DBConnectionManager();
        try {
            conn = dbmn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<AccountTableDataModel> getAllTableData(String user) {

        ArrayList <AccountTableDataModel> arrayList = new ArrayList<>();

        Connection conn = getConnection();

        String query = "SELECT id, web_name, web_address, web_user_email, web_password FROM passbook.web_credentials " +
                "WHERE username='"+user+"' ;";
        try {
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //web_name, web_address, web_user_email, web_password, id
                arrayList.add(
                        new AccountTableDataModel(
                                rs.getString("web_name"),
                                rs.getString("web_address"),
                                rs.getString("web_user_email"),
                                rs.getString("web_password"),
                                rs.getString("id")
                        )
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;
    }

    public static String getRowCount(String user) {

        Connection conn = getConnection();
        String query = "Select count(*) as count from passbook.web_credentials where username='"+user+"';";
        try{
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return rs.getString("count");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static boolean insertRow(String user, String web_name, String web_add, String mail, String pass) {
        Connection conn = getConnection();
        String query = "insert into " +
                "passbook.web_credentials " +
                "(username, web_name, web_address, web_user_email, web_password) \n" +
                "values ('"+user+"', '"+web_name+"', '"+web_add+"', '"+mail+"', '"+pass+"');";
        try{
            assert conn != null;
            //Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
