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

public class Zadanye4Postgres_3 {

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
        connect2();
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                // Удаление таблицы 'users4', если она уже существует
                String dropTableQueryUsers3 = "DROP TABLE IF EXISTS users3";
                statement.executeUpdate(dropTableQueryUsers3);

// Создание таблицы 'users3' с другой структурой
                String createTableQueryUsers3 = "CREATE TABLE users3 (" +
                        "employeeId SERIAL PRIMARY KEY," +
                        "firstName VARCHAR(20) NOT NULL," +
                        "email VARCHAR(20) NOT NULL," +
                        "jobId VARCHAR(20) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQueryUsers3);
                System.out.println("Таблица 'users3' с новой структурой создана успешно.");

                String setInitialValueQuery1 = "ALTER SEQUENCE users3_employeeId_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery1);

// Вставка данных в таблицу 'users3'
                String insertDataQueryUsers3 = "INSERT INTO users3 (firstName, email, jobId) VALUES " +
                        "('Steben', 'SKING', 'AD_PRES'), " +
                        "('Neena', 'NKOCHHAR', 'AD_VP'), " +
                        "('Valli', 'VPATABAL', 'ID_PROG')";
                statement.executeUpdate(insertDataQueryUsers3);
                System.out.println("Данные в таблицу 'users3' добавлены успешно.\n");

// Удаление таблицы 'users4', если она уже существует
                String dropTableQueryUsers4 = "DROP TABLE IF EXISTS users4";
                statement.executeUpdate(dropTableQueryUsers4);

// Создание таблицы 'users4' с другой структурой
                String createTableQueryUsers4 = "CREATE TABLE users4 (" +
                        "employeeId SERIAL PRIMARY KEY," +
                        "phoneNumber VARCHAR(20) NOT NULL," +
                        "salary DECIMAL(10,2) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQueryUsers4);
                System.out.println("Таблица 'users4' с новой структурой создана успешно.");

                String setInitialValueQuery4 = "ALTER SEQUENCE users4_employeeId_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery4);

// Вставка данных в таблицу 'users4'
                String insertDataQueryUsers4 = "INSERT INTO users4 (phoneNumber, salary) VALUES " +
                        "('555-1234', 50000.00), " +
                        "('555-4321', 60000.00), " +
                        "('555-6789', 70000.00)";
                statement.executeUpdate(insertDataQueryUsers4);
                System.out.println("Данные в таблицу 'users4' добавлены успешно.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect2() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Connected to the database!");

                try (Statement statement = connection.createStatement()) {
                    // Удаление и создание таблиц users3 и users4 (ваш текущий код)

                    // INNER JOIN между таблицами users3 и users4 по столбцу employeeId
                    String sqlQuery = "SELECT u3.employeeId, u3.firstName, u3.email, u3.jobId, u4.phoneNumber, u4.salary "
                            + "FROM users3 u3 "
                            + "INNER JOIN users4 u4 ON u3.employeeId = u4.employeeId";
                    ResultSet resultSet = statement.executeQuery(sqlQuery);

                    while (resultSet.next()) {
                        System.out.println("EmployeeId: " + resultSet.getInt(1)
                                + ", FirstName: " + resultSet.getString(2)
                                + ", Email: " + resultSet.getString(3)
                                + ", JobId: " + resultSet.getString(4)
                                + ", PhoneNumber: " + resultSet.getString(5)
                                + ", Salary: " + resultSet.getDouble(6));
                    }

                    // Создание таблицы 'users5' для хранения результата объединения данных
                    String createTableQueryUsers5 = "CREATE TABLE users5 AS "
                            + "SELECT u3.employeeId, u3.firstName, u3.email, u3.jobId, u4.phoneNumber, u4.salary "
                            + "FROM users3 u3 "
                            + "INNER JOIN users4 u4 ON u3.employeeId = u4.employeeId";
                    statement.executeUpdate(createTableQueryUsers5);
                    System.out.println("Таблица 'users5' создана на основе результатов объединения.");
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
