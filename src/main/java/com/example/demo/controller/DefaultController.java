package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/testApi")
    public String home() {
        return "test";
    }

    @RequestMapping("/")
    public String grid() {
        return "grid";
    }

    @RequestMapping("/view/{modal}")
    public String home(@PathVariable String modal) {
        return "/modal/" +
                (  modal.equals("add_record") ? "add_record" :
                modal.equals("update_record") ? "update_record" : "view_record" );
    }

    @RequestMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @RequestMapping("/error")
    public String error() {
        return "/error/400";
    }

}