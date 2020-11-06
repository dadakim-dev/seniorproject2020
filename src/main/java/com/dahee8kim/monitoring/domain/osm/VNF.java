package com.dahee8kim.monitoring.domain.osm;

import com.dahee8kim.monitoring.domain.openstack.Flavor;

public class VNF {
    private String id;
    private String vimId;
    private String name;
    private Flavor flavor;

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
