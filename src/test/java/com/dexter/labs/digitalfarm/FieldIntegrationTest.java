package com.dexter.labs.digitalfarm;

import com.dexter.labs.digitalfarm.client.owm.PolygonClient;
import com.dexter.labs.digitalfarm.client.owm.model.Polygon;
import com.dexter.labs.digitalfarm.entity.Field;
import com.dexter.labs.digitalfarm.repository.FieldRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class FieldIntegrationTest {

    @Container
    public static PostgreSQLContainer<PostgresTestContainer> postgresTestContainer = PostgresTestContainer.getInstance();

    @Value("${polygon.base.url}")
    private String baseUrl;

    @Value("${app.id}")
    private String appId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PolygonClient polygonClient;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void clean(){
        fieldRepository.deleteAll();
    }

    @Order(0)
    @Test
    void isPostgresContainerRunning() {
        assertTrue(postgresTestContainer.isRunning());
    }

    @Test
    public void getFieldInfo_successfully_When_getFieldById() throws Exception {
        String fieldId = createField();
        this.mockMvc.perform(
                get("/fields/"+fieldId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(fieldId));

    }

    @Test
    public void getFieldInfo_throw_FieldNotFoundException_WhenIdDoesNotExist_getFieldById() throws Exception {
        String fieldId = "123";
        this.mockMvc.perform(
                get("/fields/"+fieldId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void createField_successfully_when_request_isValid() throws Exception {
        String request = "{\"name\":\"[Test]Rice Field\",\"countryCode\":\"DEU\",\"geo_json\":{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-5.553604888914691,33.88229680420605],[-5.5516736984239685,33.88229680420605],[-5.5516736984239685,33.88372189858022],[-5.555965232847882,33.88390003370375],[-5.555965232847882,33.88229680420605],[-5.553604888914691,33.88229680420605]]]}}}";
        this.mockMvc.perform(post("/fields")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }

    @Test
    public void createField_throw_Exception_when_request_isNotValid() throws Exception {
        String request = "{\"countryCode\":\"DEU\",\"geo_json\":{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-5.553604888914691,33.88229680420605],[-5.5516736984239685,33.88229680420605],[-5.5516736984239685,33.88372189858022],[-5.555965232847882,33.88390003370375],[-5.555965232847882,33.88229680420605],[-5.553604888914691,33.88229680420605]]]}}}";
        this.mockMvc.perform(post("/fields")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateField_successfully_when_request_isValid() throws Exception{
        String fieldId = createField();
        String request = "{\"name\":\"[Test]Corn Field\",\"countryCode\":\"DEU\",\"geo_json\":{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-5.553604888914691,33.88229680420605],[-5.5516736984239685,33.88229680420605],[-5.5516736984239685,33.88372189858022],[-5.555965232847882,33.88390003370375],[-5.555965232847882,33.88229680420605],[-5.553604888914691,33.88229680420605]]]}}}";
        this.mockMvc.perform(put("/fields/"+fieldId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(fieldId))
                .andExpect(jsonPath("name").value("[Test]Corn Field"));
    }

    @Test
    public void updateField_throwBadRequest_when_request_isNotValid() throws Exception{
        String fieldId = createField();
        String request = "{\"name\":\"[Test]Corn Field\",\"geo_json\":{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-5.553604888914691,33.88229680420605],[-5.5516736984239685,33.88229680420605],[-5.5516736984239685,33.88372189858022],[-5.555965232847882,33.88390003370375],[-5.555965232847882,33.88229680420605],[-5.553604888914691,33.88229680420605]]]}}}";
        this.mockMvc.perform(put("/fields/"+fieldId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateField_throwFieldNotFoundException_when_fieldDoesNotExists() throws Exception{

        String request = "{\"name\":\"[Test]Rice Field\",\"countryCode\":\"DEU\",\"geo_json\":{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-5.553604888914691,33.88229680420605],[-5.5516736984239685,33.88229680420605],[-5.5516736984239685,33.88372189858022],[-5.555965232847882,33.88390003370375],[-5.555965232847882,33.88229680420605],[-5.553604888914691,33.88229680420605]]]}}}";
        this.mockMvc.perform(put("/fields/123")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteField_successfully_when_fieldIdExists() throws Exception {
        String fieldId = createField();
        this.mockMvc.perform(
                delete("/fields/"+fieldId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteField_throwFieldNotFoundException_when_fieldIdNotExists() throws Exception {
        String fieldId = "123";
        this.mockMvc.perform(
                delete("/fields/"+fieldId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    public String createField(){

        String fieldId = "";
        try{
            String request = "{\"name\":\"[Test]Rice Field\",\"countryCode\":\"DEU\",\"geo_json\":{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-5.553604888914691,33.88229680420605],[-5.5516736984239685,33.88229680420605],[-5.5516736984239685,33.88372189858022],[-5.555965232847882,33.88390003370375],[-5.555965232847882,33.88229680420605],[-5.553604888914691,33.88229680420605]]]}}}";
            var httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "?appid=" + appId))
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 201){
                Polygon polygon = objectMapper.readValue(response.body(), Polygon.class);
                Field fieldInput = new Field(
                        polygon.getName(),
                        "DEU",
                        polygon.getId()
                );

                Field field = fieldRepository.save(fieldInput);
                fieldId = field.getId();
            }

        }catch(Exception ex){
            System.out.println("Exception:"+ex.getMessage());
        }
        return fieldId;
    }
}
