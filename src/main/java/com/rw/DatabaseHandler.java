package com.rw;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
    String connectionString = "jdbc:mysql://"+ dbHost + ":"
            + dbPort + "/" + dbName;
    Class.forName("com.mysql.jdbc.Driver");

    dbConnection = DriverManager.getConnection(connectionString,
            dbUser, dbPass);
    return dbConnection;
    }

    public void signUpUser(String username, String password, int role ){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERNAME + "," + Const.USER_PASSWORD + "," +  Const.USER_ROLE + ")" +
                "VALUES(?,?,?)";



        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, username);
            prSt.setString(2, password);
            prSt.setInt(3, role);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
