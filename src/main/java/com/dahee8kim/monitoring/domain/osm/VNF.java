package com.dahee8kim.monitoring.domain.osm;

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

    private String id;
    private String vimId;
}
