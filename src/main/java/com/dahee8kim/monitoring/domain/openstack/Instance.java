package com.dahee8kim.monitoring.domain.openstack;

import java.util.ArrayList;

public class Instance {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getVmStatus() {
        return vmStatus;
    }

    public void setVmStatus(ArrayList<String> vmStatusData) {
        this.vmStatus = vmStatusData;
    }

    public String getFlavorId() {
        return flavorId;
    }

    public void setFlavorId(String flavorId) {
        this.flavorId = flavorId;
    }

    private String id;
    private String name;
    private String status;
    private String flavorId;
    private ArrayList<String> vmStatus;
}
