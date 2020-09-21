package database;

import java.sql.*;
import java.sql.DriverManager;

public class DBConnectionManager extends DBCredentials{
    Connection dbconnection;

    public Connection getConnection() {
        String connection_string = "jdbc:mysql://"+ DBCredentials.dbhost +":" +DBCredentials.dbport+"/"+DBCredentials.dbname+"?autoReconnect=true&useSSL=false";

        try {
            Class.forName(DBCredentials.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dbconnection = DriverManager.getConnection(connection_string,DBCredentials.dbuser, DBCredentials.dbpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbconnection;
    }
}