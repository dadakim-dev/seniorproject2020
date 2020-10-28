package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class kube {
    @GetMapping("/kube")
    public String Kubernetes() {
        return "kube/index";
    }
}
