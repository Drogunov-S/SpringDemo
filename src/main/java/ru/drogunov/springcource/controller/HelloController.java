package ru.drogunov.springcource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/first")
public class HelloController {
    @GetMapping("/hello")
    public String helloSay(@RequestParam(name = "name", required = false) String name,
                           @RequestParam(name = "surname", required = false) String surname) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
        System.out.println(name + " " + surname);
        return "/first/hello";
    }
    
}
