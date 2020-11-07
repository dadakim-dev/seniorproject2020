package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class NetworkServiceController {
    @GetMapping("/ns")
    public String NetworkService(Model model) {
        OSMTokenController osmTokenController = new OSMTokenController();
        NSController nsController = new NSController();
        nsController.setToken(osmTokenController.getToken());
        ArrayList<NS> nsList = nsController.getNSs();

        model.addAttribute("NSs", nsList);
        return "ns/index";
    }

    @GetMapping("/ns/detail")
    public String NetworkServiceDetail(@RequestParam("id") String id, Model model) {
        return "ns/detail";
    }
}
