package task12_3_1.zadanye3_4;

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

public class Zadanye3_4 {
    public static void main(String[] args) throws SQLException {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №4:\s
                Задание:
                3. Напишите запрос для вывода всех имен и  Employee ID

                Решение:
            \s""");

        connect();
    }

    private static void connect() {

        Connection conn;
        Statement stmt = null; // Создаем объект Statement stmt

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedbPostgres", "someuser", "123");
            System.out.println("Connection to PostgreSQL has been established.");
            stmt = conn.createStatement();

            // Удаление таблицы, если она уже существует
            String dropTableQuery = "DROP TABLE IF EXISTS users";
            stmt.execute(dropTableQuery);
//            stmt.executeUpdate(dropTableQuery);

            // Создание таблицы 'users'
            String createTableQuery = "CREATE TABLE users ("
                    + "employee_id SERIAL PRIMARY KEY,"
                    + "first_name VARCHAR(20) NOT NULL,"
                    + "last_name VARCHAR(20) NOT NULL,"
                    + "email VARCHAR(20) NOT NULL,"
                    + "phone_number VARCHAR(20) NOT NULL,"
                    + "hire_date VARCHAR(20) NOT NULL,"
                    + "job_id VARCHAR(20) NOT NULL,"
                    + "salary DECIMAL(10,2) NOT NULL"
                    + ")";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table created.\n");

            // Установка начального значения для 'employee_id'
            String setInitialValueQuery = "ALTER SEQUENCE users_employee_id_seq RESTART WITH 100;";
            stmt.execute(setInitialValueQuery);

            // Вставка данных в таблицу 'users'
            String insertDataQuery = "INSERT INTO users (first_name, last_name, email, phone_number, hire_date, job_id, salary) VALUES "
                    + "('Steben', 'King', 'SKING', '515.123.4567', '1987-06-17', 'AD_PRES', 24000.00), "
                    + "('Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1987-06-18', 'AD_VP', 17000.00), "
                    + "('Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1987-06-19', 'AD_VP', 17000.00), "
                    + "('Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1986-06-20', 'ID_PROG', 9000.00), "
                    + "('Bruce', 'Ernst', 'BERNST', '590.423.4568', '1986-06-21', 'ID_PROG', 6000.00), "
                    + "('David', 'Austin', 'DAUSTIN', '590.423.4569', '1986-06-22', 'ID_PROG', 4800.00), "
                    + "('Valli', 'Pataballa', 'VPATABAL', '590.423.4569', '1986-06-23', 'ID_PROG', 4800.00)";
            stmt.executeUpdate(insertDataQuery);

            // Выполнение запроса на выборку всех имен и Employee ID
            String selectNamesAndIdsQuery = "SELECT first_name, employee_id FROM users";
            ResultSet namesAndIdsRs = stmt.executeQuery(selectNamesAndIdsQuery);

            while (namesAndIdsRs.next()) {
                int employeeId = namesAndIdsRs.getInt("employee_id");
                String firstName = namesAndIdsRs.getString("first_name");
                System.out.println("Employee ID: " + employeeId + ", First Name: " + firstName);
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
    }
}
