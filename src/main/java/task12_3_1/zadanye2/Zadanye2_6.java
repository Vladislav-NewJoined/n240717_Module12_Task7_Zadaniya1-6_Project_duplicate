package task12_3_1.zadanye2;

import java.sql.*;

public class Zadanye2_6 {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World");

        connect();
    }

    private static void connect() throws SQLException {
        System.out.println("Again, hello World");

        Connection conn;
        Statement stmt = null; // Создаем объект Statement stmt

        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "123");
    }
}
