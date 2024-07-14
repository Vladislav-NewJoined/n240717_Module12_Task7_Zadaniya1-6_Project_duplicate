package task12_2_1.zadanye0;

import java.sql.*;

public class Update {
    private Connection connect() {
        // SQLite connection String
        String url = "jdbc:sqlite:C:\\Владислав\\IdeaProjectsDrafts\\Draft_Module12_Task2_Zadaniya1-6_part2\\src\\main\\java\\task12_2_1\\sqlite.dbase";
//        String url = "jdbc:sqlite:sq.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
//        String sql = "select id,name,phone from Users where name like '%Petya%';";
//        String sql = "select * from Users";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE from Users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {


        System.out.println("Результаты запроса по классу Update:");
        Update app = new Update();
        app.selectAll();
        System.out.println("Соединение удалило все данные из таблицы Users");
    }
}
