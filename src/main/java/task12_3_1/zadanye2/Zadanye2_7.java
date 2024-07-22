package task12_3_1.zadanye2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Zadanye2_7 {
    public static void main(String[] args) throws SQLException {

        connect();
    }

    private static void connect() throws SQLException {
        System.out.println("Again, hello World");

        Connection conn;
        Statement stmt = null; // Создаем объект Statement stmt

        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "123");
    }
}
