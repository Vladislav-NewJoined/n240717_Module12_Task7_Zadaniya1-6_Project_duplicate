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
                
                \s""");

//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?lat=52.27483630743035&lon=104.25449925002037&appid=aba15956c1b0894dd6c841fd1e154d45");
//        HttpResponse response = httpClient.execute(request);
//        Scanner sc = new Scanner(response.getEntity().getContent());
//        System.out.println(response.getStatusLine());
//        while (sc.hasNext()) {
//            System.out.println(sc.nextLine());
//        }
//
////        HttpPost requestPost = new HttpPost("https://api.openweathermap.org/data/2.5/weather?lat=52.27483630743035&lon=104.25449925002037&appid=aba15956c1b0894dd6c841fd1e154d45");
////        HttpResponse response = httpClient.execute(request);
////        Scanner sc = new Scanner(response.getEntity().getContent());
////        System.out.println(response.getStatusLine());
////        while (sc.hasNext()) {
////            System.out.println(sc.nextLine());
////        }





//        try {
//            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            // Отправляем POST данные, если необходимо
//            String postData = "key1=value1&key2=value2";
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = postData.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }
//
//            // Получаем ответ
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // Выводим ответ в консоль
//            System.out.println(response.toString());
//
//            connection.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }






        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
