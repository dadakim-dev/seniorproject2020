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
    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public ArrayList<NS> getNS(boolean hasVnf) {
        ArrayList<NS> nsArray = new ArrayList<NS>();

        try {
            String url = "http://3.35.26.6:8888/osm/nslcm/v1/ns_instances";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
            JSONArray ns = (JSONArray) parser.parse(response.getBody());

            for (int i = 0; i < ns.size(); i++) {
                NS ns_ = new NS();
                JSONObject n = (JSONObject) parser.parse(ns.get(i).toString());
                ns_.setId(n.get("_id").toString());
                ns_.setName(n.get("name").toString());
                ns_.setDescription(n.get("description").toString());
                ns_.setNsState(n.get("nsState").toString());
                ns_.setOperationalStatus(n.get("operational-status").toString());
                ns_.setConfigStatus(n.get("config-status").toString());
                ns_.setDetailedStatus(n.get("detailed-status").toString());

                if(hasVnf) { // require vnf
                    VNFController vnfController = new VNFController();
                    vnfController.setToken(token);

                    ArrayList<VNF> VNFs = new ArrayList<>();

                    JSONObject deployments = (JSONObject) parser.parse(n.get("deploymentStatus").toString());
                    JSONArray vnfs = (JSONArray) parser.parse(deployments.get("vnfs").toString());

                    for(int j = 0; j < vnfs.size(); j++) {
                        VNFs.add(vnfController.vnfParser(vnfs.get(j).toString()));
                    }
                    ns_.setVNFs(VNFs);
                }

                nsArray.add(ns_);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nsArray;
    }

    public String getNSDetail(String id) {
        try {
            String url = "http://54.180.149.38:8888/osm/nslcm/v1/ns_instances/" + id;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(token);

            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            JSONParser parser = new JSONParser();
//            JSONArray ns = (JSONArray) parser.parse(response.getBody());

//            for (int i = 0; i < ns.size(); i++) {
//                NS ns_ = new NS();
//                JSONObject n = (JSONObject) parser.parse(ns.get(i).toString());
//                ns_.setId(n.get("_id").toString());
//                ns_.setName(n.get("name").toString());
//                ns_.setDescription(n.get("description").toString());
//                ns_.setNsState(n.get("nsState").toString());
//                ns_.setOperationalStatus(n.get("operational-status").toString());
//                ns_.setConfigStatus(n.get("config-status").toString());
//                ns_.setDetailedStatus(n.get("detailed-status").toString());
////                ns_.setErrorDetail(n.get("errorDetail").toString());
//
//                nsArray.add(ns_);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
