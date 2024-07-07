package task11_8_1;

import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

// Библиотека GSON добавляется отсюда: https://github.com/google/gson в виде зависимости Maven

public class Task11_8_1 {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public interface JsonPlaceholderAPI {
        @GET("/posts/1")
        Call<Post> getPost();
    }

    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 11. Протокол “HTTP”. Задание №8:\s
                Цель задания: знакомство с GSON и его возможностями\s
                    Задание:
                    1. Что такое GSON?
                    2. Для чего он используется?
                    3. Приведите пример любого JSON запроса?
                
                Решение:\s""");

        System.out.println("""
                Ответ на вопрос 1, Что такое GSON?:
                  GSON - это библиотека для Java, разработанная Google, которая используется для преобразования
                  объектов Java в формат JSON и наоборот. С помощью GSON можно легко осуществлять сериализацию
                  и десериализацию Java объектов в JSON и обратно, что часто требуется при взаимодействии с
                  веб-сервисами или при работе с базами данных.
                
                Ответ на вопрос 2, Для чего он используется?:
                  GSON используется в программировании на языке Java для преобразования объектов Java в формат
                  JSON (и наоборот) с целью обмена данными между Java приложением и внешними системами, такими
                  как веб-сервисы, клиентские приложения или базы данных. GSON обеспечивает удобный и эффективный
                  способ сериализации и десериализации Java объектов, что делает его необходимым инструментом
                  при работе с данными в формате JSON в Java приложениях.

                Ответ по заданию 3, Приведите пример любого JSON запроса:
                  Пример JSON-запроса может выглядеть следующим образом:
                  {
                     "firstName": "John",
                     "lastName": "Doe",
                     "age": 30,
                     "email": "johndoe@example.com",
                     "address": {
                        "street": "123 Main Street",
                        "city": "Anytown",
                        "state": "NY",
                        "zipCode": "12345"
                     },
                     "phoneNumbers":
                        {
                           "type": "home",
                           "number": "555-1234"
                        },
                        {
                           "type": "work",
                           "number": "555-5678"
                        }
                  }
                
                  В данном примере JSON-запрос содержит информацию о человеке (имя, фамилия, возраст, email и т. д.),
                  его адресе и номерах телефонов.

                Пример использования "GSON":
                \s""");

        Gson gson = new Gson();
        Post post = new Post();
        String json = gson.toJson(post);
        System.out.println(json);
        System.out.println(gson.fromJson(json,Post.class));
        }
    }

    class Post {
        private int userId;
        private int id;
        private String title;
        private String body;

        public String getTitle() {
            return title;
        }
    }