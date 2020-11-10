package com.dahee8kim.monitoring.restAPI.openstack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@RestController
public class RouterAddController {
    private String token;
    private String url = "http://13.125.62.208:9696/v2.0/";

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Boolean createRouter(String name){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.token);

            JSONParser parser = new JSONParser();
            JSONObject rawData = (JSONObject) parser.parse("{\"router\":{\"name\":\""+name+"\"}}");

            HttpEntity<String> request = new HttpEntity<>(rawData.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.url +  "/routers", HttpMethod.POST, request, String.class);
            return Boolean.TRUE;
        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public Boolean addInterface(String routerId, String subnentId){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.token);

            JSONParser parser = new JSONParser();
            JSONObject rawData = (JSONObject) parser.parse("{\"subnet_id\":\""+subnentId+"\"}");

            HttpEntity<String> request = new HttpEntity<>(rawData.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.url+"/routers/" + routerId +"/add_router_interface", HttpMethod.PUT, request, String.class);
            return Boolean.TRUE;
        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public Boolean createSubnet( String subnetName, String networkId, String ipv,
                                 String cidr, String startPool, String endPool){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.token);

            JSONParser parser = new JSONParser();
            JSONObject rawData = (JSONObject) parser.parse("{\"subnet\":{\"name\":\""+subnetName
                    +"\",\"network_id\":\""+networkId + "\", \"ip_version\":\""+ipv+"\", \"cidr\":\""+cidr
                    +"\",\"allocation_pools\":[{\"start\":\""+startPool+"\", \"end\":\""+endPool+"\"}]}}");

            HttpEntity<String> request = new HttpEntity<>(rawData.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.url +  "/subnets", HttpMethod.POST, request, String.class);
            return Boolean.TRUE;
        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }



}
