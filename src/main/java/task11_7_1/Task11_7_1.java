package task11_7_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task11_7_1 {
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
                  Apache в контексте протокола HTTP — это веб-сервер, официально известный как Apache HTTP Server.
                
                Примеры использования "Retrofit":
                \s""");


        // Способ 2
        try {
            URL url2 = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setRequestMethod("POST");
            connection2.setDoOutput(true);

            // Отправляем POST данные, если необходимо
            String postData2 = "key1=value1&key2=value2";
            try (OutputStream os = connection2.getOutputStream()) {
                byte[] input2 = postData2.getBytes("utf-8");
                os.write(input2, 0, input2.length);
            }

            // Получаем ответ
            BufferedReader in2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
            String inputLine2;
            StringBuffer response2 = new StringBuffer();
            while ((inputLine2 = in2.readLine()) != null) {
                response2.append(inputLine2);
            }
            in2.close();

            // Выводим ответ в консоль
            System.out.println(response2.toString());

            connection2.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
