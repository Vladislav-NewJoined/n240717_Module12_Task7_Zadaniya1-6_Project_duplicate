package zadanye0_part3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:\\Владислав\\IdeaProjectsDrafts\\Draft_Module12_Task2_Zadaniya1-6_part2\\src\\main\\java\\task12_2_1\\sqlite.dbase";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

//            // Создание таблицы 'Users'
//            String createTableQuery = "create table if not exists Users (" +
//                    "id integer primary key autoincrement, " +
//                    "name varchar(20) not null, " +
//                    "phone varchar(20) default null);";
//            stmt.execute(createTableQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (name, phone) values " +
                    "('Petya', '125453'), " +
                    "('Vasya', '654321'), " +
                    "('Katya', null);";
            stmt.execute(insertDataQuery);

            // Запрос на выборку данных
            String selectQuery = "select id, name, phone from Users where name like '%Petya%'";
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Вывод результатов запроса
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone);
            }

            System.out.println("Выборка данных выполнена успешно.");

        } catch (SQLException e) {
            System.err.println("Ошибка выполнения SQL запроса: " + e.getMessage());
        }
    }
}
