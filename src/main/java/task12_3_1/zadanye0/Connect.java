package task12_3_1.zadanye0;

//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: MySQL

public class Connect {

//    public static void connect() {
//        Connection conn = null;
//        try {
//            // create a connection to the database
//            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "123");
//
//            System.out.println("Connection to MaMariaDB has been established.");
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        System.out.println("""
//            Задание:\s
//            Модуль 12. Базы данных и Git. Задание №3:\s
//            Цель задания: знакомство и формирование базовых навыков с по работе  с SQLite\s
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
//
//
//        connect(); // Подключаемся к базе данных
//    }
}
