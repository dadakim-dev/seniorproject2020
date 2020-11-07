package com.dahee8kim.monitoring.domain.osm;

import java.util.ArrayList;

public class Network {
    private String id;
    private ArrayList<NS> NSs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<NS> getNSs() {
        return NSs;
    }

    public void setNSs(ArrayList<NS> NSs) {
        this.NSs = NSs;
    }
}
