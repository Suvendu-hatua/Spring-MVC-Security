package com.spring_secuirty.SpringMVC_Secuirty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String login(){
        return "home";
    }
}
