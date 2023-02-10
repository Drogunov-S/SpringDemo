package ru.drogunov.springcource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello-world")
    public String helloSay() {
        return "hello_world";
    }
    
}
