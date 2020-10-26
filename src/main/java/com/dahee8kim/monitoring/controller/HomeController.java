package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.Token;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.TokenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        TokenController tokenController = new TokenController();
        NSController nsController = new NSController();

        // 토큰 정보
        Token token = tokenController.getToken();
        System.out.println(nsController.getNS(token.getToken()));

        return "index";
    }
}
