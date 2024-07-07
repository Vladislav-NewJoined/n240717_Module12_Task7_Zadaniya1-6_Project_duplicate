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
                
                Ответ по заданию 2, Для чего он используется?:
                  Основной сценарий использования Retrofit заключается в выполнении HTTP запросов к

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