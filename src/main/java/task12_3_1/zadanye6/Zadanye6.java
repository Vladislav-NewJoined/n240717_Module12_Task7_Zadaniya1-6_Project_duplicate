package task12_3_1.zadanye6;

import java.sql.*;

public class Zadanye6 {
    public static void main(String[] args) throws SQLException {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №3:\s
            Цель задания: знакомство и формирование базовых навыков с по работе  с MySQL\s
                Задание:
                6. Напишите запрос, чтобы получить нечетные записи из таблицы сотрудников  (Пример таблицы
                   см. таблица 2)

                Решение:
            \s""");

        connect();
    }

    private static void connect() throws SQLException {

        Connection conn;
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
                    "   employee_id int primary key auto_increment,\n" +
                    "   first_name varchar(20) not null,\n" +
                    "   last_name varchar(20) not null,\n" +
                    "   email varchar(20) not null,\n" +
                    "   phone_number varchar(20) not null,\n" +
                    "   hire_date varchar(20) not null,\n" +
                    "   job_id varchar(20) not null\n" +
                    ");";
            stmt.execute(createTableQuery);
            System.out.println("Table created\n");

// Установка начального значения для 'employee_id'
            String setInitialValueQuery = "alter table Users auto_increment = 100;";
            stmt.execute(setInitialValueQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (first_name, last_name, email, phone_number, hire_date, job_id) values " +
                    "('Steben', 'King', 'SKING', '515.123.4567', '1987-06-17', 'AD_PRES'), " +
                    "('Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1987-06-18', 'AD_VP'), " +
                    "('Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1987-06-19', 'AD_VP'), " +
                    "('Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1986-06-20', 'ID_PROG'), " +
                    "('Bruce', 'Ernst', 'BERNST', '590.423.4568', '1986-06-21', 'ID_PROG'), " +
                    "('David', 'Austin', 'DAUSTIN', '590.423.4569', '1986-06-22', 'ID_PROG'), " +
                    "('Valli', 'Pataballa', 'VPATABAL', '590.423.4569', '1986-06-23', 'ID_PROG');";
            stmt.execute(insertDataQuery);

// Запрос для выборки четных записей
            String selectOddQuery = "SELECT employee_id, first_name, last_name, hire_date FROM Users WHERE MOD(employee_id, 2) = 1";
            ResultSet rs = stmt.executeQuery(selectOddQuery);

// Вывод результатов запроса для четных записей
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String hireDate = rs.getString("hire_date");
                System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName + ", Hire Date: " + hireDate);
            }

            rs.close();

            System.out.println("Выборка данных выполнена успешно.");

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
    }
}
