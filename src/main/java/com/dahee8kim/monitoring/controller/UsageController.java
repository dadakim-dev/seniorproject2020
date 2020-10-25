package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsageController {
    @GetMapping("/usage")
    public String Usage() {
        return "usage/index";
    }
}
