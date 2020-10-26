package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.domain.osm.NS;
import com.dahee8kim.monitoring.domain.osm.Token;
import com.dahee8kim.monitoring.restAPI.osm.NSController;
import com.dahee8kim.monitoring.restAPI.osm.TokenController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        TokenController tokenController = new TokenController();
        NSController nsController = new NSController();

        // 토큰 정보
        Token token = tokenController.getToken();
        // NS 리스트
        ArrayList<NS> ns = nsController.getNS(token.getToken());
        // OpenStack Usage Info
        // OpenStack VM Instance | OSM VM Instance

        return "index";
    }
}
