package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.NS;
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
public class NSController {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public NS parseNS(String text) {
        NS ns = new NS();
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonData = (JSONObject) parser.parse(text);

            // get ns id
            ns.setId(jsonData.get("_id").toString());
            ns.setName(jsonData.get("name").toString());
            ns.setDescription(jsonData.get("description").toString());
            ns.setNsState(jsonData.get("nsState").toString());
            ns.setOperationalStatus(jsonData.get("operational-status").toString());
            ns.setConfigStatus(jsonData.get("config-status").toString());
            ns.setDetailedStatus(jsonData.get("detailed-status").toString());
            ns.setVnfIds(new ArrayList<String>());

            // get vim net id
            JSONObject deploy = (JSONObject) jsonData.get("deploymentStatus");
            JSONArray nets = (JSONArray) deploy.get("nets");
            JSONObject net = (JSONObject) nets.get(0);
            ns.setVimNetId(net.get("vim_net_id").toString());

            JSONArray vnfrRefs = (JSONArray) parser.parse(jsonData.get("constituent-vnfr-ref").toString());
            JSONArray vnfs = (JSONArray) parser.parse(deploy.get("vnfs").toString());
            vnfrRefs.forEach(vnfrRef -> ns.addVnfIds(vnfrRef.toString()));
        } catch (NullPointerException e) {
            ns.setVimNetId(".");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ns;
    }

    public ArrayList<NS> getNSs() {
        ArrayList<NS> nsArray = new ArrayList<NS>();
        String url = "http://13.125.62.208:8888/osm/nslcm/v1/ns_instances";

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
            for (int i = 0; i < ns.size(); i++) {
                nsArray.add(parseNS(ns.get(i).toString()));
            }
        } catch (Exception e) {}

        return nsArray;
    }

    public Boolean deleteNS(String id, String status){
        try{
            String url = "http://13.125.62.208:8888/osm/nslcm/v1/ns_instances/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            JSONParser parser = new JSONParser();
            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            System.out.println(request.toString());

            if(status.equals("READY")){
                ResponseEntity<String> response = restTemplate.exchange(url+id+"/terminate", HttpMethod.POST, request, String.class);
            }
            else {
                ResponseEntity<String> response = restTemplate.exchange(url+id, HttpMethod.DELETE, request, String.class);
            }
            return Boolean.TRUE;

        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

}
