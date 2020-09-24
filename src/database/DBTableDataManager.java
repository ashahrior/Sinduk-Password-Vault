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


    public static boolean updateRow(String user, String web_name, String web_add, String mail, String pass, String entryId) {
        if(!entryId.equals("")) {
            Connection conn = getConnection();
            //int id = Integer.parseInt(entryId);
            String query = "update passbook.web_credentials set username='"+user+"',  web_name='"+web_name+"', " +
                    "web_address='"+web_add+"',\n" +
                    " web_user_email='"+mail+"', web_password='"+pass+"' where id="+entryId+";";
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
        return false;
    }


    public static boolean deleteRow(String entryId) {
        if(!entryId.equals("")) {
            Connection conn = getConnection();
            String query = "delete from passbook.web_credentials where id="+entryId+";";
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
        return false;
    }


    public static ArrayList<AccountTableDataModel> getSearchData(String user, String searchKey) {

        ArrayList <AccountTableDataModel> arrayList = new ArrayList<>();

        Connection conn = getConnection();

        String query = "SELECT * FROM passbook.web_credentials " +
                "where username='"+user+"' and " +
                "(web_name like '%"+searchKey+"%' \n" +
                "or web_address like '%"+searchKey+"%' " +
                "or web_user_email like '%"+searchKey+"%');";
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

}
