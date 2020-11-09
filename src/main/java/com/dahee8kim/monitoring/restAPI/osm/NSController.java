package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.osm.VNF;
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
//            ns.setName(ns_.get("name").toString());
//            ns.setDescription(ns_.get("description").toString());
//            ns.setNsState(ns_.get("nsState").toString());
//            ns.setOperationalStatus(ns_.get("operational-status").toString());
//            ns.setConfigStatus(ns_.get("config-status").toString());
//            ns.setDescription(ns_.get("detailed-status").toString());

            // get vim net id
            JSONObject deploy = (JSONObject) parser.parse(jsonData.get("deploymentStatus").toString());
            JSONArray nets = (JSONArray) parser.parse(deploy.get("nets").toString());
            JSONObject net = (JSONObject) parser.parse(nets.get(0).toString());
            ns.setVimNetId(net.get("vim_net_id").toString());

            JSONArray vnfrRefs = (JSONArray) parser.parse(jsonData.get("constituent-vnfr-ref").toString());
            JSONArray vnfs = (JSONArray) parser.parse(deploy.get("vnfs").toString());
            vnfrRefs.forEach(vnfrRef -> ns.addVnfIds(vnfrRef.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ns;
    }

    public ArrayList<NS> getNS() {
        ArrayList<NS> nsArray = new ArrayList<NS>();
        String url = "http://3.35.26.6:8888/osm/nslcm/v1/ns_instances";

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return nsArray;
    }
}
