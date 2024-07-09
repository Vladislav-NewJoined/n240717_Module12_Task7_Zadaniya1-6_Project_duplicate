package task11_9_1.zadaniye7.domain;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import task11_9_1.zadaniye7.LocationService;
import task11_9_1.zadaniye7.models.LocationDto;
import task11_9_1.zadaniye7.data_sources.ReceiverApiDataSource;
import task11_9_1.zadaniye7.models.PostModel;

import java.io.IOException;

public class ReceiverService {
    final ReceiverApiDataSource receiverApiDataSource;

    public ReceiverService(ReceiverApiDataSource receiverApiDataSource) {
        this.receiverApiDataSource = receiverApiDataSource;
    }


    Retrofit retrofit2 = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/posts/")
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public PostModel fetch() {
        PostModel newPostModel = new PostModel(1, "New PostModel Title", "New PostModel Body"); // Пример создания нового поста
        Call<PostModel> repos = receiverApiDataSource.createUserPost(newPostModel); // Пример: создание нового поста
        try {
            Response<PostModel> res = repos.execute();
            System.out.println(res.body());
            return res.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocationService locationService = retrofit2.create(LocationService.class);

        // Создаем объект LocationDto с заданными координатами
        LocationDto location = new LocationDto(51.5074, 0.1278);

        // Отправляем координаты на сервер
        Call<LocationDto> locationCall = locationService.sendLocation(location);

        try {
            Response<LocationDto> response = locationCall.execute();
            // Проверка статус кода ответа
            int statusCode = response.code();
            System.out.println("Status code: " + statusCode);

            try (ResponseBody responseBody = response.errorBody()) {
                if (response.isSuccessful()) {
                    LocationDto receivedLocation = response.body();
                    System.out.println("Received location: " + receivedLocation);
                } else {
                    System.out.println("Request was not successful: " + responseBody.string());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
