package task12_2_1.zadanye2;

import java.sql.*;

public class Zadanye2 {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №2:\s
            Цель задания: знакомство и формирование базовых навыков с по работе  с SQLite\s
                Задание:
                2. Напишите запрос для отображения имен (first_name, last_name), используя псевдонимы «Имя»,
                   «Фамилия».  (Пример таблицы  см. таблица 1)
            
                Решение:
            \s""");

        String url = "jdbc:sqlite:C:\\Владислав\\IdeaProjectsDrafts\\Draft_Module12_Task2_Zadaniya1-6_part3\\src\\main\\java\\task12_2_1\\sqlite.dbase";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Создание таблицы 'Users'
            String createTableQuery = "create table if not exists Users (" +
                    "id integer primary key autoincrement, " +
                    "first_name varchar(20) not null, " +
                    "last_name varchar(20) not null);";
            stmt.execute(createTableQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (first_name, last_name) values " +
                    "('Petya', 'Ivanov'), " +
                    "('Vasya', 'Petrov'), " +
                    "('Katya', 'Sidorova'), " +
                    "('Sasha', 'Chernov'), " +
                    "('Pasha', 'Belov'), " +
                    "('Misha', 'Smirnov');";
            stmt.execute(insertDataQuery);

            // Запрос на выборку данных
            String selectQuery = "select id, first_name, last_name from Users where name like '%Petya%'";
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Вывод результатов запроса
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                System.out.println("ID: " + id + ", First name: " + first_name + ", Last name: " + last_name);
            }

            System.out.println("Выборка данных выполнена успешно.");

        } catch (SQLException e) {
            System.err.println("Ошибка выполнения SQL запроса: " + e.getMessage());
        }
    }
}