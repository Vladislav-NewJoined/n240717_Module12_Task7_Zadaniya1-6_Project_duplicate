package task11_9_1;

import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.http.GET;

// Библиотека GSON добавляется отсюда: https://github.com/google/gson в виде зависимости Maven

public class Task11_9_1 {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public interface JsonPlaceholderAPI {
        @GET("/posts/1")
        Call<Post> getPost();
    }

    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 11. Протокол “HTTP”. Задание №9, Проект:\s
                Цель задания: совершенствование  навыков работы с протоколом HTTP\s
                    Задание:
                    1. В ранее созданном проекте, в папке main  создайте New Package c названием models, далее
                       класс Receiver
                    2. В созданный класс Receiver вставляем  соответствующий код класса  из папки target из ранее
                       созданного проекта
                    3. В ранее созданном Package под названием models создаем класс с названием InRadiusDto, далее
                       копируем в него соответствующий код класса из папки target проекта созданного на предыдущих уроках
                    4. В папке main созданного ранее проекта создайте папку New Package с названием data_sources и
                       New Package с названием domain
                    5. В папке data_sources создайте класс ReceiverApiDataSource в него скопируйте содержимое
                       интерфейса ReceiversService из класса Main который содержится в папке target
                    6. В папке  domain создайте класс ReceiveService в нем сделайте
                       import data_sources.ReceiverApiDataSource и в созданном публичном классе реализуйте
                       получение экземпляра  receiverApiDataSource далее реализуйте метод fetch и перенесите
                       в него содержимое из класса  Main в находящегося в папке models, подставьте в
                       соответствующую строчку кода  receiverApiDataSource в исключении try реализуйте
                       возврат res.body, а в исключении catch возврат null
                    7. В классе Main находящегося в папке models создайте сервис и передайте в него ранее созданный
                       receiverApiDataSource
                    8. Реализуйте циклическую загрузку данных при вводе значения latitude и longitude
                
                Решение:\s""");

        System.out.println("""
                Решение по заданию 1, В ранее созданном проекте, в папке main  создайте New Package c
                  названием models, далее класс Receiver:
                  GSON - это библиотека для Java, разработанная Google, которая используется для преобразования
                  объектов Java в формат JSON и наоборот. С помощью GSON можно легко осуществлять сериализацию
                  и десериализацию Java объектов в JSON и обратно, что часто требуется при взаимодействии с
                  веб-сервисами или при работе с базами данных.
                
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