package com.dahee8kim.monitoring.domain.openstack;

public class NetworkInterface {
    private String id;
    private String name;
    private String network_id;
    private String tenant_id;
    private String status;
    private String device_id;
    private String subnet_id;

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

    public String getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getSubnet_id() {
        return subnet_id;
    }

    public void setSubnet_id(String subnet_id) {
        this.subnet_id = subnet_id;
    }
}
