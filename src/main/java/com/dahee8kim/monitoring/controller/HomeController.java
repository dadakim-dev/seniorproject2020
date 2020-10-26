package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.osm.Token;
import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.openstack.ResourceController;
import com.dahee8kim.monitoring.restAPI.openstack.VMInstanceController;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        OSMTokenController OSMTokenController = new OSMTokenController();
        NSController nsController = new NSController();

//         토큰 정보
        Token token = OSMTokenController.getToken();

//         NS 리스트
        ArrayList<NS> ns = nsController.getNS(token.getToken());
        model.addAttribute("ns", ns);

        // OpenStack Token
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        ResourceController resourceController = new ResourceController();
        resourceController.setToken(openStackToken);
        model.addAttribute("resource", resourceController.getResource());

        // OpenStack Usage Info
//        System.out.println(vmInstanceController.getUsages(openStackToken));

        // OpenStack Total Resource
//        System.out.println(vmInstanceController.getInventories(openStackToken));

        // OpenStack VM Instance | OSM VM Instance

        return "index";
    }
}
