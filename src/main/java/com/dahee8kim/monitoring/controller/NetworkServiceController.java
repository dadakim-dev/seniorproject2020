package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NetworkServiceController {
    @GetMapping("/ns")
    public String NetworkService() {
        return "ns/index";
    }

    @GetMapping("/ns/detail")
    public String NetworkServiceDetail(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "ns/detail";
    }
}
