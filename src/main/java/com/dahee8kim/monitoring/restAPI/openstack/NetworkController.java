package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.Router;
import com.dahee8kim.monitoring.domain.osm.Network;
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
public class NetworkController {
    private String token;
    JSONParser jsonParser = new JSONParser();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Network getNetwork(String data) {
        Network network = new Network();

        try {
            JSONObject network_ = (JSONObject) jsonParser.parse(data);
            network.setId(network_.get("id").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return network;
    }

    public ArrayList<Network> getNetworks() {
        ArrayList<Network> networks = new ArrayList<>();
        String url = "http://3.35.26.6:9696/v2.0/networks";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray networks_ = (JSONArray) jsonParser.parse(jsonObject.get("networks").toString());
            networks_.forEach(networkData -> {
                networks.add(getNetwork(networkData.toString()));
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return networks;
    }

    public ArrayList<Router> getRouters() {
        ArrayList<Router> routers = new ArrayList<>();
        
        return routers;
    }
}

