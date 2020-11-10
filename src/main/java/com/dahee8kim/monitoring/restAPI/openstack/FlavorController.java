package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.Flavor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class FlavorController {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public Flavor getFlavor(String flavorId) {
        Flavor flavor = new Flavor();
        if(flavorId == null) return flavor;

        String url = "http://13.125.62.208:8774/v2.1/flavors/" + flavorId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return parseFlavor(response.getBody());
    }

    public Flavor parseFlavor(String text) {
        Flavor flavor = new Flavor();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(text);
            JSONObject flavor_ = (JSONObject) jsonParser.parse(jsonData.get("flavor").toString());
            flavor.setDisk(Integer.parseInt(flavor_.get("disk").toString()));
            flavor.setMemory(Integer.parseInt(flavor_.get("ram").toString()));
            flavor.setVcpu(Integer.parseInt(flavor_.get("vcpus").toString()));
            flavor.setId(flavor_.get("id").toString());
            flavor.setName(flavor_.get("name").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flavor;
    }
}
