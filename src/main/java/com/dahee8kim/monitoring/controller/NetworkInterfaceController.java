package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NetworkInterfaceController {
    @GetMapping("/network")
    public String Network(){
        return "/network/index";
    }
}
