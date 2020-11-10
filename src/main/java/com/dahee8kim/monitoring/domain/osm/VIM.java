package com.dahee8kim.monitoring.domain.osm;

public class VIM {
    private String vim_type;
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVim_type() {
        return vim_type;
    }

    public void setVim_type(String vim_type) {
        this.vim_type = vim_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
