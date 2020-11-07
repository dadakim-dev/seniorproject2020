package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import com.dahee8kim.monitoring.restAPI.osm.VNFController;
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
        String osmToken = OSMTokenController.getToken();

        VNFController vnfController = new VNFController();
        vnfController.setToken(osmToken);
        model.addAttribute("vnfs", vnfController.getVNFs());

        // NS List
        NSController nsController = new NSController();
        nsController.setToken(osmToken);
        ArrayList<NS> ns = nsController.getNS();

        model.addAttribute("ns", ns);

        // OpenStack Token
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();


        return "index";
    }
}
