package com.example.demo.rest;


import com.example.demo.dto.SingleUserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Disabled
public class RESTTest {

    private static final Logger logger = LogManager.getLogger(RESTTest.class);

    @Test
    public void testGetResponse() {
        try {
            HttpUriRequest request = new HttpGet("https://reqres.in/api/users/2");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            logger.info("Completed get request");
            ObjectMapper mapper = new ObjectMapper();
            // turn response into string that maps to a node
            JsonNode node = mapper.readTree(EntityUtils.toString(response.getEntity()));
            // find data field
            JsonNode dataNode = node.at("/data");
            SingleUserDTO user = mapper.treeToValue(dataNode, SingleUserDTO.class);

            Assertions.assertEquals("Janet", user.getFirst_name());
            Assertions.assertNotEquals("Pomojasjkhd0", user.getEmail());
        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Test
    public void testPostResponse() {
        try {
            HttpPost request = new HttpPost("https://reqres.in/api/users/");
            Map<String, String> params = new HashMap<>();
            params.put("name", "Rocky");
            params.put("job", "accountant");
            String json = new Gson().toJson(params);
            logger.info("Prepared json: " + json);
            request.setEntity(new StringEntity(json));
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            logger.info("Completed post request");
            Assertions.assertEquals(201, response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Test
    public void testDeleteResponse() {
        try {
            HttpDelete request = new HttpDelete("https://reqres.in/api/users/2");
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            Assertions.assertEquals(204, response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
