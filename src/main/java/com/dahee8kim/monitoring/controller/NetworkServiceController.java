package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.osm.Token;
import com.dahee8kim.monitoring.domain.osm.VNF;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import com.dahee8kim.monitoring.restAPI.osm.VNFController;
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
        String osmToken = osmTokenController.getToken();

//        NSController nsController = new NSController();
//        nsController.setToken(osmToken);

        VNFController vnfController = new VNFController(osmToken);
        ArrayList<VNF> vnfList = vnfController.getVNFList();

//        ArrayList<NS> nsList = nsController.getNS(true);


//        model.addAttribute("ns", nsList);

        return "ns/index";
    }

    @GetMapping("/ns/detail")
    public String NetworkServiceDetail(@RequestParam("id") String id, Model model) {
        // OSM Token
//        OSMTokenController OSMTokenController = new OSMTokenController();
//        Token token = OSMTokenController.getToken();
//
//        NSController nsController = new NSController();
//        nsController.setToken(token.getToken());
//        nsController.getNSDetail(id);
//
//        model.addAttribute("id", id);

        return "ns/detail";
    }
}
