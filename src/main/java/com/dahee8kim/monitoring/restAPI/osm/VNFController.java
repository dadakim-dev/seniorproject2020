package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.VNF;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

public class VNFController {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public VNF parseVNF(String text) {
        VNF vnf = new VNF();
        return vnf;
    }

    public ArrayList<VNF> getVNFs() {
        ArrayList<VNF> VNFs = new ArrayList<>();

        String url = "http://3.35.26.6:8888/osm/nslcm/v1/vnf_instances";

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
            System.out.println(response.getBody());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return VNFs;
    }
}
