package com.dahee8kim.monitoring.domain.openstack;

public class NetworkList {

    private String id;
    private String name;
    private String tenant_id;
    private String admin_state_up;
    private String mtu;
    private String status;
    private String[] subnets;
    private String shared;
    private String availability_zone_hints;
    private String[] availablity_zones;
    private String ipv4;
    private String ipv6;
    private String router_external;
    private String description;
    private String port_security;
    private String is_default;
    private String[] tags; // created, updated, revision, project_id
    // provider network_type, physical_network, segmentaion


    public String getId() {  return id;  }
    public String getName() {  return name; }
    public String getAdmin_state_up() {        return admin_state_up;    }
    public String getAvailability_zone_hints() {  return availability_zone_hints;   }
    public String getDescription() {  return description;  }
    public String getIpv4() {    return ipv4;   }
    public String getMtu() {  return mtu;  }
    public String getIpv6() { return ipv6;}
    public String getIs_default() {   return is_default;   }
    public String getPort_security() {   return port_security;  }
    public String getRouter_external() {   return router_external;   }
    public String getShared() {     return shared;   }
    public String getStatus() {   return status;   }
    public String getTenant_id() {    return tenant_id;   }
    public String[] getAvailablity_zones() {   return availablity_zones;  }
    public String[] getSubnets() {    return subnets;  }
    public String[] getTags() {    return tags;  }

}
