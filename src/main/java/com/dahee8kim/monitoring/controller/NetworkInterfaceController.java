package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.openstack.NetworkInterface;
import com.dahee8kim.monitoring.domain.openstack.NetworkList;
import com.dahee8kim.monitoring.domain.openstack.Routers;
import com.dahee8kim.monitoring.restAPI.openstack.NetworkTopologyController;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class NetworkInterfaceController {
    @GetMapping("/network")
    public String Network(Model model){
        // OpenStack Network Topology
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        NetworkTopologyController networkTopologyController = new NetworkTopologyController();
        networkTopologyController.setToken(openStackToken);
        ArrayList<NetworkList> networkLists = networkTopologyController.getNetworkList();
        ArrayList<Routers> routerlists = networkTopologyController.getRouterslist();
        ArrayList<NetworkInterface> interfacelists = networkTopologyController.getInterfacelist();

        model.addAttribute("networkLists", networkLists);
        model.addAttribute("routerLists", routerlists);
        model.addAttribute("interfaceLists", interfacelists);

        return "/network/index";
    }
}
