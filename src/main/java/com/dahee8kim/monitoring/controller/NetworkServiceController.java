package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.openstack.Instance;
import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.osm.VNF;
import com.dahee8kim.monitoring.restAPI.openstack.FlavorController;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.openstack.VMInstanceController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import com.dahee8kim.monitoring.restAPI.osm.VNFController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
public class NetworkServiceController {
    @GetMapping("/ns")
    public String NetworkService(Model model) {
        // get osm token
        OSMTokenController osmTokenController = new OSMTokenController();
        String osmToken = osmTokenController.getToken();

        // get openstack token
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openstackToken = openStackTokenController.getToken();

        // set osm token to ns controller
        NSController nsController = new NSController();
        nsController.setToken(osmToken);

        // set osm token to vnf controller
        VNFController vnfController = new VNFController();
        vnfController.setToken(osmToken);

        // set openstack token to instance controller
        VMInstanceController vmInstanceController = new VMInstanceController();
        vmInstanceController.setToken(openstackToken);

        // set openstack token to flavor controller
        FlavorController flavorController = new FlavorController();
        flavorController.setToken(openstackToken);

        // get all ns
        ArrayList<NS> NSs = nsController.getNSs();
        ArrayList<VNF> VNFs = new ArrayList<>();

        if(NSs.size() > 0) {
            // get all vnf
            VNFs = vnfController.getVNFs();

            // set flavor to vnf
            for (VNF vnf : VNFs) {
                Instance instance = vmInstanceController.getVMInstance(vnf.getVimId());
                vnf.setFlavor(flavorController.getFlavor(instance.getFlavorId()));
                vnf.setVmStatus(instance.getVmStatus());
            }

            // matching ns and vnf
            for (NS ns : NSs) {
                ArrayList<String> vnfIds = ns.getVnfIds();
                try {
                    ns.setVNFs(
                            (ArrayList<VNF>) VNFs.stream()
                                    .filter(vnf -> vnfIds.contains(vnf.getId()))
                                    .collect(Collectors.toList())
                    );
                } catch (NullPointerException e) {
                    ns.setVNFs(new ArrayList<>());
                }
            }
        }

        // set attribute NS including VNF
        model.addAttribute("NSs", NSs);
        return "ns/index";
    }

    @GetMapping("/ns/detail")
    public String NetworkServiceDetail(@RequestParam("id") String id, Model model) {
        return "ns/detail";
    }
}
