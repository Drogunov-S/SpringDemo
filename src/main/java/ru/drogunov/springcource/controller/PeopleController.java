package ru.drogunov.springcource.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springcource.dao.PersonDAO;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.util.validators.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }
    
    /**
     * Получение все людей для передачи на представление
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/people";
    }
    
    /**
     * Get people from id, and return in view
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
    
    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("person") Person person) {
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect: /people";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") Integer id) {
        Person show = personDAO.show(id);
        model.addAttribute("person", show);
        return "people/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(person);
        return "redirect: /people";
    }
    
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") Integer id) {
        personDAO.delete(id);
        return "redirect: /people";
    }
}
