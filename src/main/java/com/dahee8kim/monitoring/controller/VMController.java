package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VMController {
    @GetMapping("/vm")
    public String VM() {
        return "/vm/index";
    }
}
