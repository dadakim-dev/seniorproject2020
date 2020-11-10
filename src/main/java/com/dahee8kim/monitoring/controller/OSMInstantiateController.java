package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.NSDescriptor;
import com.dahee8kim.monitoring.domain.osm.VIM;
import com.dahee8kim.monitoring.restAPI.osm.NSDescriptorController;
import com.dahee8kim.monitoring.restAPI.osm.NSInstantiateController;
import com.dahee8kim.monitoring.restAPI.osm.OSMTokenController;
import com.dahee8kim.monitoring.restAPI.osm.VIMController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class OSMInstantiateController {
    @GetMapping("/instantiate")
    public String VimNSDLists(Model model){
        // get osm token
        OSMTokenController osmTokenController = new OSMTokenController();
        String osmToken = osmTokenController.getToken();

        // set osm token to vim controller
        VIMController vimController = new VIMController();
        vimController.setToken(osmToken);

        // get all VIM
        ArrayList<VIM> vims = vimController.getVIMs();
        model.addAttribute("VIMs", vims);

        // set osm token to nsdescriptor controller
        NSDescriptorController nsDescriptorController = new NSDescriptorController();
        nsDescriptorController.setToken(osmToken);

        // get all NS Derscriptor
        ArrayList<NSDescriptor> nsDescriptors = nsDescriptorController.getNSDs();
        model.addAttribute("NSDs", nsDescriptors);

        return "ns/instantiate";
    }

    @GetMapping("/instantiating")
    public String Instantiate(@RequestParam("ns-name") String name,
                              @RequestParam("vim-account") String vim,
                              @RequestParam("nsd") String descriptor){

        // get osm token
        OSMTokenController osmTokenController = new OSMTokenController();
        String osmToken = osmTokenController.getToken();

        // set osm token to nsinstantiate controller
        NSInstantiateController nsInstantiateController = new NSInstantiateController();
        nsInstantiateController.setToken(osmToken);
        nsInstantiateController.instantiateNS(name, vim, descriptor);

        return "redirect:/";
    }

}
