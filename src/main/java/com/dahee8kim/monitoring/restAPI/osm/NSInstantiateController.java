package com.dahee8kim.monitoring.restAPI.osm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
public class NSInstantiateController {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public Boolean instantiateNS(String name, String vim, String nsd){
        try{
            String url = "http://54.180.94.196:8888/osm/nslcm/v1/ns_instances_content";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            JSONParser parser = new JSONParser();
            JSONObject rawData = (JSONObject) parser.parse("{\"nsName\":\""+name
                    + "\", \"vimAccountId\":\""+vim+"\", \"nsdId\":\""+nsd+"\"}");
            HttpEntity<String> request = new HttpEntity<>(rawData.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            return Boolean.TRUE;

        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
