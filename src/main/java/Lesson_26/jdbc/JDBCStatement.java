package Lesson_26.jdbc;

import java.sql.*;

public class JDBCStatement {

    public static void main(String[] args) {

        String countQuery = "SELECT * FROM books WHERE author_id = 2";

        try (Connection connection = JDBCConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countQuery)) {
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

        String query = "SELECT name FROM authors WHERE birth_date > '1900-01-01' AND country = 'США'";

        try (Connection connection = JDBCConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int count = 0;
            String author = null;
            while (resultSet.next()) {
                count++;
                author = resultSet.getString("name");
            }
            if (count == 1) {
                System.out.println("Имя автора: " + author);
            } else {
                throw new RuntimeException("Ошибка: ожидается одно имя, получено " + count);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при работе с БД: " + e.getMessage(), e);
        }
    }
}