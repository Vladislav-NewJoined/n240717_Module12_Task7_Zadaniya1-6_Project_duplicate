package task12_7_1.zadanye5_6;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

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

public class Zadanye5_6Postgres {

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
            System.out.println("\nСлой с данными успешно создан");

            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
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

            // Удаление предыдущей коллекции, если она существует
            if (database.listCollectionNames().into(new ArrayList<>()).contains("mongoTestCollection2")) {
                database.getCollection("mongoTestCollection2").drop();
                System.out.println("Предыдущая коллекция успешно удалена");
            }

            // Получить или создать коллекцию 'mongoTestCollection2' в базе данных
            MongoCollection<Document> collection2 = database2.getCollection("mongoTestCollection2");

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
