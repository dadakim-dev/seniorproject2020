package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NetworkServiceController {
    @GetMapping("/ns")
    public String NetworkService() {
        return "ns/index";
    }
}
