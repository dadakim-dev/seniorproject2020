package com.dahee8kim.monitoring.controller;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        OSMTokenController osmTokenController = new OSMTokenController();
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();

        NSController nsController = new NSController();
        nsController.setToken(osmTokenController.getToken());

        model.addAttribute("NSs", nsController.getNSs());
        return "index";
    }
}
