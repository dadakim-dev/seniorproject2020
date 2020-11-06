package com.dahee8kim.monitoring.domain.openstack;

import java.util.ArrayList;

public class NetworkList {

    private String id;
    private String name;
    private String tenant_id;
    private String status;
    private ArrayList<String> subnets = new ArrayList<>();
    private String shared;
//    private String admin_state_up;
//    private String mtu;
//    private String availability_zone_hints;
//    private String[] availablity_zones;
//    private String ipv4;
//    private String ipv6;
//    private String router_external;
//    private String description;
//    private String port_security;
//    private String is_default;
//    private String[] tags; // created, updated, revision, project_id
//    provider network_type, physical_network, segmentaion

    public String getId() { return id;  }
    public String getName() { return name; }
    public String getStatus() {  return status;  }
    public String getTenant_id() { return tenant_id;   }
    public String getShared() {  return shared;   }
    public ArrayList<String> getSubnets() { return subnets;  }

    public void setId(String id) { this.id = id;  }
    public void setName(String name) { this.name = name; }
    public void setStatus(String status) {this.status = status; }
    public void setTenant_id(String tenant_id) { this.tenant_id = tenant_id; }
    public void setShared(String shared) { this.shared = shared; }
    public void setSubnets(ArrayList<String> subnets) {this.subnets = subnets; }

}
