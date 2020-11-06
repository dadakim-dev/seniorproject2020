package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.NS;
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
public class NSController {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public NS parseNS(String text) {
        NS ns = new NS();
        JSONParser parser = new JSONParser();

        try {
            JSONObject ns_ = (JSONObject) parser.parse(text);
            ns.setId(ns_.get("_id").toString());
            ns.setName(ns_.get("name").toString());
            ns.setDescription(ns_.get("description").toString());
            ns.setNsState(ns_.get("nsState").toString());
            ns.setOperationalStatus(ns_.get("operational-status").toString());
            ns.setConfigStatus(ns_.get("config-status").toString());
            ns.setDescription(ns_.get("detailed_status").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ns;
    }

    public ArrayList<NS> getNS() {
        ArrayList<NS> nsArray = new ArrayList<NS>();

        String url = "http://15.164.103.73:8888/osm/nslcm/v1/ns_instances";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        JSONParser parser = new JSONParser();
        try {
            JSONArray ns = (JSONArray) parser.parse(response.getBody());
            for (int i = 0; i < ns.size(); i++) {
                nsArray.add(parseNS(ns.get(i).toString()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return nsArray;
    }

//    public String getNSDetail(String id) {
//        try {
//            String url = "http://54.180.149.38:8888/osm/nslcm/v1/ns_instances/" + id;
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            headers.setBearerAuth(token);
//
//            HttpEntity<String> request = new HttpEntity<>(headers);
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            JSONParser parser = new JSONParser();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }
}
