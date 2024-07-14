package task12_2_0_part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Select {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:\\Владислав\\IdeaProjectsDrafts\\Draft_Module12_Task2_Zadaniya1-6_part2\\src\\main\\java\\task12_2_0_part2\\sqlite.dbase";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String createTableQuery = "create table if not exists Users (" +
                    "id integer primary key autoincrement, " +
                    "name varchar(20) not null, " +
                    "phone varchar(20) default null);";
            stmt.execute(createTableQuery);

            String insertDataQuery = "insert into Users (name, phone) values " +
                    "('Fedya', '11111111'), " +
                    "('Pasha', '22222222'), " +
                    "('Misha', null);";
            stmt.execute(insertDataQuery);

            System.out.println("Таблица 'Users' создана и данные добавлены успешно.");

        } catch (SQLException e) {
            System.err.println("Ошибка выполнения SQL запроса: " + e.getMessage());
        }
    }
}
