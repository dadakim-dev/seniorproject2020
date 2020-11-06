package com.dahee8kim.monitoring.domain.osm;

public class NS {
    private String id;
    private String name;
    private String description;
    private String nsState;
    private String operationalStatus;
    private String configStatus;
    private String detailedStatus;
    private String errorDetail;
    private String vim_net_id;

    public String getVim_net_id() {
        return vim_net_id;
    }

    public void setVim_net_id(String vim_net_id) {
        this.vim_net_id = vim_net_id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNsState() {
        return nsState;
    }

    public void setNsState(String nsState) {
        this.nsState = nsState;
    }

    public String getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public String getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(String configStatus) {
        this.configStatus = configStatus;
    }

    public String getDetailedStatus() {
        return detailedStatus;
    }

    public void setDetailedStatus(String detailedStatus) {
        this.detailedStatus = detailedStatus;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
