package com.dexter.labs.digitalfarm.client.owm;

import com.dexter.labs.digitalfarm.client.owm.model.WeatherHistory;
import com.dexter.labs.digitalfarm.exception.ClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Component
public class WeatherClient {

    @Value("${weather.base.url}")
    private String baseUrl;

    @Value("${app.id}")
    private String appId;

    @Value("${weather.history.days}")
    private Integer days;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    public WeatherHistory[] getWeatherHistory(String polygonId) throws ClientException, IOException, InterruptedException {
        long start = Instant.now().minus(days, ChronoUnit.DAYS).getEpochSecond();
        long end = Instant.now().getEpochSecond();

        String urlBuilder = baseUrl + "?polyid=" + polygonId +
                "&start=" + start +
                "&end=" + end +
                "&appid=" + appId;

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder))
                .build();

        HttpResponse<String> response =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) throw new ClientException(response.toString());
        System.out.println("Response:"+response.body());

        WeatherHistory[] weatherHistories = objectMapper.readValue(response.body(), WeatherHistory[].class);
        System.out.println("weatherHistories:"+ Arrays.toString(weatherHistories));

        return weatherHistories;
    }
}
