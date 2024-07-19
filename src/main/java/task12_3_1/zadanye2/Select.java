package task12_3_1.zadanye2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "123");
            System.out.println("Connection to MaMariaDB has been established.");
            conn.createStatement().execute("create table if not exists Users (\n" +
                    "   id int primary key auto_increment,\n" +
                    "   name varchar(20) not null,\n" +
                    "   phone varchar(20) default null\n" +
                    ");");
            System.out.println("Table created");


//            // Удаление всех строк из таблицы Users
//            String deleteDataQuery = "DELETE FROM Users";
//            stmt.execute(deleteDataQuery); // Удаление всех строк из таблицы Users
//
//            // Сброс автоинкрементного значения для таблицы Users, чтобы в новом запросе таблица заполнялась с нуля.
//            String resetAutoIncrementQuery = "DELETE FROM sqlite_sequence WHERE name='Users'";
//            stmt.execute(resetAutoIncrementQuery);


            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (name, phone) values " +
                    "('Petya', '125453'), " +
                    "('Vasya', '654321'), " +
                    "('Katya', '111111'), " +
                    "('Sasha', '222222'), " +
                    "('Pasha', '333333'), " +
                    "('Misha', null);";
            Statement stmt = conn.createStatement();
            stmt.execute(insertDataQuery);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

//    public void selectAll() {
//        String sql = "select id,name,phone from Users where name like '%Petya%'";
//    }

    public static void main(String[] args) {
        connect();
    }
}