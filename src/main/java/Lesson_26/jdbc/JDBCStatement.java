package Lesson_26.jdbc;

import java.sql.*;

public class JDBCStatement {

    public static void main(String[] args) {

        String countQuery = "SELECT * FROM books WHERE author_id = 2";

        try (Connection connection = JDBCCconnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countQuery)
        ) {
            int count = 0;
            while (resultSet.next()) {
                count++;
            }

            if (count != 2) {
                throw new RuntimeException("Ошибка: Ожидалось 2 строки, получено " + count);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с БД: " + e.getMessage(), e);
        }
    }
}