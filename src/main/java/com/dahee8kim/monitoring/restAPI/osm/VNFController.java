package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.VNF;
import com.dahee8kim.monitoring.restAPI.openstack.FlavorController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

// curl http://3.35.26.6:8774/v2.1/flavors/bab2897f-aa51-4912-9c0c-0a9aad49c4e1 --header "X-
//Auth-Token: $OS_TOKEN"
public class VNFController {
    public void setToken(String token) {
        this.token = token;
    }

    private String token;

//    public ArrayList<VNF> getVNFList() {
//        ArrayList<VNF> vnfList = new ArrayList<>();
//        String url = "http://3.35.26.6:8888/osm/nslcm/v1/vnf_instances";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setBearerAuth(token);
//
//        HttpEntity<String> request = new HttpEntity<>(headers);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//        System.out.println(response.getBody().toString());
//
//        JSONParser jsonParser = new JSONParser();
//        try {
//            JSONArray vnfArray = (JSONArray) jsonParser.parse(response.getBody().toString());
//            for(int i = 0; i < vnfArray.size(); i++) {
//                JSONObject vnfObject = (JSONObject) jsonParser.parse(vnfArray.get(i).toString());
//
//                VNF vnf = new VNF();
//
//                vnf.setId(vnfObject.get("id").toString());
//                vnf.setName(vnfObject.get("name").toString());
//                vnf.setVimId(vnfObject.get("vim-id").toString());
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return vnfList;
//    }

    public VNF vnfParser(String text) {
        FlavorController flavorController = new FlavorController(token);
        VNF vnf = new VNF();

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(text);
            vnf.setId(jsonObject.get("vnf_id").toString());
            vnf.setName(jsonObject.get("vnf_name").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vnf;
    }
}
