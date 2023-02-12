package ru.drogunov.springcource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.dao.mappers.PersonMapper;
import ru.drogunov.springcource.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper;
    
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, PersonMapper personMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.personMapper = personMapper;
    }
    
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM person;";
        return jdbcTemplate.query(query, personMapper);
    }
    
    /**
     * Пример с RowMapper от Spring
     */
    public Person getById(int id) {
        String query = "SELECT * FROM person WHERE id=?";
        return jdbcTemplate.query(query, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }
    
    public void save(Person person) {
        String query = "INSERT INTO person(name, surname, age, email) VALUES(?,?,?,?)";
        jdbcTemplate.update(query, person.getName(), person.getSurname(), person.getAge(), person.getEmail());
    }
    
    public void update(int id, Person person) {
        String query = "UPDATE person SET name=?, surname=?, age=?, email=? WHERE id=?";
        jdbcTemplate.update(query, person.getName(), person.getSurname(), person.getAge(), person.getEmail(), id);
    }
    
    public void delete(int id) {
        String query = "DELETE FROM person WHERE id=?";
        jdbcTemplate.update(query, id);
    }
}









