package com.dahee8kim.monitoring.restAPI.osm;

import com.dahee8kim.monitoring.domain.osm.VNF;

import java.util.ArrayList;

public class VNFController {
    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    ArrayList<VNF> getVNFList() {
        ArrayList<VNF> vnfList = new ArrayList<>();

        return vnfList;
    }
}
