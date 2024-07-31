package task12_7_1.zadanye5_4;

import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

public class Zadanye5_4Postgres {

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
                    5. Создание сервиса для управления данными.

                    Решение:
                    Параллельно с выполнением данного задания, выполнено объединение таблиц '' и '' методом
                    'INNER JOIN', и по результатам объединения создана новая таблица: ''.
                
                    (ПРИМЕЧАНИЕ: при каждом последующем запуске кода перезагрузите соединение
                    с базой данных somedbPostgres, т.е. нажмите disconnect' и затем
                    'connect' в блоке 'DB Browser' внутри 'IntelliJ IDEA'
                    и обновите папку 'public' внутри базы данных):
                    \s""");

        System.out.println("""
                На сервере Docker создан контейнер с базой данных PostgreSQL с именем "postgresTest" при помощи
                команды в терминале Docker Desktop или в терминале среды разработки, например IntelliJ IDEA:
                "docker run --name postgresTest -d -p 5432:5432 -e POSTGRES_DB=somedbPGtest -e POSTGRES_USER=someuser
                 -e POSTGRES_PASSWORD=123 postgres:alpine".
                            
                В приложении DBeaver создано соединение с базой данных с именем "somedbPGtest".
                Для настройки соединения в DBeaver использованы следующие параметры (можно получить в ответ на команду
                в терминале: docker inspect postgresTest):
                Сервер (Хост): localhost (для соединения не со своего компьютера: 172.17.0.2)
                Порт: 5432
                База данных: somedbPGtest
                Пользователь: someuser
                Пароль: 123\s""");

        connect();
//        connect2();
        connect3();
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedbPGtest", "someuser", "123")) {
            try (Statement statement = connection.createStatement()) {
                // Удаление таблицы 'users2', если она уже существует
                String dropTableQueryUsers1 = "DROP TABLE IF EXISTS users1";
                statement.executeUpdate(dropTableQueryUsers1);

                // Создание таблицы 'users1' с другой структурой
                String createTableQueryUsers1 = "CREATE TABLE users1 (" +
                        "employeeId SERIAL PRIMARY KEY," +
                        "firstName VARCHAR(20) NOT NULL," +
                        "email VARCHAR(20) NOT NULL," +
                        "jobId VARCHAR(20) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQueryUsers1);
                System.out.println("\nТаблица 'users1' успешно создана.");

                String setInitialValueQuery1 = "ALTER SEQUENCE users1_employeeId_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery1);

                // Вставка данных в таблицу 'users1'
                String insertDataQueryUsers1 = "INSERT INTO users1 (firstName, email, jobId) VALUES " +
                        "('Steben', 'SKING', 'AD_PRES'), " +
                        "('Neena', 'NKOCHHAR', 'AD_VP'), " +
                        "('Valli', 'VPATABAL', 'ID_PROG')";
                statement.executeUpdate(insertDataQueryUsers1);
                System.out.println("Данные в таблицу 'users1' добавлены успешно.\n");

//                // Удаление таблицы 'users2', если она уже существует
//                String dropTableQueryUsers2 = "DROP TABLE IF EXISTS users2";
//                statement.executeUpdate(dropTableQueryUsers2);
//
//                // Создание таблицы 'users2' с другой структурой
//                String createTableQueryUsers2 = "CREATE TABLE users2 (" +
//                        "employeeId SERIAL PRIMARY KEY," +
//                        "phoneNumber VARCHAR(20) NOT NULL," +
//                        "salary DECIMAL(10,2) NOT NULL" +
//                        ")";
//                statement.executeUpdate(createTableQueryUsers2);
//                System.out.println("Таблица 'users2' успешно создана.");
//
//                String setInitialValueQuery2 = "ALTER SEQUENCE users2_employeeId_seq RESTART WITH 100";
//                statement.executeUpdate(setInitialValueQuery2);
//
//                // Вставка данных в таблицу 'users2'
//                String insertDataQueryUsers2 = "INSERT INTO users2 (phoneNumber, salary) VALUES " +
//                        "('555-1234', 50000.00), " +
//                        "('555-4321', 60000.00), " +
//                        "('555-6789', 70000.00)";
//                statement.executeUpdate(insertDataQueryUsers2);
//                System.out.println("Данные в таблицу 'users2' добавлены успешно.\n");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("mongoTest");

            // Удаление предыдущей коллекции, если она существует
            if (database.listCollectionNames().into(new ArrayList<>()).contains("mongoTestCollection")) {
                database.getCollection("mongoTestCollection").drop();
                System.out.println("Предыдущая коллекция успешно удалена");
            }

            // Создание новой коллекции "mongoTestCollection"
            MongoCollection<Document> collection = database.getCollection("mongoTestCollection");

            Document doc = new Document("firstName", "Alice").append("age", 26).append("city", "Paris");
            collection.insertOne(doc);

            System.out.println("Документ успешно добавлен в коллекцию 'mongoTestCollection'");
            System.out.println("\nСлой с данными успешно создан");

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static void connect2() {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            if (connection != null) {
//                System.out.println("Соединение с базой данных произошло успешно!\n");
//
//                try (Statement statement = connection.createStatement()) {
//                    // Удаляем таблицу 'users3', если она существует
//                    String dropTableQueryUsers3 = "DROP TABLE IF EXISTS users3";
//                    statement.executeUpdate(dropTableQueryUsers3);
//
//                    // INNER JOIN между таблицами users1 и users2 по столбцу employeeId
//                    String sqlQuery = "SELECT u1.employeeId, u1.firstName, u1.email, u1.jobId, u2.phoneNumber, u2.salary " +
//                            "FROM users1 u1 INNER JOIN users2 u2 ON u1.employeeId = u2.employeeId";
//                    ResultSet resultSet = statement.executeQuery(sqlQuery);
//
//                    while (resultSet.next()) {
//                        System.out.println("EmployeeId: " + resultSet.getInt(1)
//                                + ", FirstName: " + resultSet.getString(2)
//                                + ", Email: " + resultSet.getString(3)
//                                + ", JobId: " + resultSet.getString(4)
//                                + ", PhoneNumber: " + resultSet.getString(5)
//                                + ", Salary: " + resultSet.getDouble(6));
//                    }
//
//                    // Создаем таблицу 'users3' для хранения результата объединения данных
//                    String createTableQueryUsers3 = "CREATE TABLE users3 AS " +
//                            "SELECT u1.employeeId, u1.firstName, u1.email, u1.jobId, u2.phoneNumber, u2.salary " +
//                            "FROM users1 u1 INNER JOIN users2 u2 ON u1.employeeId = u2.employeeId";
//                    statement.executeUpdate(createTableQueryUsers3);
//                    System.out.println("\nТаблица 'users3' создана на основе результатов объединения таблиц " +
//                            "'users1' и 'users2' с использованием метода 'INNER JOIN'.\n");
//
//                    System.out.println("Таким образом, созданы слои сданными в виде таблиц ‘users1’, ‘users2’ и ‘users3’.");
//                }
//            } else {
//                System.out.println("Failed to make connection!");
//            }
//        } catch (SQLException e) {
//            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//        }
//    }

    private static void connect3() {
        try {
            // Подключение к PostgreSQL
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedbPGtest", "someuser", "123");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users1");

// Обработка результатов выборки и вывод на консоль
            while (rs.next()) {
                int employeeId = rs.getInt("employeeId");
                String firstName = rs.getString("firstName");
                String email = rs.getString("email");
                String jobId = rs.getString("jobId");
                System.out.println("EmployeeId: " + employeeId + ", FirstName: " + firstName + ", Email: " + email + ", JobId: " + jobId);
            }

            // Подключение к MongoDB
// Установка строки подключения
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");

// Создание настроек клиента с помощью строки подключения
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();

// Создание клиента MongoDB
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("momongoTest");
            MongoCursor<Document> cursor = database.getCollection("mongoTestCollection").find().iterator();

            // Объединение данных
            while (rs.next()) {
                System.out.println("PostgreSQL Data: " + rs.getString("column_name"));
            }

            while (cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println("MongoDB Data: " + document.toJson());
            }

            connection.close();
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}