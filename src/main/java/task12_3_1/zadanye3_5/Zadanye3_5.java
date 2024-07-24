package task12_3_1.zadanye3_5;

import java.sql.*;

// Команда в терминале для создания базы данных MariaDB: docker run --name postgres -d -p 5432:5432 -e POSTGRES_DB=somedbPGtest -e POSTGRES_USER=someuser -e POSTGRES_PASSWORD=123 postgres:alpine
// Значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: MySQL
// Про соединение с базой данных видео здесь:https://youtu.be/_R1hLusMK4c
// Запрос нужно сделать такой:  "select * from Users;" в DB Browser в папке «Consoles → somedb»

public class Zadanye3_5 {

    private static final String URL = "jdbc:postgresql://localhost:5432/somedbPGtest";
    private static final String USER = "someuser";
    private static final String PASSWORD = "123";

    public static void main(String[] args) {
        System.out.println("""
            Задание:
            Модуль 12. Базы данных и Git. Задание №4:
            Задание:
            3. Напишите запрос для вывода всех имен и  Employee ID
            Решение:
            """);

        connect();
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                // Удаление таблицы 'users', если она уже существует
                String dropTableQuery = "DROP TABLE IF EXISTS users";
                statement.executeUpdate(dropTableQuery);



                String createTableQuery = "CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(50))";
                statement.executeUpdate(createTableQuery);
                System.out.println("Таблица 'users' создана успешно.");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try (Statement statement = connection.createStatement()) {
                // Установка начального значения для столбца 'id' в таблице 'users'
                String setInitialValueQuery = "ALTER SEQUENCE users_id_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery);
                System.out.println("Начальное значение столбца 'id' установлено на 100.");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try (Statement statement = connection.createStatement()) {
                String insertDataQuery = "INSERT INTO users (name) VALUES ('Иван'), ('Мария'), ('Петр')";
                statement.executeUpdate(insertDataQuery);
                System.out.println("Данные добавлены успешно.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                while (resultSet.next()) {
                    String columnName = resultSet.getString("name");
                    System.out.println("Name: " + columnName);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
