package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.Subnet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class SubnetController {
    private String token;
    JSONParser jsonParser = new JSONParser();

    public void setToken(String token) {
        this.token = token;
    }

    public Subnet parseSubnet(String data) {
        Subnet subnet = new Subnet();

        try {
            JSONObject subnet_ = (JSONObject) jsonParser.parse(data);
            subnet.setId(((JSONObject) subnet_).get("id").toString());
            subnet.setName(((JSONObject) subnet_).get("name").toString());
            subnet.setNetworkId(((JSONObject) subnet_).get("network_id").toString());
            subnet.setGatewayIP(((JSONObject) subnet_).get("gateway_ip").toString());
            subnet.setCidr(((JSONObject) subnet_).get("cidr").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return subnet;
    }

    public ArrayList<Subnet> getSubnets() {
        ArrayList<Subnet> subnets = new ArrayList<>();

        String url = "http://13.125.62.208:9696/v2.0/subnets";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray subnets_ = (JSONArray) jsonData.get("subnets");
            for (Object subnet_ : subnets_) {
                subnets.add(parseSubnet(subnet_.toString()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return subnets;
    }
}
