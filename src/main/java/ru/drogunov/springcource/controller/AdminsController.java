package ru.drogunov.springcource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springcource.dao.PersonDAO;
import ru.drogunov.springcource.model.Person;

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
                        @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDAO.index());
        return "admin/admin";
    }
    
    @PatchMapping("/add")
    public String add(@ModelAttribute("person") Person person,
                      @RequestParam("role") String role) {
        personDAO.updateRole(person, role);
        return "redirect: /admin";
    }
}
