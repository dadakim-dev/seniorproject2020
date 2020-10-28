package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.Instance;
import com.dahee8kim.monitoring.restAPI.prometheus.VMStatusController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class VMInstanceController {
    private String token;
    private String url = "http://54.180.149.38:8774/v2.1/servers";
    private ArrayList<Instance> instance = new ArrayList<>();
    private VMStatusController vmStatusController = new VMStatusController();

    public ArrayList<Instance> getInstance() {
        this.getVMInstance();
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void getVMInstance() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.url, HttpMethod.GET, request, String.class);

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody().toString());
            JSONArray servers = (JSONArray) jsonData.get("servers");

            for(int i = 0; i < servers.size(); i++) {
                Instance instance_ = new Instance();
                JSONObject server_ = (JSONObject) servers.get(i);
                instance_.setId(server_.get("id").toString());
                instance_.setName(server_.get("name").toString());
                instance_.setStatus(getVMInstanceDetail(instance_.getId()));
                instance_.setVmStatusData(vmStatusController.getVMStatusDataSet(instance_.getId()));
                this.instance.add(instance_);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getVMInstanceDetail(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.url + "/" + id, HttpMethod.GET, request, String.class);

        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody().toString());
            JSONObject server = (JSONObject) jsonData.get("server");
            return server.get("status").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
