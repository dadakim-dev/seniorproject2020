package com.dahee8kim.monitoring.restAPI.openstack;

import com.dahee8kim.monitoring.domain.openstack.Router;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

public class RouterController {
    public void setToken(String token) {
        this.token = token;
    }
    private String token;
    private JSONParser jsonParser = new JSONParser();

    public Router parseRouter(JSONObject data) {
        Router router = new Router();

        JSONObject externalGatewayInfo = (JSONObject) data.get("external_gateway_info");
        JSONArray externalFixedIps_ = (JSONArray) externalGatewayInfo.get("external_fixed_ips");

        router.setId(data.get("id").toString());
        router.setTenantId(data.get("tenant_id").toString());
        router.setName(data.get("name").toString());
        router.setStatus(data.get("status").toString());

        ArrayList<String> externalFixedIps = new ArrayList<>();
        externalFixedIps_.forEach(externalFixedIp ->
                externalFixedIps.add(
                        ((JSONObject) externalFixedIp).get("ip_address").toString()
                )
        );

        router.setExternalFixedIps(externalFixedIps);

        return router;
    }

    public ArrayList<Router> getRouters() {
        String url = "http://54.180.94.196:9696/v2.0/routers";
        ArrayList<Router> routers = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Auth-Token", token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        System.out.println(response.getBody());

        try {
            JSONObject jsonData = (JSONObject) jsonParser.parse(response.getBody());
            JSONArray routers_ = (JSONArray) jsonData.get("routers");

            for (Object router_ : routers_)
                routers.add(parseRouter((JSONObject) router_));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return routers;
    }
}
