package com.dahee8kim.monitoring.restAPI.openstack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@RestController
public class VMInstanceController {
    private String url = "http://3.35.234.229:8778/resource_providers";
    private String projectID = "e016b0f2-e332-432c-97f6-41624773b944";

    public String getVMInstance(String token) {
        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            headers.set("X-Auth-Token", token);
//
//            HttpEntity<String> request = new HttpEntity<>(headers);
//            RestTemplate restTemplate = new RestTemplate();

//            JSONParser jsonParser = new JSONParser();
//            JSONArray resourceProvider = (JSONArray) jsonParser.parse(response.getBody().toString());

//            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
