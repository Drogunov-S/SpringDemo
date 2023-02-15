package ru.drogunov.springcource.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springcource.dao.BookDAO;
import ru.drogunov.springcource.dao.PersonDAO;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "book/books";
    }
    
    @GetMapping("{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        Book show = bookDAO.show(id);
        if (show.getPersonId() == 0) {
            model.addAttribute("people", personDAO.index());
            model.addAttribute("person", new Person());
        } else {
            model.addAttribute("person", personDAO.show(show.getPersonId()));
        }
        model.addAttribute("book", show);
        return "book/show";
    }
    
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
//    public String newBook(Model model) {
//        model.addAttribute("book", new Book());
        return "book/new";
    }
    
    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/new";
        }
        bookDAO.save(book);
        return "redirect: book/books";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable int id) {
        Book show = bookDAO.show(id);
        model.addAttribute("book", show);
        return "book/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@PathVariable String id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        
        bookDAO.update(book);
        return "redirect: book/books";
    }
    
    @PostMapping("{id}/free")
    public String reserved(@PathVariable int id,
                           @ModelAttribute("person") Person person) {
        bookDAO.reserved(id, person);
        return String.format("redirect: /books/%d", id);
    }
    
    @PatchMapping("{id}/free")
    public String freedomBook(@ModelAttribute("book") Book book,
                              @PathVariable("id") int id) {
        bookDAO.freedom(id);
        return String.format("redirect: /books/%d", book.getId());
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        bookDAO.delete(id);
        return "redirect: book/books";
    }
}

