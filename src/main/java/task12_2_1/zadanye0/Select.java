package task12_2_1.zadanye0;

import java.sql.*;

public class Select {
    private Connection connect() {
        // SQLite connection String
        String url = "jdbc:sqlite:C:\\Владислав\\IdeaProjectsDrafts\\Draft_Module12_Task2_Zadaniya1-6_part2\\src\\main\\java\\task12_2_1\\sqlite.dbase";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "select id,name,phone from Users where name like '%Petya%';";
//        String sql = "select * from Users";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        System.out.println("Результаты запроса по классу Select:");
        Select app = new Select();
        app.selectAll();
    }
}
