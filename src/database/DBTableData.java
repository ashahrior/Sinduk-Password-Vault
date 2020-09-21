package database;

import account_credentials.TableDataModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBTableData {

    public static ArrayList<TableDataModel> getTableData(String user) {

        ArrayList <TableDataModel> arrayList = new ArrayList<>();

        Connection conn = null;
        DBConnectionManager dbmn = new DBConnectionManager();
        try {
            conn = dbmn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query = "SELECT web_name, web_address, web_user_email, web_password FROM passbook.web_credentials WHERE username='"+user+"' ;";
        try {
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //web_name, web_address, web_user_email, web_password
                arrayList.add(
                        new TableDataModel(
                        rs.getString("web_name"),
                        rs.getString("web_address"),
                        rs.getString("web_user_email"),
                        rs.getString("web_password")
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
