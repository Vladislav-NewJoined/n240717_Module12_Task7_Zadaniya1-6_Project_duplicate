package task11_9_1;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.List;

// Библиотека GSON добавляется отсюда: https://github.com/google/gson в виде зависимости Maven

    interface ReceiversService {
        @POST("inRadius")
        Call<List<Receiver>> listReceivers(@Body InRadiusDto dto);
    }

public class Task11_9_1 {

//    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
//
//    public interface JsonPlaceholderAPI {
//        @GET("/posts/1")
//        Call<Post> getPost();
//    }

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

//        Gson gson = new Gson();
//        Post post = new Post();
//        String json = gson.toJson(post);
//        System.out.println(json);
//        System.out.println(gson.fromJson(json,Post.class));




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://receivers.api.ecohub.eco/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReceiversService service = retrofit.create(ReceiversService.class);
        InRadiusDto dto = new InRadiusDto(0, 0, 100000, 0, 10);
        Call<List<Receiver>> repos = service.listReceivers(dto);
        try {
            Response<List<Receiver>> res = repos.execute();
            for (Receiver r : res.body()) {
                System.out.println(r);
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
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

class InRadiusDto {
    final float latitude;
    final float longitude;
    final int radius;
    final int page;
    final int pageSize;

    InRadiusDto(float latitude, float longitude, int radius, int page, int pageSize) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.page = page;
        this.pageSize = pageSize;
    }
}

record Receiver(String id, String title) {

    @Override
    public String toString() {
        return "Receiver{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}