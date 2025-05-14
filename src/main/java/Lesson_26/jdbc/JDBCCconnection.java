package Lesson_26.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCCconnection {
    public static final String URL = "jdbc:sqlite:C:/SQLight/DB APR 14.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);

    }

}
