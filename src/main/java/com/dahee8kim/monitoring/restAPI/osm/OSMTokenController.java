package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.Token;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
public class OSMTokenController {
    public String getToken() {
        try {
            String url = "http://3.35.26.6:8888/osm/admin/v1/tokens";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            JSONParser parser = new JSONParser();
            JSONObject rawData = (JSONObject) parser.parse("{\"username\": \"admin\", \"password\": \"admin\", \"project_id\" : \"4a723640f9fe46e983cd4f46bcc25448\"}");

            HttpEntity<String> request = new HttpEntity<>(rawData.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            String data = response.getBody();
            JSONObject jsonData = (JSONObject) parser.parse(data);

            return jsonData.get("_id").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
