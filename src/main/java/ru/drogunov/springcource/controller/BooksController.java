package ru.drogunov.springcource.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.services.BookService;
import ru.drogunov.springcource.services.PeopleService;

import java.util.List;

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
    public String index(
            @RequestParam(required = false, defaultValue = "3") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "false") Boolean sortByYear,
            Model model) {

        
        model.addAttribute("page", bookService.findAll(PageRequest.of(page, pageSize), sortByYear));
        return "book/books";
    }
    
    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id,
                       Model model) {
        Book book = bookService.findOne(id);
            model.addAttribute("people", peopleService.findAll());
            model.addAttribute("person", new Person());
        model.addAttribute("book", book);
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
    
    @GetMapping("/search")
    public String searchPage() {
        return "book/search";
    }
    
    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        List<Book> attributeValue = bookService.searchByTitle(query);
        model.addAttribute("books", attributeValue);
        return "book/search";
    }
}

