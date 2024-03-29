package com.dahee8kim.monitoring.domain.openstack;

import java.util.ArrayList;

public class Router {
    private String id;
    private String name;
    private String tenantId;
    private String status;
    private ArrayList<String> externalFixedIps;

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getExternalFixedIps() {
        return externalFixedIps;
    }

    public void setExternalFixedIps(ArrayList<String> externalFixedIps) {
        this.externalFixedIps = externalFixedIps;
    }
}
