package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.VIM;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

public class VIMController {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public ArrayList<VIM> getVIMs(){
        String url = "http://54.180.94.196:8888/osm/admin/v1/vim_accounts/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        ArrayList<VIM> vimArray = new ArrayList<VIM>();
        JSONParser parser = new JSONParser();

        try{
            JSONArray vims = (JSONArray) parser.parse(response.getBody());
            for(int i = 0; i < vims.size(); i++ ){
                VIM vim = new VIM();
                JSONObject data = (JSONObject) parser.parse(vims.get(i).toString());
                vim.setId(data.get("_id").toString());
                vim.setVim_type(data.get("vim_type").toString());
                vim.setName(data.get("name").toString());
                vimArray.add(vim);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        return vimArray;

    }

}
