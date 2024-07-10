package task11_9_1.zadaniye7.domain;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import task11_9_1.zadaniye7.LocationService;
import task11_9_1.zadaniye7.models.LocationDto;
import task11_9_1.zadaniye7.data_sources.ReceiverApiDataSource;
import task11_9_1.zadaniye7.models.PostModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReceiverService {
    final ReceiverApiDataSource receiverApiDataSource;

    public ReceiverService(ReceiverApiDataSource receiverApiDataSource) {
        this.receiverApiDataSource = receiverApiDataSource;
    }


//    Retrofit retrofit2 = new Retrofit.Builder()
////                .baseUrl("https://jsonplaceholder.typicode.com/posts/")
//            .baseUrl("https://jsonplaceholder.typicode.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();


    public PostModel fetch() {
        PostModel newPostModel = new PostModel(1, "New Post", "This is a new post with coordinates", 123.456, 78.910); // Пример создания нового поста
//        Call<PostModel> repos = receiverApiDataSource.createUserPost(newPostModel); // Пример: создание нового поста
//        try {
//            Response<PostModel> res = repos.execute();
//            return res.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        LocationService locationService = retrofit2.create(LocationService.class);
//
//        // Создаем объект LocationDto с заданными координатами
//        LocationDto location = new LocationDto(51.5074, 0.1278);
//
//        // Отправляем координаты на сервер
//        Call<LocationDto> locationCall = locationService.sendLocation(location);

//        try {
//            Response<LocationDto> response = locationCall.execute();
//            // Проверка статус кода ответа
//            int statusCode = response.code();
//            System.out.println("Status code: " + statusCode);
//
//            try (ResponseBody responseBody = response.errorBody()) {
//                if (response.isSuccessful()) {
//                    LocationDto receivedLocation = response.body();
//                    System.out.println("Received location: " + receivedLocation);
//                } else {
//                    System.out.println("Request was not successful: " + responseBody.string());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Response Body: " + response.toString());
            return newPostModel; // или любой другой объект PostModel, созданный в вашем коде
        } catch (Exception e) {
            System.out.println("HTTP Request failed");
            e.printStackTrace();
            return null; // Возвращаем null в случае неудачного запроса
        }

    }
}
