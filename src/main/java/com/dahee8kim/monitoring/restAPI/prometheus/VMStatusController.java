package com.dahee8kim.monitoring.restAPI.prometheus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class VMStatusController {
    private JSONParser jsonParser = new JSONParser();

    public ArrayList<String> getVMStatus(String id, String start, String end, String step) {
        ArrayList<String> VMStatus = new ArrayList<>();
        String url = "http://13.125.62.208:9090/api/v1/query_range?query=osm_vm_status%7B" +
                "resource_uuid%3D%22" + id
                + "%22%7D&start=" + start
                + "&end=" + end
                + "&step=" + step;

        URI uri = null;
        try {
            uri = new URI(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(uri , HttpMethod.GET, request, String.class);

            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject data = (JSONObject) jsonParser.parse(jsonData.get("data").toString());
            JSONArray results = (JSONArray) jsonParser.parse(data.get("result").toString());
            JSONObject result = (JSONObject) jsonParser.parse(results.get(0).toString());
            JSONArray values = (JSONArray) jsonParser.parse(result.get("values").toString());

            values.forEach(value -> {
                try {
                    JSONArray value_ = (JSONArray) jsonParser.parse(value.toString());
                    VMStatus.add(value_.get(1).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
        }

        return VMStatus;
    }
}
