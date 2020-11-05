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
    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public ArrayList<NS> getNS() {
        /*
        * curl --location
        * --request GET 'http://3.35.234.229:8888/osm/nslcm/v1/ns_instances'
        * --header 'Content-Type: application/json'
        * --header 'Accept: application/json'
        * --header 'Authorization: Bearer  gAAAAABfli-S7mIA2YIIO_UrZyuWIvVObfD_4Y09HxQ4Hw28H0bey09o4L8N1rw3MPPI5_yklWOhA_AHT8x7hTgggCqAWP9035J1wxdLf_ivvQboo3HjM5-ZBbw5G_sR4Q5hBtKfGyOtjCnl_Y4JgEoNhz5UoBAi6bPjpFV_w4WNlhwuRS0BfAJSAAIecwfLP8KXtJb8CUh-'
        * */

        ArrayList<NS> nsArray = new ArrayList<NS>();

        try {
            String url = "http://3.35.26.6:8888/osm/nslcm/v1/ns_instances";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONArray ns = (JSONArray) parser.parse(response.getBody());

            for (int i = 0; i < ns.size(); i++) {
                NS ns_ = new NS();
                JSONObject n = (JSONObject) parser.parse(ns.get(i).toString());
                ns_.setId(n.get("_id").toString());
                ns_.setName(n.get("name").toString());
                ns_.setDescription(n.get("description").toString());
                ns_.setNsState(n.get("nsState").toString());
                ns_.setOperationalStatus(n.get("operational-status").toString());
                ns_.setConfigStatus(n.get("config-status").toString());
                ns_.setDetailedStatus(n.get("detailed-status").toString());
//                ns_.setErrorDetail(n.get("errorDetail").toString());

                nsArray.add(ns_);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nsArray;
    }

    public String getNSDetail(String id) {
        try {
            String url = "http://3.35.26.6:8888/osm/nslcm/v1/ns_instances/" + id;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            System.out.println(response.getBody().toString());

            JSONParser parser = new JSONParser();
//            JSONArray ns = (JSONArray) parser.parse(response.getBody());

//            for (int i = 0; i < ns.size(); i++) {
//                NS ns_ = new NS();
//                JSONObject n = (JSONObject) parser.parse(ns.get(i).toString());
//                ns_.setId(n.get("_id").toString());
//                ns_.setName(n.get("name").toString());
//                ns_.setDescription(n.get("description").toString());
//                ns_.setNsState(n.get("nsState").toString());
//                ns_.setOperationalStatus(n.get("operational-status").toString());
//                ns_.setConfigStatus(n.get("config-status").toString());
//                ns_.setDetailedStatus(n.get("detailed-status").toString());
////                ns_.setErrorDetail(n.get("errorDetail").toString());
//
//                nsArray.add(ns_);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
