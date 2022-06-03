package com.laborator11.network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@CrossOrigin("*")
public class StartController {
    @GetMapping
    public String getStartPage() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    @PostMapping("/users/register")
    public String test(){
        return "index";
    }

    @GetMapping("/public/{name}")
    public String getResource(@PathVariable("name") String file) {
        return "public/" + file;
    }
}
