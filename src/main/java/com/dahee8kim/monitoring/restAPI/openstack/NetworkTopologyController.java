package com.dahee8kim.monitoring.restAPI.openstack;


import com.dahee8kim.monitoring.domain.openstack.NetworkList;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class NetworkTopologyController {
    private String token;
    private String url = "http://3.35.26.6:9696/v2.0/networks";

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<NetworkList> GetNetworkList(){

        ArrayList<NetworkList> networkListArray = new ArrayList<NetworkList>();
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONArray networks = (JSONArray) parser.parse(response.getBody());

            for(int i=0;i<networks.size();i++){
                NetworkList nlist = new NetworkList();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return networkListArray;


    }




}

