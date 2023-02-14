package ru.drogunov.springcource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springcource.dao.PersonDAO;
import ru.drogunov.springcource.model.Person;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    private final PersonDAO personDAO;
    
    @Autowired
    public AdminsController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
    @GetMapping
    public String index(Model model,
                        @ModelAttribute("person") Person person
    ) {
        model.addAttribute("people", personDAO.index());
//        model.addAttribute("role", person.getRole());
        return "admin/admin";
    }
    
    @PatchMapping("/add")
    public String add(@ModelAttribute("person") Person person, Model model,
                      @RequestParam("role") String role) {
        Map<String, Object> stringObjectMap = model.asMap();
//        String role = (String) model.getAttribute("role");
        personDAO.updateRole(person, role);
        return "redirect: /admin";
    }
}
