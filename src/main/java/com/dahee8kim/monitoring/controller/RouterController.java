package com.dahee8kim.monitoring.controller;

import com.dahee8kim.monitoring.restAPI.openstack.OpenStackTokenController;
import com.dahee8kim.monitoring.restAPI.openstack.RouterAddController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RouterController {
    @PostMapping("/router")
    public String RouterAdd(@RequestParam("routerName") String name){
        // OpenStack Network Topology
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        RouterAddController routerAddController = new RouterAddController();
        routerAddController.setToken(openStackToken);
        Boolean result = routerAddController.createRouter(name);

        return "redirect:/network";
    }

    @GetMapping("/interface")
    public String InterfaceAdd(@RequestParam("router-id") String routerId, @RequestParam("subnet-id") String subnetId){
        // OpenStack Network Topology
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        RouterAddController routerAddController = new RouterAddController();
        routerAddController.setToken(openStackToken);
        Boolean result = routerAddController.addInterface(routerId,subnetId);

        return "redirect:/";}

    @GetMapping("/subnet")
    public String SubnetAdd(
            @RequestParam("subnet-name") String subnetName,
            @RequestParam("network-id") String networkId,
            @RequestParam("ip-version") String ipv,
            @RequestParam("cidr") String cidr,
            @RequestParam("start-pool") String startPool,
            @RequestParam("end-pool") String endPool
    ){
        // OpenStack Network Topology
        OpenStackTokenController openStackTokenController = new OpenStackTokenController();
        String openStackToken = openStackTokenController.getToken();

        RouterAddController routerAddController = new RouterAddController();
        routerAddController.setToken(openStackToken);
        Boolean result = routerAddController.createSubnet(subnetName,networkId,ipv,cidr,startPool,endPool);

        return "redirect:/";}

}
