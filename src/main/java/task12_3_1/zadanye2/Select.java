package task12_3_1.zadanye2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    private static Connection connect() {
        Connection conn = null;
        Statement stmt = null; // Создаем объект Statement stmt

        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "123");
            System.out.println("Connection to MariaDB has been established.");
            stmt = conn.createStatement();

            // Удаление таблицы, если она уже существует
            String dropTableQuery = "DROP TABLE IF EXISTS Users";
            stmt.execute(dropTableQuery);

            // Создание таблицы 'Users'
            String createTableQuery = "create table Users (\n" +
                    "   id int primary key auto_increment,\n" +
                    "   name varchar(20) not null,\n" +
                    "   phone varchar(20) default null\n" +
                    ");";
            stmt.execute(createTableQuery);
            System.out.println("Table created");

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (name, phone) values " +
                    "('Petya', '125453'), " +
                    "('Vasya', '654321'), " +
                    "('Katya', '111111'), " +
                    "('Sasha', '222222'), " +
                    "('Pasha', '333333'), " +
                    "('Misha', null);";
            stmt.execute(insertDataQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }

    public static void main(String[] args) {
        connect();
    }
}
