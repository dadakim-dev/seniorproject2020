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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class VMInstanceController {
    private String token;
    private String url = "http://13.125.62.208:8774/v2.1/servers";
    private ArrayList<Instance> instance = new ArrayList<>();
    private VMStatusController vmStatusController = new VMStatusController();
    private JSONParser jsonParser = new JSONParser();

    public ArrayList<Instance> getInstance() {
        this.getVMInstances();
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void getVMInstances() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.url, HttpMethod.GET, request, String.class);

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray servers = (JSONArray) jsonData.get("servers");

            String startTime = String.format("%.3f", (System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 9)) * 0.001);
            String endTime = String.format("%.3f", System.currentTimeMillis() * 0.001);

            for(int i = 0; i < servers.size(); i++) {
                Instance instance_ = new Instance();
                JSONObject server_ = (JSONObject) servers.get(i);
                instance_.setId(server_.get("id").toString());
                instance_.setName(server_.get("name").toString());
                instance_.setStatus(getVMInstanceDetail(instance_.getId()));
                instance_.setVmStatus(
                        vmStatusController.getVMStatus(instance_.getId(), startTime, endTime, "30s")
                );
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

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject server = (JSONObject) jsonData.get("server");
            return server.get("status").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public Instance getVMInstance(String id) {
        Instance instance = new Instance();
        if(id == null) return instance;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.url + "/" + id, HttpMethod.GET, request, String.class);

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject server = (JSONObject) jsonParser.parse(jsonData.get("server").toString());
            JSONObject flavor = (JSONObject) jsonParser.parse(server.get("flavor").toString());

            instance.setId(server.get("id").toString());
            instance.setFlavorId(flavor.get("id").toString());

            String startTime = String.format("%.3f", (System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 9)) * 0.001);
            String endTime = String.format("%.3f", System.currentTimeMillis() * 0.001);
            instance.setVmStatus(
                    vmStatusController.getVMStatus(instance.getId(), startTime, endTime, "30s")
            );
        } catch (Exception e) {
        }

        return instance;
    }
}
