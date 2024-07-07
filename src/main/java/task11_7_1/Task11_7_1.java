package task11_7_1;

import retrofit2.Retrofit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Task11_7_1 {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public interface JsonPlaceholderAPI {
        @GET("posts/1")
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
                  Retrofit - это библиотека для работы с HTTP запросами в приложениях на языке

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








//        // Способ 2
//        try {
//            URL url2 = new URL("https://jsonplaceholder.typicode.com/posts");
//            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
//            connection2.setRequestMethod("POST");
//            connection2.setDoOutput(true);
//
//            // Отправляем POST данные, если необходимо
//            String postData2 = "key1=value1&key2=value2";
//            try (OutputStream os = connection2.getOutputStream()) {
//                byte[] input2 = postData2.getBytes(StandardCharsets.UTF_8);
//                os.write(input2, 0, input2.length);
//            }
//
//            // Получаем ответ
//            BufferedReader in2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
//            String inputLine2;
//            StringBuffer response2 = new StringBuffer();
//            while ((inputLine2 = in2.readLine()) != null) {
//                response2.append(inputLine2);
//            }
//            in2.close();
//
//            // Выводим ответ в консоль
//            System.out.println(response2.toString());
//
//            connection2.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://opi.github.com/")
//                .build();
//
////        GitHubService service = retrofit.create(GitHubService.class);
//    }
//}

//interface GitHubService {
////    @GET("users/{user}/repos")
//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
//}