package task12_3_1.zadanye2;

//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Вот значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: mariadb-java-client-3.4.1.jar
// Как соединяться через драйвер здесь: https://ya.ru/video/preview/14732760414458014911
// Скачивать драйвер здесь: https://downloads.mariadb.com/Connectors/java/connector-java-3.4.1/

public class Zadanye2X {

    public static void connect() {
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/somedb", "someuser", "123");

            System.out.println("Connection to MaMariaDB has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

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

        connect(); // Подключаемся к базе данных
    }
}
