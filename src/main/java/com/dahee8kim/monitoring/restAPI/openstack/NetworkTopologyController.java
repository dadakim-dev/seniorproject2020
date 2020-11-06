package com.dahee8kim.monitoring.restAPI.openstack;


import com.dahee8kim.monitoring.domain.openstack.NetworkInterface;
import com.dahee8kim.monitoring.domain.openstack.NetworkList;
import com.dahee8kim.monitoring.domain.openstack.Routers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class NetworkTopologyController {
    private String token;
    private String url = "http://3.35.26.6:9696/v2.0/";

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    private ArrayList<NetworkList> netslist = new ArrayList<>();
    private ArrayList<Routers> routerslist = new ArrayList<>();
    private ArrayList<NetworkInterface> interfacelist = new ArrayList<>();

    public ArrayList<NetworkList> getNetworkList(){
        this.getNetworks();
        return netslist;
    }
    public ArrayList<Routers> getRouterslist(){
        this.getRouters();
        return routerslist;
    }
    public ArrayList<NetworkInterface> getInterfacelist(){
        this.getInterfaces();
        return interfacelist;
    }

    public void getNetworks(){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.url +  "/networks", HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(response.getBody());
            JSONArray networks = (JSONArray) parser.parse(data.get("networks").toString());

            for(int i = 0; i < networks.size(); i++){
                NetworkList net = new NetworkList();
                JSONObject net_data = (JSONObject) networks.get(i);
                net.setId(net_data.get("id").toString());
                net.setName(net_data.get("name").toString());
                net.setStatus(net_data.get("status").toString());
                net.setShared(net_data.get("shared").toString());
                net.setTenant_id(net_data.get("tenant_id").toString());
                JSONArray subnet = (JSONArray) parser.parse(net_data.get("subnets").toString());
                net.setSubnets(subnet);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getRouters(){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.url +  "/routers", HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(response.getBody());
            JSONArray routers = (JSONArray) parser.parse(data.get("routers").toString());

            for(int i = 0; i < routers.size(); i++){
                Routers route = new Routers();
                JSONObject net_data = (JSONObject) routers.get(i);
                route.setId(net_data.get("id").toString());
                route.setName(net_data.get("name").toString());
                route.setStatus(net_data.get("status").toString());
                route.setTenant_id(net_data.get("tenant_id").toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getInterfaces(){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(this.url +  "/ports", HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(response.getBody());
            JSONArray ports = (JSONArray) parser.parse(data.get("ports").toString());

            for(int i = 0; i < ports.size(); i++){
                NetworkInterface port = new NetworkInterface();
                JSONObject net_data = (JSONObject) ports.get(i);
                port.setId(net_data.get("id").toString());
                port.setName(net_data.get("name").toString());
                port.setStatus(net_data.get("status").toString());
                port.setTenant_id(net_data.get("tenant_id").toString());
                port.setDevice_id(net_data.get("device_id").toString());
                port.setNetwork_id(net_data.get("network_id").toString());

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}

