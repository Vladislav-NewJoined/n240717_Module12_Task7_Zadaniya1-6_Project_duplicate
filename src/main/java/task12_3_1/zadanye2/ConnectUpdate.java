package task12_3_1.zadanye2;

import java.sql.*;

public class ConnectUpdate {
    private Connection connect() {
        // SQLite connection String
        String url = "jdbc:sqlite:src/main/java/task12_3_1/sqlite.dbase";
//        String url = "jdbc:sqlite:src/main/java/task12_3_1/sqlite.dbase";
        Connection conn2 = null;
        try {
            conn2 = DriverManager.getConnection(url);
            conn2.createStatement().execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT DEFAULT NULL);");
//            conn.createStatement().execute("create table if not exists Users (\n" +
//                     " id integer primary key autoincrement,\n" +
//                     " name varchar(20) not null,\n" +
//                     " phone varchar(20) default null\n" +
//                     ");");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn2;
    }

    public void update() {
        String sql = "update Users set name=? where id=?";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, "Henry");
            stmt.setInt(2,2);
            stmt.executeUpdate();

            System.out.println("Database update updated succesfully ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №3:\s
            Цель задания: знакомство и формирование базовых навыков с по работе  с SQLite\s
                Задание:
                1. Что такое MySQL?
                2. Напишите запрос, чтобы отобразить имя и фамилию всех сотрудников, в именах которых есть «b» и «c»
                   (Пример таблицы  см. таблица 2)
                3. Напишите запрос, чтобы отобразить имя (имя, фамилия) и дату приема на работу для всех сотрудников,
                   которые были приняты на работу в 1987 году. (Пример таблицы  см. таблица 2)
                4. Напишите запрос, чтобы получить максимальную и минимальную зарплату из таблицы сотрудников
                   (Пример таблицы  см. таблица 4)
                5. Напишите запрос, чтобы получить четные записи из таблицы сотрудников  (Пример таблицы
                   см. таблица 2)
                6. Напишите запрос, чтобы получить нечетные записи из таблицы сотрудников  (Пример таблицы
                   см. таблица 2)

                Решение:
            \s""");

        ConnectUpdate app = new ConnectUpdate();
        Connection conn2 = app.connect(); // Подключаемся к базе данных
//        app.connect(); // Первым делом вызываем метод connect() для создания таблицы

        try {
            Statement statement = conn2.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT DEFAULT NULL);");
            System.out.println("Table Users created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }

        app.update(); // Затем вызываем метод update() для обновления данных
    }
}
