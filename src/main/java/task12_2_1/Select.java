package task12_2_1;

import java.sql.*;

public class Select {
    private Connection connect() {
        // SQLite connection String
        String url = "jdbc:sqlite:some.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
