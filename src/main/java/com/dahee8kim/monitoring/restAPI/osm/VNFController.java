package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.VNF;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

        JSONParser jsonParser = new JSONParser();
        try {
            // set VNF ID
            JSONObject jsonData = (JSONObject) jsonParser.parse(text);
            vnf.setId(jsonData.get("id").toString());

            // set VIM ID (OpenStack VNF ID)
            JSONArray vdurs = (JSONArray) jsonData.get("vdur");
            JSONObject vdur = (JSONObject) vdurs.get(0);
            vnf.setName(vdur.get("name").toString());
            vnf.setVimId(vdur.get("vim-id").toString());
        } catch (Exception e) {
        }

        return vnf;
    }

    public ArrayList<VNF> getVNFs() {
        ArrayList<VNF> VNFs = new ArrayList<>();

        String url = "http://13.125.62.208:8888/osm/nslcm/v1/vnf_instances";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        JSONParser parser = new JSONParser();

        try {
            JSONArray vnf = (JSONArray) parser.parse(response.getBody());
            vnf.forEach(v -> {
                VNFs.add(parseVNF(v.toString()));
            });
        } catch (Exception e) {
        }

        return VNFs;
    }

    public VNF getVNF(String osmId) {
        String url = "http://13.125.62.208:8888/osm/nslcm/v1/vnf_instances/" + osmId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return parseVNF(response.getBody());
    }
}
