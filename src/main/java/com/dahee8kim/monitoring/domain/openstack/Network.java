package com.dahee8kim.monitoring.domain.openstack;

import com.dahee8kim.monitoring.domain.osm.NS;
import java.util.ArrayList;

public class Network {
    private String id;
    private String name;
    private ArrayList<NS> NSs;
    private String status;
    private ArrayList<Subnet> subnets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<NS> getNSs() {
        return NSs;
    }

    public void setNSs(ArrayList<NS> NSs) {
        this.NSs = NSs;
    }

    public ArrayList<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(ArrayList<Subnet> subnets) {
        this.subnets = subnets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
