package com.dahee8kim.monitoring.controller;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.openstack.ResourceController;
import com.dahee8kim.monitoring.restAPI.openstack.VMInstanceController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        // get osm token
        OSMTokenController osmTokenController = new OSMTokenController();
        String osmToken = osmTokenController.getToken();

        // get openstack token
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        // get ns list and add to model
        NSController nsController = new NSController();
        nsController.setToken(osmToken);
        model.addAttribute("NSs", nsController.getNSs());

        // get resource state and add to model
        ResourceController resourceController = new ResourceController();
        resourceController.setToken(openStackToken);
        model.addAttribute("resource", resourceController.getResource());

        VMInstanceController vmInstanceController = new VMInstanceController();
        vmInstanceController.setToken(openStackToken);
        model.addAttribute("VMs", vmInstanceController.getInstance());

        return "index";
    }
}
