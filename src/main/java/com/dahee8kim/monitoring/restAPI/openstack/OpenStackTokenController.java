package com.dahee8kim.monitoring.restAPI.openstack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@RestController
public class OpenStackTokenController {
    public String getToken() {
        try {
            String url = "http://3.35.234.229:5000/v3/auth/tokens?nocatalog";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            JSONParser parser = new JSONParser();
            JSONObject rawData = (JSONObject) parser.parse("{\"auth\": {\"identity\":{ \"methods\" : [\"password\"],\"password\":{\"user\":{\"domain\":{\"name\":\"default\"},\"name\":\"admin\", \"password\":\"TpvPPbx8ECOZNyZWZFzJ0GptJd77byHR\"}}},\"scope\":{\"project\":{ \"domain\":{\"name\":\"default\"}, \"name\":\"admin\"}}}}");
            HttpEntity<String> request = new HttpEntity<>(rawData.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return Objects.requireNonNull(response.getHeaders().get("X-Subject-Token").get(0)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
