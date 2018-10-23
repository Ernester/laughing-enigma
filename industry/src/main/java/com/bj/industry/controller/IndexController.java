package com.bj.industry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/industry")
public class IndexController {

    @RequestMapping("/home")
    public String home(){
        return "index";
    }
}
