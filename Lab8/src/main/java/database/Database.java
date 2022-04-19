package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;

public class Database {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";
    private static Connection connection = null;

    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection() {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void runScript(){
        ScriptRunner sr = new ScriptRunner(getConnection());
        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader("create.sql"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sr.runScript(reader);
    }

}