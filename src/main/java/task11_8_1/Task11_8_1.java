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
                Модуль 11. Протокол “HTTP”. Задание №7:\s
                Цель задания: знакомство с Retrofit и его возможностями\s
                    Задание:
                    1. Что такое Retrofit?
                    2. Приведите сценарий его  использования?
                
                Решение:\s""");

        System.out.println("""
                Ответ на вопрос 1, Что такое Retrofit?:
                  Retrofit - это библиотека для работы с HTTP запросами в приложениях на языке
                  Java (или Android) с использованием архитектурного стиля RESTful. Она
                  облегчает выполнение сетевых запросов к серверу, отправку данных и обработку
                  ответов. Retrofit позволяет определять структуру API, создавать запросы и
                  получать ответы с сервера, а также работать с различными типами конвертеров
                  для сериализации и десериализации данных (например, JSON, XML).
                
                  Retrofit является одним из самых популярных и удобных инструментов дл
                  работы с сетевыми запросами в Java и Android разработке, так как предоставляет
                  простой и интуитивно понятный интерфейс, а также хорошо интегрируется с
                  другими популярными библиотеками, такими как Gson или Jackson для работы
                  с JSON, и RxJava для реактивного программирования.
                
                Ответ по заданию 2, Приведите сценарий его использования:
                  Основной сценарий использования Retrofit заключается в выполнении HTTP запросов к
                  удалённому серверу и обработке полученных данных.
                
                  Например, мы можем создать интерфейс, в котором будут описаны все необходимые
                  запросы к API. Затем с помощью Retrofit мы создадим объект, который будет
                  использовать этот интерфейс для отправки запросов и обработки ответов от сервера.
                
                  Таким образом, Retrofit позволяет упростить процесс взаимодействия с сервером,
                  обеспечивая удобный и гибкий способ работы с сетевыми запросами в приложениях
                  на Java.

                Примеры использования "Retrofit":
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