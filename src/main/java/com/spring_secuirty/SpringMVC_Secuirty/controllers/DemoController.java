package com.spring_secuirty.SpringMVC_Secuirty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/showCustomLogin")
    public String customLoginPage(){
        return "custom-login";
    }

    @GetMapping("/leaders")
    public String showLeadersInfo(){
        return "leader-meeting";
    }

    @GetMapping("/systems")
    public String showAdminInfo(){
        return "admin-meeting";
    }
}
