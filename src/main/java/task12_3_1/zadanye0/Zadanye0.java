package task12_3_1.zadanye0;

import java.sql.*;

// Команда в терминале для создания базы данных MMariaDB: docker run --name mysql -d -p 3306:3306 -e MYSQL_DATABASE=somedb -e MYSQL_USER=someuser -e MYSQL_PASSWORD=123 yobasystems/alpine-mariadb
// Значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: MySQL

public class Zadanye0 {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №4:\s
                 1. Что такое PostgreSQL?
                 2. Что такое партиционирование?
                 3. Напишите запрос для вывода всех имен и  Employee ID
                 4. Напишите запрос для вывода фамилии и даты рождения
                 5. Напишите запрос для вывода имени и фамилии и Employee  ID  в порядке убывания номера Employee ID



                Решение:
            \s""");

        String url = "jdbc:sqlite:src/main/java/task12_3_1/sqlite.dbase";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Создание таблицы 'Users'
            String createTableQuery = "create table if not exists Users (" +
                    "id integer primary key autoincrement, " +
                    "first_name varchar(20) not null, " +
                    "last_name varchar(20) not null);";
            stmt.execute(createTableQuery);

            // Удаление всех строк из таблицы Users
            String deleteDataQuery = "DELETE FROM Users";
            stmt.execute(deleteDataQuery); // Удаление всех строк из таблицы Users

            // Сброс автоинкрементного значения для таблицы Users, чтобы в новом запросе таблица заполнялась с нуля.
            String resetAutoIncrementQuery = "DELETE FROM sqlite_sequence WHERE name='Users'";
            stmt.execute(resetAutoIncrementQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (first_name, last_name) values " +
                    "('Petya', 'Ivanov'), " +
                    "('Vasya', 'Petrov'), " +
                    "('Katya', 'Sidorova'), " +
                    "('Sasha', 'Chernov'), " +
                    "('Pasha', 'Belov'), " +
                    "('Misha', 'Smirnov');";
//            System.out.println(insertDataQuery);
            stmt.execute(insertDataQuery);

            // Запрос на выборку данных
            String selectQuery = "select id, first_name, last_name from Users";
//            // Запрос на выборку данных
//            String selectQuery = "select id, SUBSTR(first_name, 1, 3) as first_name_substr, last_name from Users";
            //            String selectQuery = "select id, first_name, last_name from Users where first_name like '%Petya%'";
            System.out.println(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Вывод результатов запроса
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                System.out.println("ID: " + id + ", Имя: " + first_name + ", Фамилия: " + last_name);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка выполнения SQL запроса: " + e.getMessage());
        }
    }
}
