package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.NSDescriptor;
import com.dahee8kim.monitoring.domain.osm.VIM;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class NSDescriptorController {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public ArrayList<NSDescriptor> getNSDs(){
        String url = "http://54.180.94.196:8888/osm//nsd/v1/ns_descriptors";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        ArrayList<NSDescriptor> nsdArray = new ArrayList<NSDescriptor>();
        JSONParser parser = new JSONParser();

        try{
            JSONArray nsds = (JSONArray) parser.parse(response.getBody());
            for(int i = 0; i < nsds.size(); i++ ){
                NSDescriptor nsd = new NSDescriptor();
                JSONObject data = (JSONObject) parser.parse(nsds.get(i).toString());
                nsd.setId(data.get("_id").toString());
                nsd.setDescription(data.get("description").toString());
                nsd.setName(data.get("name").toString());
                nsdArray.add(nsd);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return nsdArray;

    }
}
