package com.dexter.labs.digitalfarm.client.owm;

import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.dto.BoundaryRequestDto;
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

import static java.net.http.HttpResponse.*;

@Component
public class PolygonClient {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${app.id}")
    private String appId;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    public Polygon getPolygonById(String polygonId) throws ClientException, IOException, InterruptedException {

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("/")
                .append(polygonId)
                .append("?appid=")
                .append(appId);

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .build();

        HttpResponse<String> response =
                httpClient.send(httpRequest, BodyHandlers.ofString());

        if(response.statusCode() != 200) throw new ClientException(response.toString());

        Polygon polygon = objectMapper.readValue(response.body(), Polygon.class);

        System.out.println("Polygon:"+polygon);

        return polygon;

    }

    public Polygon createPolygon(BoundaryRequestDto boundaryRequestDto) throws ClientException, IOException, InterruptedException {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?appid=").append(appId);

        String request = objectMapper.writeValueAsString(boundaryRequestDto);

        System.out.println("Request:"+request);

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response =
                httpClient.send(httpRequest, BodyHandlers.ofString());

        System.out.println("Response:"+response);

        if(response.statusCode() != 200 || response.statusCode() != 201) throw new ClientException(response.toString());

        Polygon polygon = objectMapper.readValue(response.body(), Polygon.class);

        System.out.println("Polygon:"+polygon);
        return polygon;
    }
}
