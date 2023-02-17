package ru.drogunov.springcource.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.repositories.BookRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    public Book findOne(Integer id) {
        return bookRepository.findById(id)
                .orElse(null);
    }
    
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
    
    @Transactional
    public void update(Book book) {
        bookRepository.save(book);
    }
    
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
    
    @Transactional
    public void reserved(Integer bookId, Person person) {
        /*TODO переделать на объекты*/
        Book book = bookRepository.findById(bookId).get();
        book.setPerson(person.getId());
        update(book);
    }
    
    @Transactional
    public void freedom(Integer bookId) {
        Book book = bookRepository.findById(bookId).get();
        book.setPerson(null);
        update(book);
    }
}
