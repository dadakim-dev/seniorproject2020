package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.openstack.Instance;
import com.dahee8kim.monitoring.domain.openstack.NetworkInterface;
import com.dahee8kim.monitoring.domain.openstack.NetworkList;
import com.dahee8kim.monitoring.domain.openstack.Routers;
import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.osm.Token;
import com.dahee8kim.monitoring.restAPI.openstack.NetworkTopologyController;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.openstack.ResourceController;
import com.dahee8kim.monitoring.restAPI.openstack.VMInstanceController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import com.dahee8kim.monitoring.restAPI.prometheus.VMStatusController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        // OSM Token
        OSMTokenController OSMTokenController = new OSMTokenController();
        Token token = OSMTokenController.getToken();

        // NS List
        NSController nsController = new NSController();
        nsController.setToken(token.getToken());
        ArrayList<NS> ns = nsController.getNS();

        model.addAttribute("ns", ns);

        // OpenStack Token
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        // Resource Info
        ResourceController resourceController = new ResourceController();
        resourceController.setToken(openStackToken);
        model.addAttribute("resource", resourceController.getResource());

        // OpenStack VM Instance | OSM VM Instance
        VMInstanceController vmInstanceController = new VMInstanceController();
        vmInstanceController.setToken(openStackToken);
        ArrayList<Instance> instance = vmInstanceController.getInstance();
        model.addAttribute("instance", instance);

        // OpenStack Network Topology
        NetworkTopologyController networkTopologyController = new NetworkTopologyController();
        networkTopologyController.setToken(openStackToken);
        ArrayList<NetworkList> networkLists = networkTopologyController.getNetworkList();
        ArrayList<Routers> routerlists = networkTopologyController.getRouterslist();
        ArrayList<NetworkInterface> interfacelists = networkTopologyController.getInterfacelist();
        model.addAttribute("networkLists",networkLists);
        model.addAttribute("routerlists",routerlists);
        model.addAttribute("interfacelists",interfacelists);


        return "index";
    }
}
