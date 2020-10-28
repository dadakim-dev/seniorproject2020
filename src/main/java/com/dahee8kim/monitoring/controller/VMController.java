package com.dahee8kim.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VMController {
    @GetMapping("/vm")
    public String VM() {
        return "/vm/index";
    }

    @GetMapping("/vm/detail")
    public String VMDetail(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "/vm/detail";
    }
}
