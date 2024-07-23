package task12_3_1.zadanye3;

import java.sql.*;

// Команда в терминале для создания базы данных MMariaDB: docker run --name mysql -d -p 3306:3306 -e MYSQL_DATABASE=somedb -e MYSQL_USER=someuser -e MYSQL_PASSWORD=123 yobasystems/alpine-mariadb
// Значения параметров для настройки соединения в DBeaver (пришли в ответ на команду в терминале: docker inspect mysql:
// Сервер (Хост): 172.17.0.2 (нужно писать localhost вместо этого)
// База данных: somedb (значение переменной окружения MYSQL_DATABASE)
// Пользователь: someuser (значение переменной окружения MYSQL_USER)
// Пароль: 123 (значение переменной окружения MYSQL_PASSWORD)
// Драйвер: MySQL

public class Zadanye3 {
    public static void main(String[] args) throws SQLException {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №4:\s
                3. Напишите запрос для вывода всех имен и  Employee ID

                Решение:
            \s""");

        connect();
    }

    private static void connect() throws SQLException {

        Connection conn;
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
                    "   job_id varchar(20) not null,\n" +
                    "   salary decimal(10,2) not null\n" +
                    ");";
            stmt.execute(createTableQuery);
            System.out.println("Table created\n");

// Установка начального значения для 'employee_id'
            String setInitialValueQuery = "alter table Users auto_increment = 100;";
            stmt.execute(setInitialValueQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (first_name, last_name, email, phone_number, hire_date, job_id, salary) values " +
                    "('Steben', 'King', 'SKING', '515.123.4567', '1987-06-17', 'AD_PRES', 24000.0), " +
                    "('Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1987-06-18', 'AD_VP', 17000.0), " +
                    "('Lex', 'De Haan', 'LDEHAAN', '515.123.4569', '1987-06-19', 'AD_VP', 17000.00), " +
                    "('Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', '1986-06-20', 'ID_PROG', 9000.0), " +
                    "('Bruce', 'Ernst', 'BERNST', '590.423.4568', '1986-06-21', 'ID_PROG', 6000.0), " +
                    "('David', 'Austin', 'DAUSTIN', '590.423.4569', '1986-06-22', 'ID_PROG', 4800.0), " +
                    "('Valli', 'Pataballa', 'VPATABAL', '590.423.4569', '1986-06-23', 'ID_PROG', 4800.0);";
            stmt.execute(insertDataQuery);

            String minMaxSalaryQuery = "SELECT MIN(salary) AS min_salary, MAX(salary) AS max_salary FROM Users";
            ResultSet minMaxRs = stmt.executeQuery(minMaxSalaryQuery);

// Чтение и вывод минимальной и максимальной зарплаты
            while (minMaxRs.next()) {
                double minSalary = minMaxRs.getDouble("min_salary");
                double maxSalary = minMaxRs.getDouble("max_salary");
                System.out.println("Минимальная зарплата: " + minSalary);
                System.out.println("Максимальная зарплата: " + maxSalary);
            }

            minMaxRs.close();

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
