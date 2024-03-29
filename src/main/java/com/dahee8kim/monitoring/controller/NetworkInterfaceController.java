package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.openstack.Router;
import com.dahee8kim.monitoring.domain.openstack.Subnet;
import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.openstack.Network;
import com.dahee8kim.monitoring.domain.osm.VNF;
import com.dahee8kim.monitoring.restAPI.openstack.NetworkController;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.openstack.RouterController;
import com.dahee8kim.monitoring.restAPI.openstack.SubnetController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import com.dahee8kim.monitoring.restAPI.osm.VNFController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class NetworkInterfaceController {
    @GetMapping("/network")
    public String Network(Model model){
        // OSM Token
        OSMTokenController OSMTokenController = new OSMTokenController();
        String osmToken = OSMTokenController.getToken();

        // OpenStack Token
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        // Get VNF Controller and set Auth (osm)
        VNFController vnfController = new VNFController();
        vnfController.setToken(osmToken);

        // Get NS Controller and set Auth (osm)
        NSController nsController = new NSController();
        nsController.setToken(osmToken);

        // Get network controller and set Auth (openstack)
        NetworkController networkController = new NetworkController();
        networkController.setToken(openStackToken);

        SubnetController subnetController = new SubnetController();
        subnetController.setToken(openStackToken);

        RouterController routerController = new RouterController();
        routerController.setToken(openStackToken);
      
        // Get NS list, VNF list, network list
        ArrayList<NS> NSs = nsController.getNSs();
        ArrayList<VNF> VNFs = vnfController.getVNFs();
        ArrayList<Network> networks = networkController.getNetworks();
        ArrayList<Subnet> subnets = subnetController.getSubnets();
        ArrayList<Router> routers = routerController.getRouters();

        // Mapping NS~VNF id list - VNF id
        AtomicInteger nsIndex = new AtomicInteger();
        for (NS ns : NSs) {
            ArrayList<String> vnfIds = ns.getVnfIds();
            ns.setVNFs(
                (ArrayList<VNF>) VNFs.stream()
                        .filter(vnf -> vnfIds.contains(vnf.getId()))
                        .collect(Collectors.toList())
            );
        }

        // Mapping Network id - VNF vim network id
        for (Network network : networks) {
            network.setNSs((ArrayList<NS>) NSs.stream()
                    .filter(ns ->
                            ns.getVimNetId().equals(network.getId()))
                    .collect(Collectors.toList())
            );
            network.setSubnets((ArrayList<Subnet>) subnets.stream()
                    .filter(subnet ->
                            subnet.getNetworkId().equals(network.getId()))
                    .collect(Collectors.toList()));
        }

        // export network list including NS, VNF to model
        model.addAttribute("networks", networks);
        model.addAttribute("routers", routers);
//        model.addAttribute("subnets", subnets);

        return "/network/index";
    }
}
