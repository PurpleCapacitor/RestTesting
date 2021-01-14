package com.example.demo.restassured;


import com.example.demo.dto.SingleUserDTO;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class RESTTest {

    @Test
    public void testGetResponse() throws IOException {
        HttpUriRequest request = new HttpGet("https://reqres.in/api/users/2");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        ObjectMapper mapper = new ObjectMapper();
        // turn response into string that maps to a node
        JsonNode node = mapper.readTree(EntityUtils.toString(response.getEntity()));
        // find data field
        JsonNode dataNode = node.at("/data");
        SingleUserDTO user = mapper.treeToValue(dataNode, SingleUserDTO.class);

        Assertions.assertEquals("Janet", user.getFirst_name());
        Assertions.assertNotEquals("Pomojasjkhd0", user.getEmail());
    }
}
