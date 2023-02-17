package ru.drogunov.springcource.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.services.BookService;
import ru.drogunov.springcource.services.PeopleService;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final PeopleService peopleService;
    
    @Autowired
    public BooksController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/books";
    }
    
    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id,
                       Model model) {
        Book show = bookService.findOne(id);
        if (isNull(show.getPersonId())) {
            model.addAttribute("people", peopleService.findAll());
            model.addAttribute("person", new Person());
        } else {
            model.addAttribute("person", peopleService.findOne(show.getPersonId()));
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
        bookService.save(book);
        return "redirect: " + book.getId();
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable Integer id) {
        Book show = bookService.findOne(id);
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
        
        bookService.update(book);
        return "redirect: book/books";
    }
    
    /*TODO у нас в моделе же должкн быть бук, передавать его сразу*/
    @PostMapping("{id}/free")
    public String reserved(@PathVariable Integer id,
                           @ModelAttribute("person") Person person) {
        bookService.reserved(id, person);
        return String.format("redirect: /books/%d", id);
    }
    
    @PatchMapping("{id}/free")
    public String freedomBook(@ModelAttribute("book") Book book,
                              @PathVariable("id") Integer personId) {
        bookService.freedom(personId);
        return String.format("redirect: /books/%d", book.getId());
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        bookService.delete(id);
        return "redirect: book/books";
    }
}

