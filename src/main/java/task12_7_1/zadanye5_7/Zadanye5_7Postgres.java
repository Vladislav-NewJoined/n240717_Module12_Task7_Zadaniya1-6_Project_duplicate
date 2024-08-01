package task12_7_1.zadanye5_7;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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

public class Zadanye5_7Postgres {

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
                Параллельно с выполнением данного задания, выполнено объединение таблиц 'users1' и 'users2' методом
                'INNER JOIN', и по результатам объединения создана новая таблица: 'users3'.
            
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
        connect2();
        connect3();
    }

    private static void connect() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
                System.out.println("Данные в таблицу 'users1' добавлены успешно.");

                // Получение содержимого таблицы 'users1'
                System.out.println("Содержимое 'users1':");
                String selectQueryUsers1 = "SELECT * FROM users1";
                ResultSet resultSetUsers1 = statement.executeQuery(selectQueryUsers1);
                while (resultSetUsers1.next()) {
                    System.out.println("EmployeeId: " + resultSetUsers1.getInt(1) +
                            ", FirstName: " + resultSetUsers1.getString(2) +
                            ", Email: " + resultSetUsers1.getString(3) +
                            ", JobId: " + resultSetUsers1.getString(4));
                }

                // Удаление таблицы 'users2', если она уже существует
                String dropTableQueryUsers2 = "DROP TABLE IF EXISTS users2";
                statement.executeUpdate(dropTableQueryUsers2);

                // Создание таблицы 'users2' с другой структурой
                String createTableQueryUsers2 = "CREATE TABLE users2 (" +
                        "employeeId SERIAL PRIMARY KEY," +
                        "phoneNumber VARCHAR(20) NOT NULL," +
                        "salary DECIMAL(10,2) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableQueryUsers2);
                System.out.println("\nТаблица 'users2' успешно создана.");

                String setInitialValueQuery2 = "ALTER SEQUENCE users2_employeeId_seq RESTART WITH 100";
                statement.executeUpdate(setInitialValueQuery2);

                // Вставка данных в таблицу 'users2'
                String insertDataQueryUsers2 = "INSERT INTO users2 (phoneNumber, salary) VALUES " +
                        "('555-1234', 50000.00), " +
                        "('555-4321', 60000.00), " +
                        "('555-6789', 70000.00)";
                statement.executeUpdate(insertDataQueryUsers2);
                System.out.println("Данные в таблицу 'users2' добавлены успешно.");

                System.out.println("Содержимое 'users2':");
                String queryUsers2 = "SELECT * FROM users2";
                ResultSet resultSet2 = statement.executeQuery(queryUsers2);
                while (resultSet2.next()) {
                    System.out.println("EmployeeId: " + resultSet2.getInt("employeeId")
                            + ", PhoneNumber: " + resultSet2.getString("phoneNumber")
                            + ", Salary: " + resultSet2.getString("salary"));
                }
                System.out.println();

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

            Document doc = new Document("firstName", "Steben").append("age", 26).append("city", "Paris");
            collection.insertOne(doc);

            System.out.println("Документ успешно добавлен в коллекцию 'mongoTestCollection'");
            // Получение содержимого коллекции "mongoTestCollection"
            System.out.println("Содержимое 'mongoTestCollection':");
            for (Document document : collection.find()) {
                System.out.println(document.toJson());
            }

            System.out.println("\nСлой с данными успешно создан");

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void connect2() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Соединение с базой данных произошло успешно!\n");

                try (Statement statement = connection.createStatement()) {
                    // Удаляем таблицу 'users3', если она существует
                    String dropTableQueryUsers3 = "DROP TABLE IF EXISTS users3";
                    statement.executeUpdate(dropTableQueryUsers3);

                    // INNER JOIN между таблицами users1 и users2 по столбцу employeeId
                    String sqlQuery = "SELECT u1.employeeId, u1.firstName, u1.email, u1.jobId, u2.phoneNumber, u2.salary " +
                            "FROM users1 u1 INNER JOIN users2 u2 ON u1.employeeId = u2.employeeId";
                    ResultSet resultSet = statement.executeQuery(sqlQuery);

                    System.out.println("Содержимое 'users3':");
                    while (resultSet.next()) {
                        System.out.println("EmployeeId: " + resultSet.getInt(1)
                                + ", FirstName: " + resultSet.getString(2)
                                + ", Email: " + resultSet.getString(3)
                                + ", JobId: " + resultSet.getString(4)
                                + ", PhoneNumber: " + resultSet.getString(5)
                                + ", Salary: " + resultSet.getDouble(6));
                    }

                    // Создаем таблицу 'users3' для хранения результата объединения данных
                    String createTableQueryUsers3 = "CREATE TABLE users3 AS " +
                            "SELECT u1.employeeId, u1.firstName, u1.email, u1.jobId, u2.phoneNumber, u2.salary " +
                            "FROM users1 u1 INNER JOIN users2 u2 ON u1.employeeId = u2.employeeId";
                    statement.executeUpdate(createTableQueryUsers3);
                    System.out.println("\nТаблица 'users3' создана на основе результатов объединения таблиц " +
                            "'users1' и 'users2' с использованием метода 'INNER JOIN'.\n");

                    System.out.println("Таким образом, созданы слои с данными в виде таблиц ‘users1’, ‘users2’ и ‘users3’.");
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private static void connect3() {
        try {
// Подключение к базе данных PostgreSQL
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/somedbPGtest", "someuser", "123");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users1");
            Map<String, Map<String, String>> pgData = new HashMap<>();
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String email = rs.getString("email");
                String jobId = rs.getString("jobId");

                Map<String, String> userData = new HashMap<>();
                userData.put("email", email);
                userData.put("jobId", jobId);

                pgData.put(firstName, userData);
            }
            connection.close();

// Подключение к базе данных MongoDB
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("mongoTest");
            MongoCollection<Document> collection = database.getCollection("mongoTestCollection");

            Map<String, Map<String, String>> mongoData = new HashMap<>();
            for (Document doc : collection.find()) {
                String firstName = doc.getString("firstName");
                int age = doc.getInteger("age", 0);
                String city = doc.getString("city");

                Map<String, String> userData = new HashMap<>();
                userData.put("age", String.valueOf(age));
                userData.put("city", city);

                mongoData.put(firstName, userData);
            }

            // Подключение к базе данных MongoDB
            MongoClient mongoClient2 = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database2 = mongoClient2.getDatabase("mongoTest2");

            // Получить или создать коллекцию 'mongoTestCollection2' в базе данных
            MongoCollection<Document> collection2 = database2.getCollection("mongoTestCollection2");

            // Удаление предыдущей коллекции, если она существует
            if (database.listCollectionNames().into(new ArrayList<>()).contains("mongoTestCollection2")) {
                database.getCollection("mongoTestCollection2").drop();
                System.out.println("Предыдущая коллекция успешно удалена");
            }

// Объединение данных по полю "firstName"
            Map<String, Map<String, String>> combinedData = new HashMap<>();
            for (Map.Entry<String, Map<String, String>> entry : pgData.entrySet()) {
                String firstName = entry.getKey();
                if (mongoData.containsKey(firstName)) {
                    Map<String, String> userData = new HashMap<>();
                    userData.putAll(entry.getValue());
                    userData.putAll(mongoData.get(firstName));
                    combinedData.put(firstName, userData);

                    // Вывод результата объединения в формате строки
                    System.out.println("Combined Document for " + firstName + ": " + userData);
                }
            }
            // Для каждого объединенного документа, создать объект Document и вставить его в коллекцию
            for (Map.Entry<String, Map<String, String>> entry : combinedData.entrySet()) {
                Document doc = new Document();
                doc.put("firstName", entry.getKey());

                // Добавить остальные поля данных из объединенного документа
                Map<String, String> userData = entry.getValue();
                doc.putAll(userData);

                // Вставить документ в коллекцию 'mongoTestCollection2'
                collection2.insertOne(doc);
            }

            System.out.println("Документы успешно добавлены в коллекцию 'mongoTestCollection2'");

            mongoClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
