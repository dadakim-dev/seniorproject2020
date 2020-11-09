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

    public ArrayList<String> getVmStatusData() {
        return vmStatusData;
    }

    public void setVmStatusData(ArrayList<String> vmStatusData) {
        this.vmStatusData = vmStatusData;
    }

    private String id;
    private String name;
    private String status;

    private ArrayList<String> vmStatusData = new ArrayList<>();
}
