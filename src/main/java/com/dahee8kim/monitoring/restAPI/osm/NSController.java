package com.dahee8kim.monitoring.restAPI.osm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
public class NSController {
    public String getNS(String token) {
        /*
        * curl --location
        * --request GET 'http://3.35.234.229:8888/osm/nslcm/v1/ns_instances'
        * --header 'Content-Type: application/json'
        * --header 'Accept: application/json'
        * --header 'Authorization: Bearer  gAAAAABfli-S7mIA2YIIO_UrZyuWIvVObfD_4Y09HxQ4Hw28H0bey09o4L8N1rw3MPPI5_yklWOhA_AHT8x7hTgggCqAWP9035J1wxdLf_ivvQboo3HjM5-ZBbw5G_sR4Q5hBtKfGyOtjCnl_Y4JgEoNhz5UoBAi6bPjpFV_w4WNlhwuRS0BfAJSAAIecwfLP8KXtJb8CUh-'
        * */

        try {
            String url = "http://3.35.234.229:8888/osm/nslcm/v1/ns_instances";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONArray ns = (JSONArray) parser.parse(response.getBody());
            JSONObject ns1 = (JSONObject) ns.get(0);

            String id = ns1.get("_id").toString();
            String name = ns1.get("name").toString();
            String operationalStatus = ns1.get("operational-status").toString();
            String configStatus = ns1.get("config-status").toString();
            String detailedStatus = ns1.get("detailed-status").toString();

            return detailedStatus;
//            return response.getBody();

//            String data = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
