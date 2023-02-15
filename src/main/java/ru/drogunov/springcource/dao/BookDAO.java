package ru.drogunov.springcource.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.drogunov.springcource.dao.mappers.BookMapper;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;

import java.util.List;

@Repository
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;
    
    public BookDAO(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }
    
    public List<Book> index() {
        String query = "SELECT * FROM book;";
        return jdbcTemplate.query(query, bookMapper);
    }
    
    public Book show(int id) {
        String query = "SELECT * FROM book WHERE id = ?;";
        return jdbcTemplate.query(query, new Object[]{id}, bookMapper)
                .stream()
                .findAny()
                .orElse(null);
    }
    
    public void delete(String id) {
        String query = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
    
    public void update(Book book) {
        String query = "UPDATE book SET title=?, author=?, year=?, person_id=? WHERE id =?";
        jdbcTemplate.update(query,
                book.getTitle(),
                book.getAuthor(),
                book.getYearManufactured(),
                book.getPersonId(),
                book.getId());
    }
    
    public void save(Book book) {
        String query = "INSERT INTO book(title, author, year) VALUES (?,?,?);";
        jdbcTemplate.query(query,
                new Object[]{book.getTitle(), book.getAuthor(), book.getYearManufactured()},
                bookMapper);
    }
    
    public void freedom(Book book) {
        String query = "UPDATE book SET person_id = NULL WHERE id = ?";
        jdbcTemplate.update(query, book.getId());
    }
    
    public void freedom(int id) {
        String query = "UPDATE book SET person_id = NULL WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
    
    public void reserved(int bookId, Person person) {
        String query = "UPDATE book SET person_id = ? WHERE id = ?";
        jdbcTemplate.update(query, person.getId(), bookId);
    }
    
    public List<Book> indexByPerson(int personId) {
        String query = "SELECT * FROM book WHERE person_id=?";
        return jdbcTemplate.query(query, new Object[]{personId}, bookMapper);
    }
}
