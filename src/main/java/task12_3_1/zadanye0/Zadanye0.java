package task12_3_1.zadanye0;

import java.sql.*;

// Значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: MySQL

public class Zadanye0 {
//    public static void main(String[] args) {
//        System.out.println("""
//            Задание:\s
//            Модуль 12. Базы данных и Git. Задание №3:\s
//            Цель задания: знакомство и формирование базовых навыков с по работе  с MySQL\s
//                Задание:
//                1. Что такое MySQL?
//                2. Напишите запрос, чтобы отобразить имя и фамилию всех сотрудников, в именах которых есть «b» и «c»
//                   (Пример таблицы  см. таблица 2)
//                3. Напишите запрос, чтобы отобразить имя (имя, фамилия) и дату приема на работу для всех сотрудников,
//                   которые были приняты на работу в 1987 году. (Пример таблицы  см. таблица 2)
//                4. Напишите запрос, чтобы получить максимальную и минимальную зарплату из таблицы сотрудников
//                   (Пример таблицы  см. таблица 4)
//                5. Напишите запрос, чтобы получить четные записи из таблицы сотрудников  (Пример таблицы
//                   см. таблица 2)
//                6. Напишите запрос, чтобы получить нечетные записи из таблицы сотрудников  (Пример таблицы
//                   см. таблица 2)
//
//                Решение:
//            \s""");
//
//        String url = "jdbc:sqlite:src/main/java/task12_3_1/sqlite.dbase";
//
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement()) {
//
//            // Создание таблицы 'Users'
//            String createTableQuery = "create table if not exists Users (" +
//                    "id integer primary key autoincrement, " +
//                    "first_name varchar(20) not null, " +
//                    "last_name varchar(20) not null);";
//            stmt.execute(createTableQuery);
//
//            // Удаление всех строк из таблицы Users
//            String deleteDataQuery = "DELETE FROM Users";
//            stmt.execute(deleteDataQuery); // Удаление всех строк из таблицы Users
//
//            // Сброс автоинкрементного значения для таблицы Users, чтобы в новом запросе таблица заполнялась с нуля.
//            String resetAutoIncrementQuery = "DELETE FROM sqlite_sequence WHERE name='Users'";
//            stmt.execute(resetAutoIncrementQuery);
//
//            // Вставка данных в таблицу 'Users'
//            String insertDataQuery = "insert into Users (first_name, last_name) values " +
//                    "('Petya', 'Ivanov'), " +
//                    "('Vasya', 'Petrov'), " +
//                    "('Katya', 'Sidorova'), " +
//                    "('Sasha', 'Chernov'), " +
//                    "('Pasha', 'Belov'), " +
//                    "('Misha', 'Smirnov');";
////            System.out.println(insertDataQuery);
//            stmt.execute(insertDataQuery);
//
//            // Запрос на выборку данных
//            String selectQuery = "select id, first_name, last_name from Users";
////            // Запрос на выборку данных
////            String selectQuery = "select id, SUBSTR(first_name, 1, 3) as first_name_substr, last_name from Users";
//            //            String selectQuery = "select id, first_name, last_name from Users where first_name like '%Petya%'";
//            System.out.println(selectQuery);
//            ResultSet rs = stmt.executeQuery(selectQuery);
//
//            // Вывод результатов запроса
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String first_name = rs.getString("first_name");
//                String last_name = rs.getString("last_name");
//                System.out.println("ID: " + id + ", Имя: " + first_name + ", Фамилия: " + last_name);
//            }
//        } catch (SQLException e) {
//            System.err.println("Ошибка выполнения SQL запроса: " + e.getMessage());
//        }
//    }
}
