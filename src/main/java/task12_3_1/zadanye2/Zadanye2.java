package task12_3_1.zadanye2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

// Вот значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: mariadb-java-client-3.4.1.jar
// Как соединяться через драйвер здесь: https://ya.ru/video/preview/14732760414458014911
// Скачивать драйвер здесь: https://downloads.mariadb.com/Connectors/java/connector-java-3.4.1/

public class Zadanye2 {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №3:\s
            Цель задания: знакомство и формирование базовых навыков с по работе  с MySQL\s
                Задание:
                2. Напишите запрос, чтобы отобразить имя и фамилию всех сотрудников, в именах которых есть «b» и «c»
                   (Пример таблицы  см. таблица 2)

                Решение:
            \s""");

        connect();
    }

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
                    "   employee_id int primary key auto_increment,\n" +
                    "   first_name varchar(20) not null,\n" +
                    "   last_name varchar(20) not null,\n" +
                    "   email varchar(20) not null,\n" +
                    "   phone_number varchar(20) not null,\n" +
                    "   hire_date varchar(20) not null,\n" +
                    "   job_id varchar(20) not null\n" +
                    ");";
            stmt.execute(createTableQuery);
            System.out.println("Table created");

// Установка начального значения для 'employee_id'
            String setInitialValueQuery = "alter table Users auto_increment = 100;";
            stmt.execute(setInitialValueQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (first_name, last_name, email, phone_number, hire_date, job_id) values " +
                    "('Steven', 'King', 'SKING', '515.123.4567', '1987-06-17', 'AD_PRES'), " +
                    "('Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1987-06-18', 'AD_VP'), " +
                    "('Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1987-06-19', 'AD_VP'), " +
                    "('Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1987-06-20', 'ID_PROG'), " +
                    "('Bruce', 'Ernst', 'BERNST', '590.423.4568', '1987-06-21', 'ID_PROG'), " +
                    "('David', 'Austin', 'DAUSTIN', '590.423.4569', '1987-06-22', 'ID_PROG'), " +
                    "('Valli', 'Pataballa', 'VPATABAL', '590.423.4569', '1987-06-23', 'ID_PROG');";
            stmt.execute(insertDataQuery);

            // Запрос на выборку данных
            String selectQuery = "select employee_id, first_name, phone_number from Users where first_name like '%Valli%'";
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Вывод результатов запроса
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String name = rs.getString("first_name");
                String phone = rs.getString("phone_number");
                System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone);
            }

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
        return conn;
    }
}
