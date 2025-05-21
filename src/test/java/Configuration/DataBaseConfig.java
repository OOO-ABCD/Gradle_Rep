package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DataBaseConfig {
        private static final String URL = "jdbc:sqlite:database.db";
        public static final String username = "username";
        public static final String password = "password";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, username, password);
        }

        public static void breakConnection(Connection connection) {
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
