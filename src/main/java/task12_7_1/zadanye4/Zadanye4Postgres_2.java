package task12_7_1.zadanye4;

import java.sql.*;

// На сервере Docker создан контейнер с базой данных PostgreSQL с именем "postgresTest" при помощи
// команды в терминале Docker Desktop или в терминале среды разработки, например IntelliJ IDEA:
// "docker run --name postgresTest -d -p 5432:5432 -e POSTGRES_DB=somedbPGtest -e POSTGRES_USER=someuser
//  -e POSTGRES_PASSWORD=123 postgres:alpine".
// В приложении DBeaver создано соединение с базой данных с именем "somedbPGtest".
// Для настройки соединения в DBeaver использованы следующие параметры (можно получить в ответ на команду в терминале: docker inspect postgresTest):
// Сервер (Хост): localhost (для соединения не со своего компьютера: 172.17.0.2
// Порт: 5432
// База данных: somedbPGtest
// Пользователь: someuser
// Пароль: 123
// Для проверки настроек можно сделать такой тестовый запрос:  "select * from users" в DB Browser в папке "Consoles -→ somedbPGtest"

public class Zadanye4Postgres_2 {

    private static final String URL = "jdbc:postgresql://localhost:5432/somedbPGtest";
    private static final String USER = "someuser";
    private static final String PASSWORD = "123";

    public static void main(String[] args) throws SQLException {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №7, Проект:\s
                Общая задача создания проекта
                    Создать подключение к реляционной базе данных Postgres и к не реляционной
                    базе MongoDb.
                    Суть функции проекта сводится к сохранению мета-информации каждого запроса
                    в отдельную базу данных без заранее закрепленной структуры, в то время как
                    основная информация будет поступать в реляционную базу Postgres.
                Задание:
                ТЕСТ Задание: Напишите программу на Java, которая соединяет две таблицы "users3" и "users4"
                из базы данных PostgreSQL "somedbPGtest", используя процесс "JOIN". Вам необходимо выполнить
                INNER JOIN по столбцу userid и вывести результат объединения данных.
            
                Параметры соединения к базе данных PostgreSQL "somedbPGtest":
                - Сервер (Хост): localhost
                - Порт: 5432
                - База данных: somedbPGtest
                - Пользователь: someuser
                - Пароль: 123
            
                Таблицы для слияния:
                - Таблица "users3" содержит столбцы userid, username, email и т.д.
                - Таблица "users4" содержит столбцы userid, phone, salary и т.д.
            
                Ваша задача: Написать Java код, который устанавливает соединение с базой данных
                PostgreSQL "somedbPGtest", выполняет INNER JOIN между таблицами "users3" и "users4"
                по столбцу userid и выводит результат объединения данных на экран.

                Решение:
            \s""");

        connect();
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                // Удаление таблицы 'users3', если она уже существует
                String dropTableQuery = "DROP TABLE IF EXISTS users3";
                statement.executeUpdate(dropTableQuery);

                // Создание таблицы 'users3' с другой структурой
                String createTableQuery = "CREATE TABLE users3 (" +
                        "employee_id SERIAL PRIMARY KEY," +
                        "first_name VARCHAR(20) NOT NULL," +
//                        "last_name VARCHAR(20) NOT NULL," +
                        "email VARCHAR(20) NOT NULL," +
//                        "phone_number VARCHAR(20) NOT NULL," +
//                        "hire_date VARCHAR(20) NOT NULL," +
//                        "job_id VARCHAR(20) NOT NULL," +
//                        "salary DECIMAL(10,2) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQuery);
                System.out.println("Таблица 'users3' с новой структурой создана успешно.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Установка начального значения для столбца 'id' в таблице 'users3'
                String setInitialValueQuery = "ALTER SEQUENCE users3_employee_id_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery);
                System.out.println("Начальное значение столбца 'employee_id' установлено на 100.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Новая вставка данных в таблицу 'users3'
                String insertDataQuery = "INSERT INTO users3 (first_name, email) VALUES " +
                        "('Steben', 'SKING'), " +
                        "('Neena', 'NKOCHHAR'), " +
                        "('Valli', 'VPATABAL')";
                statement.executeUpdate(insertDataQuery);
                System.out.println("Данные в таблицу users3 добавлены успешно.\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            try (Statement statement = connection.createStatement()) {
//                // Выполнение запроса на выборку всех имен и Employee ID
//                String selectNamesAndIdsQuery = "SELECT first_name, employee_id FROM users";
//                ResultSet namesAndIdsRs = statement.executeQuery(selectNamesAndIdsQuery);
//
//                while (namesAndIdsRs.next()) {
//                    int employeeId = namesAndIdsRs.getInt("employee_id");
//                    String firstName = namesAndIdsRs.getString("first_name");
//                    System.out.println("Employee ID: " + employeeId + ", First Name: " + firstName);
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }






        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                // Удаление таблицы 'users4', если она уже существует
                String dropTableQuery = "DROP TABLE IF EXISTS users4";
                statement.executeUpdate(dropTableQuery);

                // Создание таблицы 'users4' с другой структурой
                String createTableQuery = "CREATE TABLE users4 (" +
                        "employee_id SERIAL PRIMARY KEY," +
//                        "first_name VARCHAR(20) NOT NULL," +
//                        "last_name VARCHAR(20) NOT NULL," +
//                        "email VARCHAR(20) NOT NULL," +
                        "phone_number VARCHAR(20) NOT NULL," +
//                        "hire_date VARCHAR(20) NOT NULL," +
//                        "job_id VARCHAR(20) NOT NULL," +
                        "salary DECIMAL(10,2) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQuery);
                System.out.println("Таблица 'users4' с новой структурой создана успешно.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Установка начального значения для столбца 'id' в таблице 'users4'
                String setInitialValueQuery = "ALTER SEQUENCE users4_employee_id_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery);
                System.out.println("Начальное значение столбца 'employee_id' установлено на 100.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Новая вставка данных в таблицу 'users4'
                String insertDataQuery = "INSERT INTO users (first_name, phone_number, salary) VALUES " +
                        "('Steben', '515.123.4567', 24000.00), " +
                        "('Neena', '515.123.4568', 17000.00), " +
//                        "('Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1987-06-19', 'AD_VP', 17000.00), " +
//                        "('Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1986-06-20', 'ID_PROG', 9000.00), " +
//                        "('Bruce', 'Ernst', 'BERNST', '590.423.4568', '1986-06-21', 'ID_PROG', 6000.00), " +
//                        "('David', 'Austin', 'DAUSTIN', '590.423.4569', '1986-06-22', 'ID_PROG', 4800.00), " +
                        "('Valli', '590.423.4569', 4800.00)";
                statement.executeUpdate(insertDataQuery);
                System.out.println("Данные в таблицу users4 добавлены успешно.\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }

//            try (Statement statement = connection.createStatement()) {
//                // Выполнение запроса на выборку всех имен и Employee ID
//                String selectNamesAndIdsQuery = "SELECT first_name, employee_id FROM users4";
//                ResultSet namesAndIdsRs = statement.executeQuery(selectNamesAndIdsQuery);
//
//                while (namesAndIdsRs.next()) {
//                    int employeeId = namesAndIdsRs.getInt("employee_id");
//                    String firstName = namesAndIdsRs.getString("first_name");
//                    System.out.println("Employee ID: " + employeeId + ", First Name: " + firstName);
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
