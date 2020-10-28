package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.Resource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
public class ResourceController {
    private String token;
    private String url = "http://54.180.149.38:8778/resource_providers";
    private String projectID = "97852b05-1c06-41b7-bcb5-8c4bb5c41f26";

    private Resource resource = new Resource();

    public Resource getResource() {
        this.getUsages();
        this.getInventories();
        return resource;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void getUsages() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.url + "/" + this.projectID + "/usages", HttpMethod.GET, request, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonData = null;
        try {
            jsonData = (JSONObject) jsonParser.parse(response.getBody().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject usages = (JSONObject) jsonData.get("usages");

        this.resource.setUsedDisk(Integer.parseInt(usages.get("DISK_GB").toString()));
        this.resource.setUsedMemory(Integer.parseInt(usages.get("MEMORY_MB").toString()));
        this.resource.setUsedVCPU(Integer.parseInt(usages.get("VCPU").toString()));
    }

    public void getInventories() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", this.token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(this.url + "/" + this.projectID + "/inventories", HttpMethod.GET, request, String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonData = null;

        try {
            jsonData = (JSONObject) jsonParser.parse(response.getBody().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject inventories = (JSONObject) jsonData.get("inventories");

        JSONObject vcpu = (JSONObject) inventories.get("VCPU");
        JSONObject memory = (JSONObject) inventories.get("MEMORY_MB");
        JSONObject disk = (JSONObject) inventories.get("DISK_GB");

        this.resource.setTotalDisk(Integer.parseInt(disk.get("total").toString()));
        this.resource.setTotalMemory(Integer.parseInt(memory.get("total").toString()));
        this.resource.setTotalVCPU(Integer.parseInt(vcpu.get("total").toString()));
    }
}
