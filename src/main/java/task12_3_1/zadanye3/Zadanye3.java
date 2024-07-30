package task12_3_1.zadanye3;

import java.sql.*;

// Команда в терминале для создания базы данных PostgreSQL: docker run --name postgresTest -d -p 5432:5432 -e POSTGRES_DB=somedbPGtest -e POSTGRES_USER=someuser -e POSTGRES_PASSWORD=123 postgres:alpine
// Значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect postgresTest
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedbPGtest
// Пользователь: someuser
// Пароль: 123
// Для проверки настроек можно сделать такой тестовый запрос:  "select * from users" в DB Browser в папке "Consoles -→ somedbPGtest"

public class Zadanye3 {

    private static final String URL = "jdbc:postgresql://localhost:5432/somedbPGtest";
    private static final String USER = "someuser";
    private static final String PASSWORD = "123";

    public static void main(String[] args) {
        System.out.println("""
            Задание:
            Модуль 12. Базы данных и Git. Задание №4:
            Задание:
            3. Напишите запрос для вывода всех имен и Employee ID
            Решение (при каждом последующем запуске кода перезагрузите соединение
                    с базой данных somedbPostgres, т.е. нажмите disconnect' и затем
                    'connect' в блоке 'DB Browser' внутри 'IntelliJ IDEA'
                    и обновите папку 'public' внутри базы данных):
            """);

        connect();
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                // Удаление таблицы 'users', если она уже существует
                String dropTableQuery = "DROP TABLE IF EXISTS users";
                statement.executeUpdate(dropTableQuery);

                // Создание таблицы 'users' с другой структурой
                String createTableQuery = "CREATE TABLE users (" +
                        "employee_id SERIAL PRIMARY KEY," +
                        "first_name VARCHAR(20) NOT NULL," +
                        "last_name VARCHAR(20) NOT NULL," +
                        "email VARCHAR(20) NOT NULL," +
                        "phone_number VARCHAR(20) NOT NULL," +
                        "hire_date VARCHAR(20) NOT NULL," +
                        "job_id VARCHAR(20) NOT NULL," +
                        "salary DECIMAL(10,2) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQuery);
                System.out.println("Таблица 'users' с новой структурой создана успешно.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Установка начального значения для столбца 'id' в таблице 'users'
                String setInitialValueQuery = "ALTER SEQUENCE users_employee_id_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery);
                System.out.println("Начальное значение столбца 'id' установлено на 100.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Новая вставка данных в таблицу 'users'
                String insertDataQuery = "INSERT INTO users (first_name, last_name, email, phone_number, hire_date, job_id, salary) VALUES " +
                        "('Steben', 'King', 'SKING', '515.123.4567', '1987-06-17', 'AD_PRES', 24000.00), " +
                        "('Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1987-06-18', 'AD_VP', 17000.00), " +
                        "('Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1987-06-19', 'AD_VP', 17000.00), " +
                        "('Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1986-06-20', 'ID_PROG', 9000.00), " +
                        "('Bruce', 'Ernst', 'BERNST', '590.423.4568', '1986-06-21', 'ID_PROG', 6000.00), " +
                        "('David', 'Austin', 'DAUSTIN', '590.423.4569', '1986-06-22', 'ID_PROG', 4800.00), " +
                        "('Valli', 'Pataballa', 'VPATABAL', '590.423.4569', '1986-06-23', 'ID_PROG', 4800.00)";
                statement.executeUpdate(insertDataQuery);
                System.out.println("Данные добавлены успешно.\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement statement = connection.createStatement()) {
                // Выполнение запроса на выборку всех имен и Employee ID
                String selectNamesAndIdsQuery = "SELECT first_name, employee_id FROM users";
                ResultSet namesAndIdsRs = statement.executeQuery(selectNamesAndIdsQuery);

                while (namesAndIdsRs.next()) {
                    int employeeId = namesAndIdsRs.getInt("employee_id");
                    String firstName = namesAndIdsRs.getString("first_name");
                    System.out.println("Employee ID: " + employeeId + ", First Name: " + firstName);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
