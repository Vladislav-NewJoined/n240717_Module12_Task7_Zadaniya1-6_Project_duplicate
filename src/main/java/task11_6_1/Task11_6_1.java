package task11_6_1;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;


import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;

public class Task11_6_1 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                Модуль 11. Протокол “HTTP”. Задание №6:\s
                Цель задания: знакомство с веб  - сервером apache
                    Задание:
                    1. Что такое Apache в контексте протокола HTTP?
                    2. Для чего используется Apache в рамках работы протокола HTTP?
                    3. Существуют ли альтернативы Apache?
                
                Решение:\s""");

        System.out.println("""
                Ответ на вопрос 1, Что такое Apache в контексте протокола HTTP?:
                  Apache в контексте протокола HTTP — это веб-сервер, официально известный как Apache HTTP Server.
                  Apache является одним из самых популярных и распространенных веб-серверов в мире. Он предоставляет
                  возможности обработки HTTP-запросов и отдачи HTTP-ответов, обслуживая веб-сайты и приложения через
                  протокол HTTP. Apache также может использоваться для обработки запросов HTTPS и выполнения других
                  функций, связанных с веб-сервером. Apache поддерживает множество возможностей настройки и
                  расширения, что делает его популярным выбором для хостинга веб-сайтов и приложений на Java
                  и других языках программирования.
                
                Ответ на вопрос 2, Для чего используется Apache в рамках работы протокола HTTP?:
                  Apache в контексте протокола HTTP — это веб-сервер, официально известный как Apache HTTP Server.
                
                
                Примеры использования Apache HTTP с использованием запросов GET и POST:
                \s""");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?lat=52.27483630743035&lon=104.25449925002037&appid=aba15956c1b0894dd6c841fd1e154d45");
        HttpResponse response = httpClient.execute(request);
        Scanner sc = new Scanner(response.getEntity().getContent());
        System.out.println(response.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }

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


        try {
            URL url3 = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection3 = (HttpURLConnection) url3.openConnection();
            connection3.setRequestMethod("POST");

            BufferedReader in3 = new BufferedReader(new InputStreamReader(connection3.getInputStream()));
            String inputLine3;
            StringBuffer response3 = new StringBuffer();

            while ((inputLine3 = in3.readLine()) != null) {
                response3.append(inputLine3);
            }
            in3.close();

            System.out.println(response3.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
