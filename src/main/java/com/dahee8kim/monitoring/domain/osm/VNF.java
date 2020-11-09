package com.dahee8kim.monitoring.domain.osm;

import com.dahee8kim.monitoring.domain.openstack.Flavor;

import java.util.ArrayList;

public class VNF {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVimId() {
        return vimId;
    }

    public void setVimId(String vimId) {
        this.vimId = vimId;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getVmStatus() {
        return vmStatus;
    }

    public void setVmStatus(ArrayList<String> vmStatus) {
        this.vmStatus = vmStatus;
    }

    private String id;
    private String name;
    private String vimId;
    private Flavor flavor;
    private ArrayList<String> vmStatus;
}
