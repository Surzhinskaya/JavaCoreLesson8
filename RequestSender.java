package Homework8;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.TreeNode;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static java.lang.System.*;


public class RequestSender {

    //Создать клиент
    private static OkHttpClient Client = new OkHttpClient();
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String API_KEY = "LmOyLdMjhqD5Qfkenf9kQo4uBy88FXqA";

    public static WeatherResponse getMinAndMaxTemp(String cityId) throws IOException {
        //Выбрать URL для отправки данных
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(cityId)
                .addQueryParameter("apikey", API_KEY)
                .build();

        //Сформировать запрос
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        //Выполнить запрос
        Response response = Client.newCall(request).execute();

        String json = response.body().string();

        String maxTemp = null;
        String minTemp = null;
        String cityName = new String();
        String ptime;
        try {
            for (int i = 0; i < 5; i++) {

                ptime = objectMapper.readTree(json)
                        .at ("/DailyForecasts")
                        .get(i)
                        .at("/Date")
                        .asText();



                maxTemp = objectMapper.readTree(json)
                        .at("/DailyForecasts")
                        .get(i)
                        .at("/Temperature/Maximum/Value")
                        .asText();

                minTemp = objectMapper.readTree(json)
                        .at ("/DailyForecasts")
                        .get(i)
                        .at("/Temperature/Minimum/Value")
                        .asText();
                out.println(new WeatherResponse(cityName, minTemp, maxTemp, ptime));
            }

            } catch(NullPointerException e){
                out.println("Response in null");
                e.printStackTrace();
            }


        return null;
    }

    public static String getCityId(String cityName) throws IOException {
        //Выбрать URL для отправки данных
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", cityName)
                .build();

        //Сформировать запрос
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        //Выполнить запрос
        Response response = Client.newCall(request).execute();


        String cityId = null;
        try {
            cityId = objectMapper.readTree(response.body().string())
                    .get(0)
                    .at("/Key")
                    .asText();
        } catch (NullPointerException e) {
            out.println("Response in null");
            e.printStackTrace();
        }

        return cityId;
            }


    }
