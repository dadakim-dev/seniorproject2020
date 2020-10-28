package com.dahee8kim.monitoring.restAPI.prometheus;

import com.dahee8kim.monitoring.domain.openstack.Instance;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class VMStatusController {
    public ArrayList<String> getVMStatusDataSet(String id) {
        ArrayList<Double> times = new ArrayList<>();
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 9)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 8)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 7)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 6)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 5)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 4)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 3)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 2)) * 0.001);
        times.add((System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(30 * 1)) * 0.001);
        times.add(System.currentTimeMillis() * 0.001);

        ArrayList<String> dataSet = new ArrayList<>();

        for(int i = 0; i < times.size(); i++) {
            dataSet.add(getVMStatus(id, String.format("%.3f", times.get(i))));
        }

        return dataSet;
    }

    public String getVMStatus(String id, String time) {
        String url = "http://54.180.149.38:9090/api/v1/query?query=osm_vm_status%7Binstance%3D%27mon-k8s%3A8000%27%2Cjob%3D%27prometheus%27%2Cresource_uuid%3D%27"+id+"%27%7D&time=" + time;
        URI uri = null;
        try {
            uri = new URI(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(uri , HttpMethod.GET, request, String.class);

            JSONParser jsonParser = new JSONParser();
            JSONObject data = (JSONObject) jsonParser.parse(response.getBody().toString());
            JSONObject data_ = (JSONObject) data.get("data");
            JSONArray result = (JSONArray) data_.get("result");
            JSONObject value = (JSONObject) result.get(0);
            JSONArray value_ = (JSONArray) value.get("value");

            return value_.get(1).toString();
        } catch (Exception e) {
        }

        return "";
    }
}
