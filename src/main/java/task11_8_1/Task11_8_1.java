package task11_8_1;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

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

                Примеры использования "GSON":
                \s""");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<Post> call = jsonPlaceholderAPI.getPost();

        try {
            Post post = call.execute().body();
            System.out.println("Post title: " + post.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Post {
        private int userId;
        private int id;
        private String title;
        private String body;

        public String getTitle() {
            return title;
        }
    }
}