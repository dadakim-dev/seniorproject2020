package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.NetworkInterface;
import com.dahee8kim.monitoring.domain.openstack.Router;
import com.dahee8kim.monitoring.domain.openstack.Network;
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
    private JSONParser jsonParser = new JSONParser();
    private String url = "http://54.180.94.196:9696/v2.0/";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Network parseNetwork(String data) {
        Network network = new Network();

        try {
            JSONObject network_ = (JSONObject) jsonParser.parse(data);
            network.setId(network_.get("id").toString());
            network.setName(network_.get("name").toString());
            network.setStatus(network_.get("status").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return network;
    }

    public ArrayList<Network> getNetworks() {
        ArrayList<Network> networks = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url + "networks", HttpMethod.GET, request, String.class);
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray networks_ = (JSONArray) jsonParser.parse(jsonObject.get("networks").toString());
            networks_.forEach(networkData -> {
                networks.add(parseNetwork(networkData.toString()));
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return networks;
    }

    public Router parseRouter(String data) {
        Router router = new Router();

        try {
            JSONObject router_ = (JSONObject) jsonParser.parse(data);
            router.setId(router_.get("id").toString());
            router.setName(router_.get("name").toString());
            router.setStatus(router_.get("status").toString());
            router.setTenantId(router_.get("tenant_id").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return router;
    }

    public ArrayList<Router> getRouters() {
        ArrayList<Router> routers = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url +  "routers", HttpMethod.GET, request, String.class);

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray routers_ = (JSONArray) jsonParser.parse(jsonData.get("routers").toString());
            routers_.forEach(routerData -> {
                routers.add(parseRouter(routerData.toString()));
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return routers;
    }

    public NetworkInterface parseNetworkInterface(String data) {
        NetworkInterface networkInterface = new NetworkInterface();

        try {
            JSONObject net = (JSONObject) jsonParser.parse(data);
            networkInterface.setId(net.get("id").toString());
            networkInterface.setName(net.get("name").toString());
            networkInterface.setStatus(net.get("status").toString());
            networkInterface.setTenantId(net.get("tenant_id").toString());
            networkInterface.setDeviceId(net.get("device_id").toString());
            networkInterface.setNetworkId(net.get("network_id").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return networkInterface;
    }

    public ArrayList<NetworkInterface> getNetworkInterfaces() {
        ArrayList<NetworkInterface> networkInterfaces = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url +  "ports", HttpMethod.GET, request, String.class);

        JSONObject jsonData = null;
        try {
            jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray ports = (JSONArray) jsonParser.parse(jsonData.get("ports").toString());
            ports.forEach(portData -> {
                networkInterfaces.add(parseNetworkInterface(portData.toString()));
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return networkInterfaces;
    }
}

