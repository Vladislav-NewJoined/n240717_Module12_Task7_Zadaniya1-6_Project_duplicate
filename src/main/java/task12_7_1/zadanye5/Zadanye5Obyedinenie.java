package task12_7_1.zadanye5;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

public class Zadanye5Obyedinenie {

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

        connect3();
    }

    private static void connect3() {
        System.out.println("\nОбъединяем данные из базы данных Postgres (таблицы users1) с документом из " +
                "коллекции базы данных MongoDB (коллекции mongoTestCollection):");
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("mongoTest");

            // Удаление предыдущей коллекции, если она существует
            if (database.listCollectionNames().into(new ArrayList<>()).contains("mongoTestCollection")) {
                database.getCollection("mongoTestCollection").drop();
//                System.out.println("Предыдущая коллекция успешно удалена");
            }

            // Создание новой коллекции "mongoTestCollection"
            MongoCollection<Document> collection = database.getCollection("mongoTestCollection");

            Document doc = new Document("firstName", "Alice").append("age", 26).append("city", "Paris");
            collection.insertOne(doc);

//            System.out.println("Документ успешно добавлен в коллекцию 'mongoTestCollection'");
//            System.out.println("\nСлой с данными успешно создан");

//            mongoClient.close();

            // Подключение к базе данных Postgres и выполнение запроса
            Map<String, Map<String, Object>> postgresData = new HashMap<>();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet1 = statement.executeQuery("SELECT employeeId, firstName, email, jobId FROM users1")) {

                while (resultSet1.next()) {
                    String firstName = resultSet1.getString("firstName");
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("email", resultSet1.getString("email"));
                    userData.put("jobId", resultSet1.getString("jobId"));
                    postgresData.put(firstName, userData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

// Получение документа из коллекции mongoTestCollection в MongoDB
            Map<String, Map<String, Object>> mongoData = new HashMap<>();

            Document mongoDocument = collection.find().first();
            if (mongoDocument != null) {
                String firstName = mongoDocument.getString("firstName");
                Map<String, Object> mongoUserData = new HashMap<>();
                mongoUserData.put("age", mongoDocument.getInteger("age"));
                mongoUserData.put("city", mongoDocument.getString("city"));
                mongoData.put(firstName, mongoUserData);
            }

// Объединение данных по общему элементу firstName
            List<Map<String, Map<String, Object>>> combinedData = new ArrayList<>();
            for (String key : postgresData.keySet()) {
                if (mongoData.containsKey(key)) {
                    Map<String, Map<String, Object>> result = new HashMap<>();
                    result.put(key, new HashMap<>());
                    result.get(key).putAll(postgresData.get(key));
                    result.get(key).putAll(mongoData.get(key));
                    combinedData.add(result);
                }
            }

// Вывод объединенных данных
            for (Map<String, Map<String, Object>> entry : combinedData) {
                System.out.println(entry);
            }

// Удаление предыдущей коллекции 'mongoTestCollection2', если она существует
            if (database.listCollectionNames().into(new ArrayList<>()).contains("mongoTestCollection2")) {
                database.getCollection("mongoTestCollection2").drop();
                System.out.println("Предыдущая коллекция 'mongoTestCollection2' успешно удалена");
            }

// Создание новой коллекции 'mongoTestCollection2'
            MongoCollection<Document> collection2 = database.getCollection("mongoTestCollection2");

// Сохранение объединенных данных в новую коллекцию
            for (Map<String, Map<String, Object>> entry : combinedData) {
                Document combinedDocument = new Document();
                for (Map.Entry<String, Map<String, Object>> mapEntry : entry.entrySet()) {
                    Document innerDocument = new Document(mapEntry.getValue());
                    combinedDocument.append(mapEntry.getKey(), innerDocument);
                }
                collection2.insertOne(combinedDocument);
            }

            System.out.println("Данные успешно сохранены в коллекции 'mongoTestCollection2'");

// Объединение данных из PostgreSQL и MongoDB
            List<Document> combinedDocuments = new ArrayList<>();
            for (String key : postgresData.keySet()) {
                if (mongoData.containsKey(key)) {
                    Document combinedDocument = new Document();
                    combinedDocument.append("firstName", key);
                    combinedDocument.append("mongoData", mongoData.get(key));
                    combinedDocument.append("postgresData", postgresData.get(key));
                    combinedDocuments.add(combinedDocument);
                }
            }

// Создание коллекции и сохранение объединенных данных
//            MongoCollection<Document> collection2 = database.getCollection("mongoTestCollection2");
            collection2.insertMany(combinedDocuments);
            System.out.println("Объединенные данные успешно сохранены в коллекцию 'mongoTestCollection2' в базе данных 'mongoTest'");

// Закрытие соединения с MongoDB
            mongoClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
